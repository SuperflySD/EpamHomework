package task2propertiesReader;

import org.junit.Test;
import task2propertiesreader.DirectoryNotFoundException;
import task2propertiesreader.KeyNotFoundException;
import task2propertiesreader.PropertiesReader;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PropertiesReaderTest {
    private final String DIRECTORYPATH = "src\\main\\resources";
    private final String FILENAME = "2";

    @Test
    public void getValue() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(DIRECTORYPATH, FILENAME);
        assertEquals("1", propertiesReader.getValue("shirts"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void wrongKey() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(DIRECTORYPATH, FILENAME);
        propertiesReader.getValue("hats");

    }

    @Test(expected = FileNotFoundException.class)
    public void wrongFile() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(DIRECTORYPATH, "nonexistentFile");
    }

    @Test(expected = DirectoryNotFoundException.class)
    public void wrongDirectory() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader("nonexistentDirectory", FILENAME);
    }

}