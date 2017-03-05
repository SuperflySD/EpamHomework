package javase.unit3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrazyLoggerTest {
    CrazyLogger logger = new CrazyLogger();
    {
        logger.write(Level.Error, "First error message");
        logger.write(Level.Error, "Second error message 2");
        logger.write(Level.Info,  "Single info message");
        logger.write(Level.Info,  "");
    }

    @Test
    public void writeTest() throws Exception {
        assertEquals(logger.countLogs(),4);
    }

    @Test
    public void printTestByLevel() throws Exception {
        logger.printOnConsole(Level.Error);

    }

    @Test
    public void printTestByMessage() throws Exception {
        logger.printOnConsole("2");
        logger.printOnConsole(null,null);

    }
    @Test
    public void printTestByLevelMessage() throws Exception {
        logger.printOnConsole(Level.Error,"Second");

    }

}