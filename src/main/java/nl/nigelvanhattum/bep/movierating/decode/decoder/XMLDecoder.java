package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

public class XMLDecoder implements Decoder {
    @Override
    public List<MovieRating> decode(String input) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(MovieRating.class);
            Unmarshaller m = context.createUnmarshaller();
            StreamSource source = new StreamSource(new StringReader(input));
            m.unmarshal(source, MovieRating.class).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<MovieRating> decodeFromStream(InputStreamReader reader) {
        throw new UnsupportedOperationException("This decoder is not yet implemented");
    }
}
