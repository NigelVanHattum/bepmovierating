package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.InputStreamReader;
import java.util.List;

public interface Decoder {

    List<MovieRating> decode(String input) throws JAXBException;

    List<MovieRating> decodeFromStream(InputStreamReader reader) throws JAXBException, XMLStreamException;
}
