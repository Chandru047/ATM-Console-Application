public class Admin extends Account
{
    private static final double PIN = 1920;

    public Admin(String id, String pass)
    {
        super(id, pass); // pass the id and pass to the Accounts
    }

    static double getPIN()
    {
        return PIN;
    }

}
