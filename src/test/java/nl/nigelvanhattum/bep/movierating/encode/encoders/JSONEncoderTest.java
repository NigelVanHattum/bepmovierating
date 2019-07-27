package nl.nigelvanhattum.bep.movierating.encode.encoders;

import com.google.gson.Gson;
import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.EncoderType;
import nl.nigelvanhattum.bep.movierating.encode.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONEncoderTest {
    Encoder encoder;

    @Before
    public void getEncoder() {
        encoder = EncoderFactory.getEncoder(EncoderType.JSON);
    }

    @Test
    public void testEncode() {
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setName("The Shawshank Redemption");
        movie1.setReleaseDate("1994-10-14");
        movie1.setRating(9.2);
        movies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setRating(9.2);
        movies.add(movie1);

        Movie movie3 = new Movie();
        movie3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movie3.setReleaseDate("2003-07-09");
        movie3.setRating(8.0);
        movies.add(movie3);

        Gson gson = new Gson();
        String encodedString = gson.toJson(movies);
        String expectedJSON = "[{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"The Shawshank Redemption\",\"releaseDate\":\"1994-10-14\",\"rating\":9.2},{\"name\":\"Pirates of the Caribbean: The Curse of the Black Pearl\",\"releaseDate\":\"2003-07-09\",\"rating\":8.0}]";

        Assert.assertEquals(expectedJSON, encodedString);
    }
}
