package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.decode.DecoderFactory;
import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONDecoderTest {
    private static final String encodedString = "[{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"Pirates of the Caribbean: The Curse of the Black Pearl\",\"releaseDate\":\"2003-07-09\",\"rating\":8.0}]";
    Decoder decoder;
    List<MovieRating> expectedMovieRatings;

    @Before
    public void getDecoder() {
        decoder = DecoderFactory.getDecoder(DecoderType.JSON);
        expectedMovieRatings = new ArrayList<>();

        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        expectedMovieRatings.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        expectedMovieRatings.add(movieRating1);

        MovieRating movieRating3 = new MovieRating();
        movieRating3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movieRating3.setReleaseDate("2003-07-09");
        movieRating3.setRating(8.0);
        expectedMovieRatings.add(movieRating3);
    }

    @Test
    public void testStringDecode() {
        List<MovieRating> decodedMovieRatings = decoder.decode(encodedString);
        Assert.assertEquals(expectedMovieRatings, decodedMovieRatings);
    }

    @Test
    public void testStreamDecoder() {
        InputStream inputStream = new ByteArrayInputStream( encodedString.getBytes() );
        List<MovieRating> decodedMovieRatings = decoder.decodeFromStream(new InputStreamReader(inputStream));
        Assert.assertEquals(expectedMovieRatings, decodedMovieRatings);
    }
}
