package unit7task1;

public class Transaction {
    private Account accFrom;
    private Account accTo;
    private int transactionSum;

    public Transaction(Account accFrom, Account accTo, int transactionSum) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.transactionSum = transactionSum;
    }


    public Account getAccountFrom() {
        return accFrom;
    }

    public Account getAccountTo() {
        return accTo;
    }

    public int getTransactionSum() {
        return transactionSum;
    }

    @Override
    public String toString() {
        return "Transaction{accFrom=" + accFrom + ", accTo=" + accTo + ", transactionSum=" + transactionSum + '}';
    }
}



