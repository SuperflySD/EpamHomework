package task;

import org.junit.Test;
import unit7task1.JavaCodeBytesReader;

import java.util.Map;

import static org.junit.Assert.*;

public class JavaCodeCharsReaderTest {
   JavaCodeCharsReader jr = new JavaCodeCharsReader();
    private static final String FILETOREAD = "src\\main\\resources\\CrazyLogger(from unit3).java";
    private static final String FILETOWRITE =  "src\\main\\resources\\CrazyLogger(OVERWRITTEN).txt";

   @Test
    public void readJavaCode() throws Exception {
       System.out.println(jr.readJavaCode(FILETOREAD));

    }

    @Test
    public void detectJavaKeyWords() throws Exception {
        Map<String, Integer> map = jr.detectJavaKeyWords(jr.readJavaCode(FILETOREAD));
        assertTrue(map.get("private") == 2);

    }
    @Test
    public void writeJavaCode() throws Exception {
        Map<String, Integer> map = jr.detectJavaKeyWords(jr.readJavaCode(FILETOREAD));
        jr.writeJavaCode(FILETOWRITE, map.toString());

    }

}