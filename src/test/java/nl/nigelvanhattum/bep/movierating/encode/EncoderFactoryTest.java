package nl.nigelvanhattum.bep.movierating.encode;

import nl.nigelvanhattum.bep.movierating.encode.encoder.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.JSONEncoder;
import nl.nigelvanhattum.bep.movierating.encode.encoder.XMLEncoder;
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

    @Test
    public void testGetJSONStringEncoder() {
        Encoder encoder = EncoderFactory.getEncoder("json");
        Assert.assertEquals(JSONEncoder.class, encoder.getClass());
    }

    @Test
    public void testGetXMLStringEncoder() {
        Encoder encoder = EncoderFactory.getEncoder("xml");
        Assert.assertEquals(XMLEncoder.class, encoder.getClass());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInvalidStringEncoder() {
        Encoder encoder = EncoderFactory.getEncoder("wut?");
    }
}
