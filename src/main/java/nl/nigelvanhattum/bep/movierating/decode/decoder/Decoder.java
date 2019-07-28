package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.InputStreamReader;
import java.util.List;

public interface Decoder {

    List<Movie> decode(String input);

    List<Movie> decodeFromStream(InputStreamReader reader);
}
