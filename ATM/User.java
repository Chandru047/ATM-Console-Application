package ATM;

public class User extends Account
{
    private String balance; // variable to store the balance of the user

    //Constructor
    public User(String id , String pass)
    {
        super(id , pass);// pass the id and pass to the Accounts

    }

    //Getters and Setters

    public void setBalance(String balance)
    {
        this.balance = balance ;
    }

    public String getBalance()
    {
        return balance;
    }

}
