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

    @Test
    public void testEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setRating(9.2);

        Assert.assertEquals(movie1, movie2);
    }

    @Test
    public void testNotReleaseEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-04-24");
        movie2.setRating(9.2);

        Assert.assertNotEquals(movie1, movie2);
    }

    @Test
    public void testNotRatingEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-04-24");
        movie2.setRating(9.3);

        Assert.assertNotEquals(movie1, movie2);
    }

    @Test
    public void testNotNameEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Goddfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Movie movie2 = new Movie();
        movie2.setName("The Godfather");
        movie2.setReleaseDate("1972-03-24");
        movie2.setRating(9.2);

        Assert.assertNotEquals(movie1, movie2);
    }

    @Test
    public void testExactEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Assert.assertEquals(movie1, movie1);
    }

    @Test
    public void testWrongClassEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        String notAMovieString = "This is not a movie";

        Assert.assertNotEquals(movie1, notAMovieString);
    }

    @Test
    public void testNullEquals() {
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Assert.assertNotEquals(movie1, null);
    }


    @Test
    public void testHashCode() {
        Integer expectedHash = 2066219782;
        Movie movie1 = new Movie();
        movie1.setName("The Godfather");
        movie1.setReleaseDate("1972-03-24");
        movie1.setRating(9.2);

        Assert.assertEquals(expectedHash, movie1.hashCode(), 0);
    }
}
