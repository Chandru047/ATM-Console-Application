import java.util.Scanner;

public class UserActions extends Actions
{
    static Scanner in = new Scanner(System.in);
    static void register(String id)
    {
        User user = new User();
        user.setId(id);
        System.out.println("Enter the Password:");
        String password = in.nextLine();
        user.setPass(password);
        System.out.println("Enter your Name:");
        String name = in.nextLine();
        user.setname(name);
        System.out.println("Enter your Location:");
        String location = in.nextLine();
        user.setLocation(location);

    }

    String login(String id)
    {
        for (int i = 0 ; i< BookMyShow.getUserList().size() ; i++)
        {
            if (BookMyShow.getUserList().get(i).getId().equals(id))
            {
                System.out.println("Enter the password:");
                String password = in.nextLine();
                if (BookMyShow.getUserList().get(i).getPass().equals(password))
                {
                    return "Success";
                }
            }
        }
        System.out.println("User does not exist ....    Creating new account");
        return "not_Exist";
    }
}
