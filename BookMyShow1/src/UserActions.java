import java.util.ArrayList;
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
        System.out.println("Account Created Successfully");
        user.setLocation(location);
        BookMyShow.getUserList().add(user);

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

    public static void displayMovie(String id)
    {
        ArrayList<MoviePOJO> theatresS = null;
        System.out.println("Movies Screening in your location .....");
        String location = "";
        for (User user  : BookMyShow.getUserList())
        {
            
            if (user.getId().equals(id))
            {
                
                location = user.getLocation();
                ArrayList<MoviePOJO> theatres = BookMyShow.getLocationAndMovie().get(location);
                theatresS = theatres ;

            }
        }

        if (!BookMyShow.getLocationAndTheatre().containsKey(location)) {
            System.out.println("Location not available.");
        }
        if (theatresS == null)
        {
            theatresS = new ArrayList<>();
        }
        for (MoviePOJO theatre : theatresS)
        {
            System.out.println(theatre.getMovieName());
        }
    }
}
