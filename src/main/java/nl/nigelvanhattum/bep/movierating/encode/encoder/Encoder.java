package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.OutputStreamWriter;
import java.util.List;

public interface Encoder {

    String encode(List<Movie> mvoies);

    boolean writeOut(List<Movie> movies, OutputStreamWriter writer);
}
