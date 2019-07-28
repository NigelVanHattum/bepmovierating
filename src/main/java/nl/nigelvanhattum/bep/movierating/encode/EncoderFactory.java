package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoder.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.JSONEncoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.XMLEncoder;

public class EncoderFactory {

    private  EncoderFactory(){}

    public static Encoder getEncoder(EncoderType type) {

        switch (type) {
            case JSON: return new JSONEncoder();
            case XML: throw new UnsupportedOperationException("This encoder is not yet inplemented");//return new XMLEncoder();
            default: throw new UnsupportedOperationException("This encoder is not yet inplemented");
        }
    }
}
