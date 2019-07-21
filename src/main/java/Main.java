import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Test test test");
        Options options = new Options();

        Option jsoninput = new Option("json", "jsonInput", true, "input json-file path");
        jsoninput.setRequired(false);
        options.addOption(jsoninput);

        Option xmlinput = new Option("xml", "xmlInput", true, "input xml-file path");
        xmlinput.setRequired(false);
        options.addOption(xmlinput);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        String inputFilePath = null;
        String outputFilePath = null;

        try {
            System.out.println("test");
            cmd = parser.parse(options, args);
            System.out.println("gottem");
            inputFilePath = cmd.getOptionValue("jsonInput");
            outputFilePath = cmd.getOptionValue("xmlinput");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        System.out.println(inputFilePath);
        System.out.println(outputFilePath);

    }

}
