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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLDecoder implements Decoder {
    Logger logger = Logger.getLogger(XMLDecoder.class.getName());
    @Override
    public List<MovieRating> decode(String input) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(MovieRatings.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource source = new StreamSource(new StringReader(input));
            MovieRatings container = unmarshaller.unmarshal(source, MovieRatings.class).getValue();
            return container.getMovieRatings();
        } catch (JAXBException jaxE) {
            logger.log(Level.SEVERE, "Error during decoding, skipping..");
            logger.log(Level.SEVERE, jaxE.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<MovieRating> decodeFromStream(InputStreamReader reader) {
        JAXBContext context;
        XMLStreamReader xmlStreamReader= null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        try {
            xmlStreamReader = factory.createXMLStreamReader(reader);
        } catch (XMLStreamException e) {
            logger.log(Level.SEVERE, "Error during decoding, skipping...");
            logger.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
        try {
            context = JAXBContext.newInstance(MovieRatings.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MovieRatings container = unmarshaller.unmarshal(xmlStreamReader, MovieRatings.class).getValue();
            return container.getMovieRatings();
        } catch (JAXBException jaxE) {
            logger.log(Level.SEVERE, "Error during decoding, skipping...");
            logger.log(Level.SEVERE, () -> jaxE.getMessage());
            return new ArrayList<>();
        }
    }
}
