import java.util.ArrayList;
import java.util.Scanner;

public class Atm
{
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
                UserActions userAction = new UserActions();
                userAction.options();
            }
        }

    }
}
