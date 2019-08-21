package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Encoder {

    String encode(List<MovieRating> movieRatings);

    OutputStream encodeStream(List<MovieRating> movieRatings, OutputStream outputStream) throws IOException;
}
