package nl.nigelvanhattum.bep.movierating.encode.encoders;

import nl.nigelvanhattum.bep.movierating.encode.model.Movie;

import java.io.OutputStreamWriter;
import java.util.List;

public interface Encoder {

    String encode(List<Movie> mvoies);

    boolean writeOut(List<Movie> movies, OutputStreamWriter writer);
}
