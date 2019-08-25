package nl.nigelvanhattum.bep.movierating.encode.encoder;

import com.google.gson.Gson;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class JSONEncoder implements Encoder {

    @Override
    public String encode(List<MovieRating> movieRatings) {
        Gson gson = new Gson();
        return gson.toJson(movieRatings);
    }

    @Override
    public OutputStream encodeStream(List<MovieRating> movieRatings, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        Gson gson = new Gson();
        gson.toJson(movieRatings, writer);
        writer.close();
        return outputStream;
    }
}
