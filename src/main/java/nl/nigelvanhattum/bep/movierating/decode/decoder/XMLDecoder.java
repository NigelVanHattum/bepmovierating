package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.InputStreamReader;
import java.util.List;

public class XMLDecoder implements Decoder {
    @Override
    public List<Movie> decode(String input) {
        throw new UnsupportedOperationException("This decoder is not yet implemented");
    }

    @Override
    public List<Movie> decodeFromStream(InputStreamReader reader) {
        throw new UnsupportedOperationException("This decoder is not yet implemented");
    }
}
