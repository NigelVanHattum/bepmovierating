package nl.nigelvanhattum.bep.movierating.encode;

public class EncoderFactory {

    public static Encoder getEncoder(EncoderType type) {

        switch (type) {
            case JSON: return new JSONEncoder();
            default: throw new UnsupportedOperationException("This enoder is not yet inplemented");
        }
    }
}
