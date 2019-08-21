package nl.nigelvanhattum.bep.movierating.encode.encoder;

import nl.nigelvanhattum.bep.movierating.model.MovieRating;
import nl.nigelvanhattum.bep.movierating.model.MovieRatings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLEncoder implements Encoder{

    static Logger logger = Logger.getLogger(XMLEncoder.class.getName());
    @Override
    public String encode(List<MovieRating> movies) {
        MovieRatings root = new MovieRatings();
        root.setMovieRatings(movies);

        try {
            JAXBContext context = JAXBContext.newInstance(MovieRatings.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        }
        return null;
    }

    @Override
    public OutputStream encodeStream(List<MovieRating> movieRatings, OutputStream outputStream) {
        MovieRatings root = new MovieRatings();
        root.setMovieRatings(movieRatings);

        try {
            JAXBContext context = JAXBContext.newInstance(MovieRatings.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(root, outputStream);
            return outputStream;
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, "Error during export, exiting now...");
            System.exit(1);
        }
        return null;
    }
}
