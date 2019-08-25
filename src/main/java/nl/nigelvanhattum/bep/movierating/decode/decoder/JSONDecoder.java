package nl.nigelvanhattum.bep.movierating.decode.decoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.nigelvanhattum.bep.movierating.model.MovieRating;

import java.io.InputStreamReader;
import java.util.List;

public class JSONDecoder implements Decoder{


    @Override
    public List<MovieRating> decode(String input) {
        Gson gson = new Gson();
        return gson.fromJson(input, new TypeToken<List<MovieRating>>(){}.getType());
    }

    @Override
    public List<MovieRating> decodeFromStream(InputStreamReader reader) {
        Gson gson = new Gson();
        return gson.fromJson(reader, new TypeToken<List<MovieRating>>(){}.getType());
    }
}
