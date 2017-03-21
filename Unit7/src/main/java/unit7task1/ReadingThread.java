package unit7task1;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class ReadingThread implements Callable {
    private ConcurrentTransactionManager manager;
    private final String FILEPATH;

    public ReadingThread(ConcurrentTransactionManager manager, String FILEPATH) {
        this.manager = manager;
        this.FILEPATH = FILEPATH;
    }

    @Override
    public List<Transaction> call() throws Exception {
        List<Transaction> accList = null;
        synchronized (manager) {
            try {
               accList = manager.getTransactions(FILEPATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accList;
    }
}