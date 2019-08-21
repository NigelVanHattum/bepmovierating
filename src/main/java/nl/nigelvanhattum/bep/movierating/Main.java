package nl.nigelvanhattum.bep.movierating;

import nl.nigelvanhattum.bep.movierating.decode.DecoderFactory;
import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.decode.decoder.Decoder;
import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.encoder.Encoder;
import nl.nigelvanhattum.bep.movierating.model.Movie;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger;

    public static void main(String[] args) throws Exception {
        logger = Logger.getLogger(Main.class.getName());
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

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;
        String[] jsonFiles = null;
        String[] xmlFiles = null;
        String outputEncoding = null;
        String outputLocation = null;
        File outputFile = null;

        try {
            cmd = parser.parse(options, args);
            jsonFiles = cmd.getOptionValues("jsonInput");
            xmlFiles = cmd.getOptionValues("xmlInput");
            outputEncoding = cmd.getOptionValue("encodeTo");

            if(jsonFiles == null && xmlFiles == null) {
                throw new IllegalArgumentException("no input files are given");
            }

            outputLocation = args[args.length -1];
            outputFile = new File(outputLocation);

            if(outputFile.exists()) {
                logger.log(Level.WARNING, "Found existing file, overwriting it.");
                deleteFile(outputFile.toPath());
                if(outputFile.createNewFile()) {
                        logger.log(Level.INFO, "{0} overwritten", outputFile.getAbsolutePath());
                    }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        List<Movie> movies = new ArrayList<>();
        movies.addAll(decodeJSONFiles(jsonFiles));

        saveOutput(movies, outputEncoding, outputFile);

    }

    static List<Movie> decodeJSONFiles(String[] files) {
        List<Movie> movies = new ArrayList<>();
        for(String jsonFile : files) {
            logger.log(Level.FINE, () ->String.format("Decoding %s...", jsonFile));
            Decoder decoder = DecoderFactory.getDecoder(DecoderType.JSON);
            File file = new File(jsonFile);
            try (InputStream targetStream = new FileInputStream(file)){
                movies.addAll(decoder.decodeFromStream(new InputStreamReader(targetStream)));
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return movies;
    }

    static void saveOutput(List<Movie> movies, String outputEncoding, File outputFile) {
        Encoder encoder = EncoderFactory.getEncoder(outputEncoding);
        try {
            logger.log(Level.INFO, () ->"Starting export to " + outputFile.getAbsolutePath() + "using "+ outputEncoding);
            OutputStream outputStream = new FileOutputStream(outputFile);
            encoder.encodeStream(movies, outputStream);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, () ->"Could not find " + outputFile.getAbsolutePath());
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        }
    }

    static void deleteFile(Path path) throws IOException{
        Files.delete(path);
    }

}
