import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BookMyShow
{
    static Scanner in = new Scanner(System.in);
    private static final ArrayList<Admin> adminList= new ArrayList<>();
    private static final ArrayList<User> userList = new ArrayList<>();
    private static final HashMap<String , ArrayList<Theatre>> locationAndTheatre = new HashMap<>();


    static HashMap<String, ArrayList<Theatre>> getlocationAndTheatre()
    {
        return locationAndTheatre;
    }

    static ArrayList<Admin> getAdminList()
    {
        return adminList;
    }

    static ArrayList<User> getUserList()
    {
        return userList;
    }

    void start()
    {
        System.out.println("----------------------------------------");
        System.out.println("-        Welcome to BookMyShow         -");
        System.out.println("----------------------------------------");

        //Initial Input
        while (true)
        {
            System.out.println("Enter the Username: ");
            String input = in.nextLine();

            if (input.contains("ad"))
            {
                Actions adminActions = new AdminActions();
                String result = adminActions.login(input);
                switch (result)
                {
                    case "Success":
                        adminOptions();
                        break;
                    case "Invalid", "Created":
                        break;
                }

            }
            else if (input.equals("exit"))
            {
                System.out.println("Exiting.........");
                break;
            }
            else
            {
                Actions userActions = new UserActions();
                String result = userActions.login(input);
                switch (result)
                {
                    case "Success":
                        userOptions();
                        break;
                    case "not_Exist":
                        UserActions.register(input);
                        break;
                }
            }
        }


    }

    public static void adminOptions()
    {
       loop: while (true)
        {
            System.out.println("-------------------------");
            System.out.println("       Admin Options     ");
            System.out.println("-------------------------");
            System.out.println("Enter your choice: \n1.Add Location \n2.Add Theatre \n3.Add Movie \n4.Exit");
            String choice = in.nextLine();
            switch (choice)
            {
                case "1" :
                    AdminActions.addLocation();
                    break;
                case "2" :
                    AdminActions.addTheatre();
                    break;
                case "3" :
                    AdminActions.addMovie();
                    break ;
                case "4" :
                    break loop;



            }
        }


    }

    public static void userOptions()
    {
        System.out.println("User Options ......");
    }
}
