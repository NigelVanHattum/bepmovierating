package nl.nigelvanhattum.bep.movierating.decode.decoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import nl.nigelvanhattum.bep.movierating.model.MovieRatings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class XMLDecoder implements Decoder {
    Logger logger = Logger.getLogger(XMLDecoder.class.getName());

    @Override
    public List<MovieRating> decode(String input) throws JAXBException {
        JAXBContext context;

        context = JAXBContext.newInstance(MovieRatings.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StreamSource source = new StreamSource(new StringReader(input));
        MovieRatings container = unmarshaller.unmarshal(source, MovieRatings.class).getValue();
        return container.getMovieRatings();
    }

    @Override
    public List<MovieRating> decodeFromStream(InputStreamReader reader) throws JAXBException, XMLStreamException {
        JAXBContext context;
        XMLStreamReader xmlStreamReader = null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);

        xmlStreamReader = factory.createXMLStreamReader(reader);
        context = JAXBContext.newInstance(MovieRatings.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        MovieRatings container = unmarshaller.unmarshal(xmlStreamReader, MovieRatings.class).getValue();
        return container.getMovieRatings();
    }
}
