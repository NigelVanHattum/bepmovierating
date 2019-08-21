package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import java.io.InputStreamReader;
import java.util.List;

public interface Decoder {

    List<MovieRating> decode(String input);

    List<MovieRating> decodeFromStream(InputStreamReader reader);
}
