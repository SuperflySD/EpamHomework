package unit7task1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConcurrentTransactionManager {

    public List<Transaction> getTransactionsParallel(String filePath) throws IOException {
        Map<String, Object> results = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            return bf.lines().filter(x -> x.contains("Transaction")).
                    map(x -> {
                        Thread t1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int start = x.indexOf("\"Owner\":") + 9;
                                int end = x.indexOf("\"", start);
                                String owner = x.substring(start, end);
                                results.put("accFrom", new Account(owner));
                            }
                        });

                        Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int start = x.lastIndexOf("\"Owner\":") + 9;
                                int end = x.indexOf("\"", start);
                                String owner = x.substring(start, end);
                                results.put("accTo", new Account(owner));
                            }
                        });

                        Thread t3 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int start = x.indexOf("\"TransactionSum\":") + 17;
                                int end = x.indexOf(" ", start);
                                results.put("sum", Integer.parseInt(x.substring(start, end)));
                            }
                        });
                        try {
                            t1.start();
                            t2.start();
                            t3.start();
                            t1.join();
                            t2.join();
                            t3.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        return new Transaction((Account) results.get("accFrom"), (Account) results.get("accTo"), (int) results.get("sum"));
                    }).collect(Collectors.toList());
        }
    }

    public void writeTransaction(String filePath, Transaction transaction) throws InterruptedException {
        String stringToWrite = "\n{\"Transaction\":  {  \"AccountFrom\":{ \"Owner\":\"" + transaction.getAccountFrom().getOwner() +
                "\" }, \"AccountTo\":{ \"Owner\":\"" + transaction.getAccountTo().getOwner() +
                "\" }, \"TransactionSum\":" + transaction.getTransactionSum() + " }}";

        try (FileWriter fw = new FileWriter(filePath, true)) {
            try (BufferedWriter bf = new BufferedWriter(fw)) {
                bf.write(stringToWrite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


