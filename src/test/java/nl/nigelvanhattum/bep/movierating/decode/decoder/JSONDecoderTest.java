package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.decode.DecoderFactory;
import nl.nigelvanhattum.bep.movierating.decode.DecoderType;
import nl.nigelvanhattum.bep.movierating.model.Movie;
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
    List<Movie> expectedMovies;

    @Before
    public void getDecoder() {
        decoder = DecoderFactory.getDecoder(DecoderType.JSON);
        expectedMovies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setName("The Shawshank Redemption");
        movie1.setReleaseDate("1994-10-14");
        movie1.setRating(9.2);
        expectedMovies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setRating(9.2);
        expectedMovies.add(movie1);

        Movie movie3 = new Movie();
        movie3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movie3.setReleaseDate("2003-07-09");
        movie3.setRating(8.0);
        expectedMovies.add(movie3);
    }

    @Test
    public void testStringDecode() {
        List<Movie> decodedMovies = decoder.decode(encodedString);
        Assert.assertEquals(expectedMovies, decodedMovies);
    }

    @Test
    public void testStreamDecoder() {
        InputStream inputStream = new ByteArrayInputStream( encodedString.getBytes() );
        List<Movie> decodedMovies = decoder.decodeFromStream(new InputStreamReader(inputStream));
        Assert.assertEquals(expectedMovies, decodedMovies);
    }
}
