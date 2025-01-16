public class Admin extends Account
{
    private static final double PIN = 1920; // variable to store the default pin

    //Constructor
    public Admin(String id, String pass)
    {
        super(id, pass); // pass the id and pass to the Accounts
    }

    //Getter
    static double getPIN()
    {
        return PIN;
    }

}
