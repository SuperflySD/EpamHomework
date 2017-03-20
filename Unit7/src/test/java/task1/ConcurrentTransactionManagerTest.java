package task1;

import org.junit.Test;
import task1.ConcurrentTransactionManager;
import task1.WritingThread;
import task1.Transaction;

import java.util.List;

public class ConcurrentTransactionManagerTest {
    ConcurrentTransactionManager manager = new ConcurrentTransactionManager();
    private final String FILEPATH = "src\\main\\resources\\1.txt";

    @Test
    public void getTransactionsParallelTest() throws Exception {
        new ReadingThread(manager, FILEPATH).start();

    }

    @Test
    public void writeTransactionTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            new WritingThread(manager,FILEPATH,i).start();
        }
    }
}


