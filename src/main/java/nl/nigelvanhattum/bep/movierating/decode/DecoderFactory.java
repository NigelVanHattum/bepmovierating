package nl.nigelvanhattum.bep.movierating.decode;

import nl.nigelvanhattum.bep.movierating.decode.decoder.Decoder;
import nl.nigelvanhattum.bep.movierating.decode.decoder.JSONDecoder;

public class DecoderFactory {

    private DecoderFactory(){}

    public static Decoder getDecoder(DecoderType type) {

        switch (type) {
            case JSON: return new JSONDecoder();
            default: throw new UnsupportedOperationException("This encoder is not yet inplemented");
        }
    }
}
