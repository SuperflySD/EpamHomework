package unit7task1;

public class Account {
    private String owner;
    private String sum;

    public Account(String owner, String sum) {
        this.owner = owner;
        this.sum = sum;
    }

    public Account(String owner) {
        this.owner = owner;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }


    @Override
    public String toString() {
        return "Account{owner=" + owner + ", sum=" + sum + "}";
    }
}
