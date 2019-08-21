package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.EncoderType;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;
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
    List<MovieRating> baseMovieRatings;

    @Before
    public void getEncoder() {
        encoder = EncoderFactory.getEncoder(EncoderType.JSON);

        baseMovieRatings = new ArrayList<>();

        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        baseMovieRatings.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        baseMovieRatings.add(movieRating1);

        MovieRating movieRating3 = new MovieRating();
        movieRating3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movieRating3.setReleaseDate("2003-07-09");
        movieRating3.setRating(8.0);
        baseMovieRatings.add(movieRating3);
    }

    @Test
    public void testEncode() {
        String encodedString = encoder.encode(baseMovieRatings);
        Assert.assertEquals(expectedJSON, encodedString);
    }

    @Test
    public void testEncodeStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            encoder.encodeStream(baseMovieRatings, outputStream);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

        String encodedString = outputStream.toString();

        Assert.assertEquals(expectedJSON, encodedString);
    }
}
