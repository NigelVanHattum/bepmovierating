package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.model.Movie;

import java.util.List;

public interface Encoder {

    Object encode(List<Movie> mvoies);
}
