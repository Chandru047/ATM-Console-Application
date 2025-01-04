public class User extends Account
{
    private String balance;
    public User(String id , String pass)
    {
        super(id , pass);// pass the id and pass to the Accounts

    }

    public void setBalance(String balance)
    {
        this.balance = balance ;
    }

    public String getBalance()
    {
        return balance;
    }

}
