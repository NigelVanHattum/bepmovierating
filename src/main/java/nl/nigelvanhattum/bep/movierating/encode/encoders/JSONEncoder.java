package nl.nigelvanhattum.bep.movierating.encode.encoders;

import com.google.gson.Gson;
import nl.nigelvanhattum.bep.movierating.encode.model.Movie;

import java.io.OutputStreamWriter;
import java.util.List;

public class JSONEncoder implements Encoder {
    @Override
    public String encode(List<Movie> movies) {
        Gson gson = new Gson();
        return gson.toJson(movies);
    }

    @Override
    public boolean writeOut(List<Movie> movies, OutputStreamWriter writer) {
        return false;
    }
}
