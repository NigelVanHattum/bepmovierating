package nl.nigelvanhattum.bep.movierating;

import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainTest {
    Options options;
    List<MovieRating> movieRatings;

    @Before
    public void setupAllRatingsFromTestFiles() {
        movieRatings = new ArrayList<>();
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        movieRatings.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        movieRatings.add(movieRating2);

        MovieRating movieRating3 = new MovieRating();
        movieRating3.setName("The Shawshank Redemption");
        movieRating3.setReleaseDate("1994-10-14");
        movieRating3.setRating(9.5);
        movieRatings.add(movieRating3);

        MovieRating movieRating4 = new MovieRating();
        movieRating4.setName("The Godfather");
        movieRating4.setReleaseDate("1972-03-24");
        movieRating4.setRating(7.5);
        movieRatings.add(movieRating4);

        MovieRating movieRating5 = new MovieRating();
        movieRating5.setName("The Shawshank Redemption");
        movieRating5.setReleaseDate("1994-10-14");
        movieRating5.setRating(8.0);
        movieRatings.add(movieRating5);

        MovieRating movieRating6 = new MovieRating();
        movieRating6.setName("The Godfather");
        movieRating6.setReleaseDate("1972-03-24");
        movieRating6.setRating(6.5);
        movieRatings.add(movieRating6);

    }

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
    public void testDeleteFile() throws IOException {
        File file = new File("testFile.test");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Assert.fail(e.getLocalizedMessage());
            }
        }
        Main.deleteFile(file.toPath());
        Assert.assertFalse(file.exists());
    }

    @Test
    public void testDecodeNullFiles() {
        List<MovieRating> actual = Main.decodeFiles(null, DecoderType.JSON);

        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void testDecodeFiles() {
        String aJson = getClass().getResource("/a.json").getFile();
        String bJson = getClass().getResource("/b.json").getFile();
        String[] files = {aJson, bJson};
        List<MovieRating> actual = Main.decodeFiles(files, DecoderType.JSON);

        ArrayList<MovieRating> expected = new ArrayList<>();
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        expected.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        expected.add(movieRating2);

        MovieRating movieRating3 = new MovieRating();
        movieRating3.setName("The Shawshank Redemption");
        movieRating3.setReleaseDate("1994-10-14");
        movieRating3.setRating(9.5);
        expected.add(movieRating3);

        MovieRating movieRating4 = new MovieRating();
        movieRating4.setName("The Godfather");
        movieRating4.setReleaseDate("1972-03-24");
        movieRating4.setRating(7.5);
        expected.add(movieRating4);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testProcessFiles() {
        String aJson = getClass().getResource("/a.json").getFile();
        String bJson = getClass().getResource("/b.json").getFile();
        String cXml = getClass().getResource("/c.xml").getFile();
        String[] jsonFiles = {aJson, bJson};
        String[] xmlFiles = {cXml};

        HashMap<String, String[]> files = new HashMap<>();
        files.put(Main.HASHMAPKEYJSON, jsonFiles);
        files.put(Main.HASHMAPKEYXML, xmlFiles);

        List<MovieRating> actual = Main.processFiles(files);

        Assert.assertEquals(movieRatings, actual);
    }

    @Test
    public void testParseOptions() {
        String[] jsonFiles = {"a.json", "b.sjon"};
        String[] xmlFiles = {"c.xml"};
        String[] outputEncoding = {"json"};
        String[] outputFile = {"outputFile"};

        HashMap<String, String[]> expected = new HashMap<>();
        expected.put(Main.HASHMAPKEYJSON, jsonFiles);
        expected.put(Main.HASHMAPKEYXML, xmlFiles);
        expected.put(Main.HASHMAPKEYTO, outputEncoding);
        expected.put(Main.HASHMAPKEYOUTPUT, outputFile);

        String[] args = {"-json", jsonFiles[0], "-json", jsonFiles[1], "-xml", xmlFiles[0], "-to", outputEncoding[0], outputFile[0]};
        HashMap<String, String[]> actual = Main.parseOptions(options, args);

        Assert.assertEquals(expected.values().size(), actual.values().size());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testParseOptionsNoOutput() {
        exit.expectSystemExitWithStatus(1);
        String[] jsonFiles = {"a.json", "b.sjon"};
        String[] xmlFiles = {"c.xml"};
        String[] outputEncoding = {"json"};

        HashMap<String, String[]> expected = new HashMap<>();
        expected.put(Main.HASHMAPKEYJSON, jsonFiles);
        expected.put(Main.HASHMAPKEYXML, xmlFiles);
        expected.put(Main.HASHMAPKEYTO, outputEncoding);

        String[] args = {"-json", jsonFiles[0], "-json", jsonFiles[1], "-xml", xmlFiles[0], "-to", outputEncoding[0]};
        HashMap<String, String[]> actual = Main.parseOptions(options, args);
    }

    @Test
    public void testEndToEnd() {
        ArrayList<MovieRating> expected = new ArrayList<>();
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        expected.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        expected.add(movieRating2);

        File expectedFile = new File("testFile.xml");

        if (expectedFile.exists()) {
            expectedFile.delete();
        }

        Main.saveOutput(expected, "xml", expectedFile);

        String[] files = {expectedFile.getAbsolutePath()};
        List<MovieRating> actual = Main.decodeFiles(files, DecoderType.XML);

        Assert.assertEquals(expected, actual);
        expectedFile.delete();
    }


    @Test
    public void testMainNoArgs() throws Exception {
        exit.expectSystemExitWithStatus(1);
        String[] emptyArgs = null;
        Main.main(emptyArgs);

    }

    @Test
    public void testMain() throws Exception {
        String aJson = getClass().getResource("/a.json").getFile();
        String bJson = getClass().getResource("/b.json").getFile();
        String cXml = getClass().getResource("/c.xml").getFile();
        String[] jsonFiles = {aJson, bJson};
        String[] xmlFiles = {cXml};
        String[] outputEncoding = {"xml"};
        String[] outputFile = {"outputFile.xml"};

        String[] args = {"-json", jsonFiles[0], "-json", jsonFiles[1], "-xml", xmlFiles[0], "-to", outputEncoding[0], outputFile[0]};

        HashMap<String, String[]> parsedArgs = Main.parseOptions(options, args);

        Main.main(args);

        Assert.assertEquals(movieRatings, Main.processFiles(parsedArgs));

        Main.deleteFile(new File(outputFile[0]).toPath());
    }

    @Test
    public void testOverwriteIfNeededYes() throws IOException {
        String file = "text.testFile";
        File testFile = new File(file);

        if (testFile.exists()) {
            testFile.createNewFile();
        }

        Main.overWriteIfNeeded(testFile);

        Assert.assertTrue(testFile.exists());
        testFile.delete();
    }

    @Test
    public void testOverwriteIfNeededNo() throws IOException {
        String file = "text.testFile";
        File testFile = new File(file);

        if (testFile.exists()) {
            testFile.delete();
        }

        Main.overWriteIfNeeded(testFile);

        Assert.assertTrue(testFile.exists());
        testFile.delete();
    }
}
