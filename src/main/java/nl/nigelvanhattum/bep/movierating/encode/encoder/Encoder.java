package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Encoder {

    String encode(List<MovieRating> movieRatings) throws JAXBException;

    OutputStream encodeStream(List<MovieRating> movieRatings, OutputStream outputStream) throws IOException, JAXBException;
}
