package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.OutputStreamWriter;
import java.util.List;

public class XMLEncoder implements Encoder{

    @Override
    public String encode(List<Movie> mvoies) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean writeOut(List<Movie> movies, OutputStreamWriter writer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}