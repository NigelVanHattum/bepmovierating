package nl.nigelvanhattum.bep.movierating.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieRatingTest {
    MovieRating movieRating;

    @Before
    public void setMovie() {
        movieRating = new MovieRating();
    }

    @Test
    public void testSetName() {
        String name = "The Godfather";
        movieRating.setName(name);
        Assert.assertEquals(name, movieRating.getName());
    }

    @Test
    public void testSetRating() {
        double rating = 9.0;
        movieRating.setRating(rating);
        Assert.assertEquals(rating, movieRating.getRating(), 0);
    }

    @Test
    public void testSetReleaseDate() {
        String releaseDate = "1994-10-14";
        movieRating.setReleaseDate(releaseDate);
        Assert.assertEquals(releaseDate, movieRating.getReleaseDate());
    }

    @Test
    public void testEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);

        Assert.assertEquals(movieRating1, movieRating2);
    }

    @Test
    public void testNotReleaseEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-04-24");
        movieRating2.setRating(9.2);

        Assert.assertNotEquals(movieRating1, movieRating2);
    }

    @Test
    public void testNotRatingEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-04-24");
        movieRating2.setRating(9.3);

        Assert.assertNotEquals(movieRating1, movieRating2);
    }

    @Test
    public void testNotNameEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Goddfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);

        Assert.assertNotEquals(movieRating1, movieRating2);
    }

    @Test
    public void testExactEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        Assert.assertEquals(movieRating1, movieRating1);
    }

    @Test
    public void testWrongClassEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        String notAMovieString = "This is not a movie";

        Assert.assertNotEquals(movieRating1, notAMovieString);
    }

    @Test
    public void testNullEquals() {
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        Assert.assertNotEquals(movieRating1, null);
    }


    @Test
    public void testHashCode() {
        Integer expectedHash = 2066219782;
        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Godfather");
        movieRating1.setReleaseDate("1972-03-24");
        movieRating1.setRating(9.2);

        Assert.assertEquals(expectedHash, movieRating1.hashCode(), 0);
    }
}
