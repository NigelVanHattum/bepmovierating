package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import java.io.OutputStream;
import java.util.List;

public class XMLEncoder implements Encoder{

    @Override
    public String encode(List<MovieRating> mvoies) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public OutputStream encodeStream(List<MovieRating> movieRatings, OutputStream outputStream) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
