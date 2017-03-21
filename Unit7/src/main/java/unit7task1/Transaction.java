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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (transactionSum != that.transactionSum) return false;
        if (!accFrom.equals(that.accFrom)) return false;
        return accTo.equals(that.accTo);
    }

    @Override
    public int hashCode() {
        int result = accFrom.hashCode();
        result = 31 * result + accTo.hashCode();
        result = 31 * result + transactionSum;
        return result;
    }
}



