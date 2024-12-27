import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions extends Actions
{
    static Scanner in = new Scanner(System.in);
    String login(String id) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int index = -1;

            for (int i = 0; i < BookMyShow.getAdminList().size(); i++) {
                if (BookMyShow.getAdminList().get(i).getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (BookMyShow.getAdminList().get(index).getPass().equals(password)) {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions();
                    return "Success";
                }
                else
                {
                    System.out.println("Invalid password. Try again.");
                    return "Invalid";
                }
            }
            else
            {
                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = Integer.parseInt(in.nextLine());


                if (enteredPin != Admin.getPIN())
                {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return "Invalid";
                }

                Admin admin = new Admin();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                admin.setPass(newPassword);
                BookMyShow.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return "Created";
            }
        }
    }

    static void addLocation()
    {
        System.out.println("Enter the location:");
        String location = in.nextLine();
        if (BookMyShow.getlocationAndMovie().containsKey(location))
        {
            System.out.println("Location already exists");
        }
        else
        {
            BookMyShow.getlocationAndMovie().put(location , new ArrayList<Theatre>());
            System.out.println("Location added Successfully");
        }
    }

    static void addTheatre()
    {
        System.out.println("Enter the Location : ");
        String location = in.nextLine();
        if (!BookMyShow.getlocationAndMovie().containsKey(location))
        {
            System.out.println("Enter Does not exist");
            System.out.println("Please enter an valid Location");
            return;
        }

        System.out.println("Enter the name of the Theatre");
        String theatreName = in.nextLine();
        BookMyShow.getlocationAndMovie().put(location , new ArrayList<Theatre>());
    }
}
