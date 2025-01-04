import java.util.ArrayList;

public class Account
{
    private String id;
    private String pass;
    private final ArrayList<Transaction> transactionList;

    public Account(String id , String pass)
    {
        this.id = id ;
        this.pass = pass ;
        this.transactionList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }
}
