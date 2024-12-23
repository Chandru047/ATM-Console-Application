import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions extends Actions
{
    static Scanner in ;
    void options()
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {
            System.out.println(" Add User \n Delete User  \n View all User\n View all Transaction \n Deposit \n exit");
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("exit"))
            {
                break;
            }
            else if (choice.equalsIgnoreCase("Add User") || choice.equalsIgnoreCase("Add"))
            {
                addUser();
            }
            else if (choice.equalsIgnoreCase("Delete User") || choice.equalsIgnoreCase("Delete"))
            {
                deleteUser();
            }
            else if (choice.equalsIgnoreCase("View all User") || choice.equalsIgnoreCase("View"))
            {
                viewUser();
            }
            else if (choice.equalsIgnoreCase("View all Transaction") || choice.equalsIgnoreCase("Transaction"))
            {
                viewTransaction();
            }
            else if (choice.equalsIgnoreCase("deposit"))
            {
                deposit();
            }

        }

    }

    void login(String id)
    {
        while (true)
        {
            Scanner in = new Scanner(System.in);
            int index = -1 ;
            for (int i = 0; i < adminList.size(); i++)
            {
                if (adminList.get(i).getId().equals(id))
                {
                    index = i;
                    break;
                }
            }

            if (index != -1)
            {

                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (adminList.get(index).getPass().equals(password))
                {
                    System.out.println("Login successful!");
                    options();
                    break;
                }
                else
                {
                    System.out.println("Invalid password. Try again.");
                }
            }
            else
            {

                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = in.nextInt();
                in.nextLine();

                if (enteredPin != Admin.getPIN())
                {
                    System.out.println("Incorrect pin. Account creation failed.");
                    continue;
                }
                Admin admin = new Admin();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                ArrayList<String> temp = new ArrayList<>();
                admin.setPass(newPassword);
                adminList.add(admin);

                System.out.println("Account created successfully!");
                break;
            }
        }

    }
    void addUser()
    {
        Scanner in = new Scanner(System.in);
        int index = -1;
        System.out.println("Enter the ID of the new User");
        String newId = in.nextLine();
        if (newId.contains("us")) {


            for (int i = 0; i < userList.size(); i++)
            {
                if (userList.get(i).getId().equals(newId))
                {
                    index = i;
                    break;
                }
            }

            if (index != - 1)
            {
                System.out.println("User Already exists");
            }
            else
            {
                System.out.println("ID does not exist. Creating a new account...");

                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();


                System.out.println("Enter the Initial Balance: ");
                String initialBalance = in.nextLine();

                ArrayList<String> temp = new ArrayList<>();
                User user = new User();
                user.setId(newId);
                user.setPass(newPassword);
                user.setBalance(initialBalance);
                userList.add(user);
                System.out.println(userList);

                System.out.println("Account created successfully!");

            }
        }
        else
        {
            System.out.println("Invalid user");
        }
    }

    void deleteUser() {
        System.out.println("Enter the User ID to Delete");
        Scanner in = new Scanner(System.in);
        String dAccount = in.nextLine();
        boolean exists = false;
        int Index = -1;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(dAccount)) {
                exists = true;
                Index = i;
                break;
            }
        }

        if (exists) {
            userList.remove(Index);
            System.out.println("User Deleted");
        } else {
            System.out.println("User Does not exist");
        }

    }

    void viewUser() {
        for (User user : userList) {
            System.out.println(user.getId());
        }
    }

    void deposit()
    {
        System.out.println("Amount available:" +getBankBalance());
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the deposit amount: ");
        double bankBalance = in.nextDouble();
        System.out.println("Enter the no of 2000 note: ");
        String twoThousand = in.nextLine();
        note.add(twoThousand);
        System.out.println("Enter the no of 500 note: ");
        String fiveHundred = in.nextLine();
        note.add(fiveHundred);
        System.out.println("Enter the no of 50 note: ");
        String fifty = in.nextLine();
        note.add(fifty);

        double newBalance = bankBalance + getBankBalance();
        setBankBalance(newBalance);

    }

    void viewTransaction()
    {
//        user.transaction();
    }
}
