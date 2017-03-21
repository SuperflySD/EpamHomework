package unit7task1;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConcurrentTransactionManagerTest {
    ConcurrentTransactionManager manager = new ConcurrentTransactionManager();
    private final String FILEPATH = "src\\main\\resources\\1.txt";
    ExecutorService ex = Executors.newCachedThreadPool();

    @Test
    public void getTransactionsParallelTest() throws Exception {
        List<Transaction> list = (List) ex.submit(new ReadingThread(manager, FILEPATH)).get();
        assertEquals(list.get(0).getAccountFrom().getOwner(), ("John"));
    }

    @Test
    public void writeTransactionTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            new WritingThread(manager, FILEPATH, i).start();
            new WritingThreadLock(manager, FILEPATH, i).start();
        }
        List<Transaction> list = (List) ex.submit(new ReadingThread(manager, FILEPATH)).get();
        assertTrue(list.contains(new Transaction(new Account("Thread " + 1), new Account("Account " + 1), 1)));

    }
}


