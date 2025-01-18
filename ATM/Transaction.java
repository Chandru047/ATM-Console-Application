package ATM;

public class Transaction {
    private String type; // variable to store the type of the transaction ( eg:deposit or withdraw )
    private double amount;// variable to store the amount

    //Constructor
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }



    @Override
    public String toString() // override the toString Method in Object
    {
        return "Transaction [Type=" + type + ", Amount=" + amount + "]";
    }
}
