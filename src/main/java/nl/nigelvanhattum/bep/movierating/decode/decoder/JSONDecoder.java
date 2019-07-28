package nl.nigelvanhattum.bep.movierating.decode.decoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.nigelvanhattum.bep.movierating.model.Movie;

import java.io.InputStreamReader;
import java.util.List;

public class JSONDecoder implements Decoder{


    @Override
    public List<Movie> decode(String input) {
        Gson gson = new Gson();
        List<Movie> movies = gson.fromJson(input, new TypeToken<List<Movie>>(){}.getType());
        return movies;
    }

    @Override
    public List<Movie> decodeFromStream(InputStreamReader reader) {
        Gson gson = new Gson();
        List<Movie> movies = gson.fromJson(reader, new TypeToken<List<Movie>>(){}.getType());
        return movies;
    }
}
