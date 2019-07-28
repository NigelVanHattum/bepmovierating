package nl.nigelvanhattum.bep.movierating.encode.encoder;

import com.google.gson.Gson;
import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONEncoder implements Encoder {
    private Logger logger;

    public JSONEncoder() {
        logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public String encode(List<Movie> movies) {
        Gson gson = new Gson();
        return gson.toJson(movies);
    }

    @Override
    public OutputStream encodeStream(List<Movie> movies, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        Gson gson = new Gson();
        gson.toJson(movies, writer);
        writer.close();
        return outputStream;
    }
}
