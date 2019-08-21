package nl.nigelvanhattum.bep.movierating.model;

import java.util.Objects;

public class MovieRating {
    private String name;
    private String releaseDate;
    private double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieRating movieRating = (MovieRating) o;
        return Double.compare(movieRating.rating, rating) == 0 &&
                name.equals(movieRating.name) &&
                releaseDate.equals(movieRating.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, rating);
    }
}
