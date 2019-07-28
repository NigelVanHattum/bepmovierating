package nl.nigelvanhattum.bep.movierating.model;

import nl.nigelvanhattum.bep.movierating.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {
    Movie movie;

    @Before
    public void setMovie() {
        movie = new Movie();
    }

    @Test
    public void testSetName() {
        String name = "The Godfather";
        movie.setName(name);
        Assert.assertEquals(name, movie.getName());
    }

    @Test
    public void testSetRating() {
        double rating = 9.0;
        movie.setRating(rating);
        Assert.assertEquals(rating, movie.getRating(), 0);
    }

    @Test
    public void testSetReleaseDate() {
        String releaseDate = "1994-10-14";
        movie.setReleaseDate(releaseDate);
        Assert.assertEquals(releaseDate, movie.getReleaseDate());
    }
}
