package unit7task1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WritingThreadLock extends Thread {
    private int i;
    private ConcurrentTransactionManager manager;
    private String FILEPATH;
    private Lock lock = new ReentrantLock();

    public WritingThreadLock(ConcurrentTransactionManager manager, String FILEPATH, int i) {
        this.manager = manager;
        this.FILEPATH = FILEPATH;
        this.i = i;
    }

    @Override
    public void run() {

        try {
            lock.lock();
            manager.writeTransaction(FILEPATH, new Transaction(new Account("Thread " + i), new Account("Account " + i), i));
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

