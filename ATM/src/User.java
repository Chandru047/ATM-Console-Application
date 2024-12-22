import java.util.ArrayList;

public class User
{
    ArrayList user;
    User(ArrayList user)
    {
        this.user = user ;
    }
    void options()
    {
        System.out.println("Check balance \n Withdraw \n Deposit \n Change Pass \n View Transaction");
    }
}
