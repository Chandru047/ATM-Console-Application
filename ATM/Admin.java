import java.util.ArrayList;

public class Admin {
    private static final double PIN = 1920;
    private String id ;
    private String pass;
    private final ArrayList<Transaction> transactionList;

    public Admin() {
        this.transactionList = new ArrayList<>();
    }

    static double getPIN(){
        return PIN;
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

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }


    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }





}
