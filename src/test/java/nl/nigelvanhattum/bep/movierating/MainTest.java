package nl.nigelvanhattum.bep.movierating;

import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public void testDeleteFile() {
        File file = new File("testFile.test");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Assert.fail(e.getLocalizedMessage());
            }
        }
        Assert.assertTrue(file.delete());
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

        MovieRating movieRating5 = new MovieRating();
        movieRating5.setName("The Shawshank Redemption");
        movieRating5.setReleaseDate("1994-10-14");
        movieRating5.setRating(8.0);
        expected.add(movieRating5);

        MovieRating movieRating6 = new MovieRating();
        movieRating6.setName("The Godfather");
        movieRating6.setReleaseDate("1972-03-24");
        movieRating6.setRating(6.5);
        expected.add(movieRating6);

        Assert.assertEquals(expected, actual);
    }
}
