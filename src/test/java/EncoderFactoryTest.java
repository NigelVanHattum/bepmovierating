import nl.nigelvanhattum.bep.movierating.encode.Encoder;
import nl.nigelvanhattum.bep.movierating.encode.EncoderFactory;
import nl.nigelvanhattum.bep.movierating.encode.EncoderType;
import nl.nigelvanhattum.bep.movierating.encode.encoders.JSONEncoder;
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
        //Encoder encoder = EncoderFactory.getEncoder(EncoderType.XML);
        //Assert.assertEquals(XMLEncoder.class, encoder.getClass());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInvalidEncoder() {
        Encoder encoder = EncoderFactory.getEncoder(EncoderType.NOTSUPPORTED);
    }
}
