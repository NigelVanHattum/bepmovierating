import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new Options();

        Option jsoninput = new Option("json", "jsonInput", true, "input json-file path");
        jsoninput.setRequired(false);
        options.addOption(jsoninput);

        Option xmlinput = new Option("xml", "xmlInput", true, "input xml-file path");
        xmlinput.setRequired(false);
        options.addOption(xmlinput);

        Option output = new Option("out", "output", true, " ");
        xmlinput.setRequired(false);
        options.addOption(xmlinput);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;
        String inputFilePath[] = null;
        String outputFilePath[] = null;

        try {
            cmd = parser.parse(options, args);
            inputFilePath = cmd.getOptionValues("jsonInput");
            outputFilePath = cmd.getOptionValues("xmlInput");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        for(String s: inputFilePath) {
            System.out.println(s);
            File f = new File("Fietsbel");
            System.out.println(f.getAbsolutePath());
        }
        System.out.println(outputFilePath);

    }

}
