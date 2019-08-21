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
    List<MovieRating> movieRatings = new ArrayList<>();

    public MovieRatings(){}

    public void setMovieRatings(List<MovieRating> movies) {
        this.movieRatings = movies;
    }

    public List<MovieRating> getMovieRatings() {
        return movieRatings;
    }
}
