package nl.nigelvanhattum.bep.movierating;

import nl.nigelvanhattum.bep.movierating.decode.DecoderFactory;
import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.decode.decoder.Decoder;
import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.encoder.Encoder;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import org.apache.commons.cli.*;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    private static HelpFormatter formatter = new HelpFormatter();
    private static final String HASHMAPKEYJSON = "json";
    private static final String HASHMAPKEYXML = "xml";
    private static final String HASHMAPKEYTO = "to";
    private static final String HASHMAPKEYOUTPUT = "output";

    public static void main(String[] args) throws Exception {

        Options options = registerOptions();

        HashMap<String, String[]> parsedParameters = parseOptions(options, args);
        if (parsedParameters.get(HASHMAPKEYJSON) == null && parsedParameters.get(HASHMAPKEYXML) == null) {
            throw new IllegalArgumentException("no input files are given");
        }


        File outputFile = new File(parsedParameters.get(HASHMAPKEYOUTPUT)[0]);
        if (outputFile.exists()) {
            logger.log(Level.WARNING, "Found existing file, overwriting it.");
            deleteFile(outputFile.toPath());
            if (outputFile.createNewFile()) {
                logger.log(Level.INFO, "{0} overwritten", outputFile.getAbsolutePath());
            }
        }

        saveOutput(processFiles(parsedParameters), parsedParameters.get(HASHMAPKEYTO)[0], outputFile);
    }

    static List<MovieRating> processFiles(HashMap<String, String[]> files) {
        List<MovieRating> movieRatings = new ArrayList<>();
        movieRatings.addAll(decodeFiles(files.get(HASHMAPKEYJSON), DecoderType.JSON));
        movieRatings.addAll(decodeFiles(files.get(HASHMAPKEYXML), DecoderType.XML));

        return movieRatings;
    }

    static HashMap<String, String[]> parseOptions(Options options, String[] args) {
        HashMap<String, String[]> returnValue = new HashMap<>();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        String[] jsonFiles = null;
        String[] xmlFiles = null;
        String[] outputEncoding = null;
        String outputLocation = null;

        try {
            cmd = parser.parse(options, args);
            jsonFiles = cmd.getOptionValues("jsonInput");
            returnValue.put(HASHMAPKEYJSON, jsonFiles);
            xmlFiles = cmd.getOptionValues("xmlInput");
            returnValue.put(HASHMAPKEYXML, xmlFiles);
            outputEncoding = cmd.getOptionValues("encodeTo");
            returnValue.put(HASHMAPKEYTO, outputEncoding);

            outputLocation = args[args.length - 1];
            if (arrayContains(jsonFiles, outputLocation) || arrayContains(xmlFiles, outputLocation) || arrayContains(outputEncoding, outputLocation)) {
                logger.log(Level.SEVERE, "No output location given.");
                formatter.printHelp("-json <file> -json <file> -xml <file> -xml <file> -to <xml-json> <output-file>", options);
                System.exit(1);
            }
            String[] outputLocationArray = {outputLocation};
            returnValue.put(HASHMAPKEYOUTPUT, outputLocationArray);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getCause().toString());
            formatter.printHelp("-json <file> -json <file> -xml <file> -xml <file> -to <xml-json> <output-file>", options);
            System.exit(1);
        }


        return returnValue;
    }

    static List<MovieRating> decodeFiles(String[] files, DecoderType type) {
        if (files == null) {
            return new ArrayList<>();
        }
        List<MovieRating> movieRatings = new ArrayList<>();
        for (String file : files) {
            logger.log(Level.INFO, () -> String.format("Decoding %s...", new File(file).getAbsolutePath()));
            Decoder decoder = DecoderFactory.getDecoder(type);
            try (InputStream targetStream = new FileInputStream(file)) {
                movieRatings.addAll(decoder.decodeFromStream(new InputStreamReader(targetStream)));
            } catch (IOException | JAXBException | XMLStreamException | NullPointerException e) {
                logger.log(Level.SEVERE, () -> String.format("Error during decoding of %s, skipping...", file));
                logger.log(Level.SEVERE, e.getLocalizedMessage());
            }
        }
        return movieRatings;
    }

    static void saveOutput(List<MovieRating> movieRatings, String outputEncoding, File outputFile) {
        Encoder encoder = EncoderFactory.getEncoder(outputEncoding);
        try {
            logger.log(Level.INFO, () -> "Starting export to " + outputFile.getAbsolutePath() + " using " + outputEncoding);
            OutputStream outputStream = new FileOutputStream(outputFile);
            encoder.encodeStream(movieRatings, outputStream);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, () -> "Could not find " + outputFile.getAbsolutePath());
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getCause().toString());
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        }
    }

    static void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }

    static Options registerOptions() {
        Options options = new Options();

        Option jsonInput = new Option("json", "jsonInput", true, "input json-file path");
        jsonInput.setRequired(false);
        options.addOption(jsonInput);

        Option xmlInput = new Option("xml", "xmlInput", true, "input xml-file path");
        xmlInput.setRequired(false);
        options.addOption(xmlInput);

        Option output = new Option("to", "encodeTo", true, "Export encoding");
        output.setRequired(true);
        options.addOption(output);

        return options;
    }

    public static boolean arrayContains(String[] array, String match) {
        if (array == null) {
            return false;
        }
        for (String string : array) {
            if (string.equals(match)) {
                return true;
            }
        }
        return false;
    }

}
