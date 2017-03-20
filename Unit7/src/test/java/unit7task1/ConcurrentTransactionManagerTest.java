package unit7task1;

import org.junit.Test;

public class ConcurrentTransactionManagerTest {
    ConcurrentTransactionManager manager = new ConcurrentTransactionManager();
    private final String FILEPATH = "src\\main\\resources\\1.txt";

    @Test
    public void getTransactionsParallelTest() throws Exception {
        ReadingThread readingThread = new ReadingThread(manager, FILEPATH);
        readingThread.start();
        readingThread.join();

    }

    @Test
    public void writeTransactionTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            new WritingThread(manager,FILEPATH,i).start();
            new WritingThreadLock(manager, FILEPATH,i).start();
        }
    }
}


