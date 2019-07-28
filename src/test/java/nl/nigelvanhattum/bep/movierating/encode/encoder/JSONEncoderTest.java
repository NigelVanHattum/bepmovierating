package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.EncoderType;
import nl.nigelvanhattum.bep.movierating.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONEncoderTest {
    private static final String expectedJSON = "[{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"Pirates of the Caribbean: The Curse of the Black Pearl\",\"releaseDate\":\"2003-07-09\",\"rating\":8.0}]";
    Encoder encoder;
    List<Movie> baseMovies;

    @Before
    public void getEncoder() {
        encoder = EncoderFactory.getEncoder(EncoderType.JSON);

        baseMovies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setName("The Shawshank Redemption");
        movie1.setReleaseDate("1994-10-14");
        movie1.setRating(9.2);
        baseMovies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setRating(9.2);
        baseMovies.add(movie1);

        Movie movie3 = new Movie();
        movie3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movie3.setReleaseDate("2003-07-09");
        movie3.setRating(8.0);
        baseMovies.add(movie3);
    }

    @Test
    public void testEncode() {
        String encodedString = encoder.encode(baseMovies);
        Assert.assertEquals(expectedJSON, encodedString);
    }

    @Test
    public void testEncodeStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            encoder.encodeStream(baseMovies, outputStream);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

        String encodedString = outputStream.toString();

        Assert.assertEquals(expectedJSON, encodedString);
    }
}
