package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoders.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.encoders.JSONEncoder;
import nl.nigelvanhattum.bep.movierating.encode.encoders.XMLEncoder;
import org.junit.Assert;
import org.junit.Test;

public class EncoderFactoryTest {

    @Test
    public void testGetJSONEncoder() {
        Encoder encoder = EncoderFactory.getEncoder(EncoderType.JSON);
        Assert.assertEquals(JSONEncoder.class, encoder.getClass());
    }

    @Test
    public void testGetXMLEncoder() {
        Encoder encoder = EncoderFactory.getEncoder(EncoderType.XML);
        Assert.assertEquals(XMLEncoder.class, encoder.getClass());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInvalidEncoder() {
        Encoder encoder = EncoderFactory.getEncoder(EncoderType.NOTSUPPORTED);
    }
}
