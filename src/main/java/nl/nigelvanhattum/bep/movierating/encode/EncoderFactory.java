package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoders.JSONEncoder;

public class EncoderFactory {

    public static Encoder getEncoder(EncoderType type) {

        switch (type) {
            case JSON: return new JSONEncoder();
            default: throw new UnsupportedOperationException("This encoder is not yet inplemented");
        }
    }
}
