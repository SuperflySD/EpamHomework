package unit7task1;

public class WritingThread extends Thread {
    private int i;
    private ConcurrentTransactionManager manager;
    private String FILEPATH;

    public WritingThread(ConcurrentTransactionManager manager, String FILEPATH, int i) {
        this.manager=manager;
        this.FILEPATH=FILEPATH;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            manager.writeTransaction(FILEPATH, new Transaction(new Account("Thread " + i), new Account("Account "+i), i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}