package nl.nigelvanhattum.bep.movierating.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class MovieRating {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "ReleaseDate")
    private String releaseDate;
    @XmlElement(name = "Rating")
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
