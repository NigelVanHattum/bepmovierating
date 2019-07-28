package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public interface Encoder {

    String encode(List<Movie> movies);

    OutputStream encodeStream(List<Movie> movies, OutputStream outputStream);
}
