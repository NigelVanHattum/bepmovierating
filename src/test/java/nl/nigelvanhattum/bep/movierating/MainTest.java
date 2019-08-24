package nl.nigelvanhattum.bep.movierating;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
    Options options;

    @Before
    public void setupOptions() {
        options = new Options();

        Option jsonInput = new Option("json", "jsonInput", true, "input json-file path");
        jsonInput.setRequired(false);
        options.addOption(jsonInput);

        Option xmlInput = new Option("xml", "xmlInput", true, "input xml-file path");
        xmlInput.setRequired(false);
        options.addOption(xmlInput);

        Option output = new Option("to", "encodeTo", true, "Export encoding");
        output.setRequired(true);
        options.addOption(output);
    }

    @Test
    public void testArrayContainsContains() {
        String[] array = {"aap", "noot", "mies"};
        Assert.assertTrue(Main.arrayContains(array, "mies"));
    }

    @Test
    public void testArrayContainsNotContains() {
        String[] array = {"aap", "noot", "mies"};
        Assert.assertFalse(Main.arrayContains(array, "fiets"));
    }

    @Test
    public void testArrayContainsNull() {
        String[] array = null;
        Assert.assertFalse(Main.arrayContains(array, "fiets"));
    }

    @Test
    public void testRegisterOptions() {
        Assert.assertEquals(options.toString(), Main.registerOptions().toString());
    }

    @Test
    public void testEmptyInputFiles() {
        
    }
}
