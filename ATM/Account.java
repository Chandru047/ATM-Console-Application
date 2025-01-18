package ATM;

import java.util.ArrayList;

public class Account
{
    private String id; // variable to store the id
    private String pass; // variable to store the pass
    private  ArrayList<Transaction> transactionList; // ArrayList to store the Transactions

    //Constructor
    public Account(String id , String pass)
    {
        this.id = id ;
        this.pass = pass ;
        this.transactionList = new ArrayList<>();
    }

    //Getters and Setters

    public String getId() {
        return id;
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
