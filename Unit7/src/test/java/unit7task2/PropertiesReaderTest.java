package unit7task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertiesReaderTest {
    private final String FILEPATH = "src\\main\\resources\\2.properties";

    @Test
    public void getValue() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(FILEPATH);
        for (int i = 0; i < 1000; i++) {
            new PropertyReadingThread(propertiesReader, "1").start();
            new PropertyReadingThread(propertiesReader, "2").start();
            new PropertyReadingThread(propertiesReader, "3").start();
            new PropertyReadingThread(propertiesReader, "4").start();
            new PropertyReadingThread(propertiesReader, "4").start();
            new PropertyReadingThread(propertiesReader, "4").start();
            assertTrue(propertiesReader.getReadersSameTime()<2);
        }
    }


}