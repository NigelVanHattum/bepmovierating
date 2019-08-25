package nl.nigelvanhattum.bep.movierating.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovieRatingsTest {
    private List<MovieRating> ratings;

    @Before
    public void setupRatings() {
        ratings = new ArrayList<>();

        MovieRating movieRating1 = new MovieRating();
        movieRating1.setName("The Shawshank Redemption");
        movieRating1.setReleaseDate("1994-10-14");
        movieRating1.setRating(9.2);
        ratings.add(movieRating1);

        MovieRating movieRating2 = new MovieRating();
        movieRating2.setName("The Godfather");
        movieRating2.setReleaseDate("1972-03-24");
        movieRating2.setRating(9.2);
        ratings.add(movieRating1);

        MovieRating movieRating3 = new MovieRating();
        movieRating3.setName("Pirates of the Caribbean: The Curse of the Black Pearl");
        movieRating3.setReleaseDate("2003-07-09");
        movieRating3.setRating(8.0);
        ratings.add(movieRating3);
    }

    @Test
    public void testSetMovieRatings() {
        MovieRatings container = new MovieRatings();
        container.setMovieRatings(ratings);
        Assert.assertEquals(ratings, container.getMovieRatings());
    }
}
