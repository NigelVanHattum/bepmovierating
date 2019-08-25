package nl.nigelvanhattum.bep.movierating.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "MovieRatings")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieRatings {

    @XmlElement(name = "MovieRating")
    List<MovieRating> movies = new ArrayList<>();

    public void setMovieRatings(List<MovieRating> movies) {
        this.movies = movies;
    }

    public List<MovieRating> getMovieRatings() {
        return movies;
    }
}
