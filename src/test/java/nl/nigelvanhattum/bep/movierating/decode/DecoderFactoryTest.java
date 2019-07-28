package nl.nigelvanhattum.bep.movierating.decode;

import nl.nigelvanhattum.bep.movierating.decode.decoder.Decoder;
import nl.nigelvanhattum.bep.movierating.decode.decoder.JSONDecoder;
import org.junit.Assert;
import org.junit.Test;

import java.beans.XMLDecoder;

public class DecoderFactoryTest {


    @Test
    public void testGetJSONEncoder() {
        Decoder decoder = DecoderFactory.getDecoder(DecoderType.JSON);
        Assert.assertEquals(JSONDecoder.class, decoder.getClass());
    }

    @Test
    public void testGetXMLEncoder() {
        Decoder decoder = DecoderFactory.getDecoder(DecoderType.XML);
        Assert.assertEquals(XMLDecoder.class, decoder.getClass());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInvalidEncoder() {
        Decoder decoder = DecoderFactory.getDecoder(DecoderType.NOTSUPPORTED);
    }
}
