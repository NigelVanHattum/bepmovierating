package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.Movie;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

public class XMLDecoder implements Decoder {
    @Override
    public List<Movie> decode(String input) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Movie.class);
            Unmarshaller m = context.createUnmarshaller();
            StreamSource source = new StreamSource(new StringReader(input));
            m.unmarshal(source, Movie.class).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<Movie> decodeFromStream(InputStreamReader reader) {
        throw new UnsupportedOperationException("This decoder is not yet implemented");
    }
}
