package task1;

import java.io.IOException;
import java.util.List;

public class ReadingThread extends Thread {
    private ConcurrentTransactionManager manager;
    private String FILEPATH;

    public ReadingThread(ConcurrentTransactionManager manager, String FILEPATH) {
        this.manager = manager;
        this.FILEPATH = FILEPATH;
    }

    @Override
    public void run() {

        List<Transaction> accList = null;
        try {
            accList = manager.getTransactionsParallel(FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Transaction s : accList) {
                System.out.println(s);

    }
}}