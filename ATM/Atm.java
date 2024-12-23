import java.util.ArrayList;
import java.util.Scanner;

public class Atm
{
    static double bankBalance ;
    ArrayList<String> note = new ArrayList<>();
    static void setBankBalance(double balance)
    {
        bankBalance = balance;
    }
    static double getBankBalance()
    {
        return bankBalance;
    }
    static ArrayList<Admin> adminList = new ArrayList<>();
    static ArrayList<User> userList = new ArrayList<>();
    void start()
    {
        while (true)
        {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the ID: ");
            String id = in.nextLine();

            if (id.contains("ad"))
            {
                Actions adminAction = new AdminActions();
                adminAction.login(id);
            }
            else if (id.contains("us"))
            {
                Actions userAction = new UserActions();
                userAction.login(id);
            }
            else if(id.contains("exit"))
            {
                break;
            }
            else
            {
                System.out.println("Invalid Choice !");
            }
        }

    }
}
