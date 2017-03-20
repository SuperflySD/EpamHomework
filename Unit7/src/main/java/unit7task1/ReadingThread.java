package unit7task1;

import java.io.IOException;
import java.util.List;

public class ReadingThread extends Thread {
    private ConcurrentTransactionManager manager;
    private final String FILEPATH;

    public ReadingThread(ConcurrentTransactionManager manager, String FILEPATH) {
        this.manager = manager;
        this.FILEPATH = FILEPATH;
    }

    @Override
    public void run() {
        synchronized (manager) {
            try {
                List<Transaction> accList = manager.getTransactions(FILEPATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}