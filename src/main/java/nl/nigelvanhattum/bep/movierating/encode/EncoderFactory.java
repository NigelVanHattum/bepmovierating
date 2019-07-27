package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoders.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.encoders.JSONEncoder;
import nl.nigelvanhattum.bep.movierating.encode.encoders.XMLEncoder;

public class EncoderFactory {

    private  EncoderFactory(){}

    public static Encoder getEncoder(EncoderType type) {

        switch (type) {
            case JSON: return new JSONEncoder();
            case XML: return new XMLEncoder();
            default: throw new UnsupportedOperationException("This encoder is not yet inplemented");
        }
    }
}
