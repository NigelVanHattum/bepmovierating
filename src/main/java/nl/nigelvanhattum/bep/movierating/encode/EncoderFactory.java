package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoder.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.JSONEncoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.XMLEncoder;

public class EncoderFactory {

    private  EncoderFactory(){}

    public static Encoder getEncoder(EncoderType type) {

        switch (type) {
            case JSON: return new JSONEncoder();
            case XML: return new XMLEncoder();
            default: throw new UnsupportedOperationException(String.format("%s encoder is not yet inplemented", type));
        }
    }

    public static Encoder getEncoder(String type) {

        switch (type.toUpperCase()) {
            case "JSON": return new JSONEncoder();
            case "XML": return new XMLEncoder();
            default: throw new UnsupportedOperationException(String.format("%s encoder is not yet inplemented", type));
        }
    }
}
