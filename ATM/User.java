import java.util.ArrayList;

public class User
{
    private String id ;
    private String pass;
    private String balance;
    private final ArrayList<Transaction> transactionList;

    public User() {
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
    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public void setBalance(String balance)
    {
        this.balance = balance ;
    }

    public String getBalance()
    {
        return balance;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }


    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }
}
