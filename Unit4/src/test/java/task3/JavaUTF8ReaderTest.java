package task3;

import org.junit.Test;

public class JavaUTF8ReaderTest {
    JavaUTF8Reader jr = new JavaUTF8Reader();
    private static final String FILETOREAD = "src\\main\\resources\\CrazyLogger(from unit3).java";
    private static final String FILETOWRITE =  "src\\main\\resources\\CrazyLogger(UTF-16).txt";

    @Test
    public void readUTF8() throws Exception {
        System.out.println(jr.readUTF8(FILETOREAD));

    }

    @Test
    public void writeJavaCode() throws Exception {

        jr.writeUTF16(FILETOWRITE, jr.readUTF8(FILETOREAD));

    }

}