public class Transaction {
    private String type;
    private double amount;

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
