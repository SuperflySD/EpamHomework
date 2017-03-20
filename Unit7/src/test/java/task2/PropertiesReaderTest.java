package task2;

import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

public class PropertiesReaderTest {

    private final String FILEPATH = "src\\main\\resources\\2.properties";

    @Test
    public void getValue() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(FILEPATH);
        assertEquals("1", propertiesReader.getValue("shirts"));
    }





}