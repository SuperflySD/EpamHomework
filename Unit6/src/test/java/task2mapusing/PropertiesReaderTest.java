package task2mapusing;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;


public class PropertiesReaderTest {
    private final String FILEPATH = "src\\main\\resources\\2.properties";

    @Test
    public void getValue() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(FILEPATH);
        assertEquals(propertiesReader.addDuplicateKeyToMap("5",
                "Question: how does map work if duplicate key is put?"),
                "Answer - it erases previous value and returns the old one by the put method");
    }
}
