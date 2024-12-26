import Notes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Atm
{
    static double bankBalance ;
    private static ArrayList<Notes> note = new ArrayList<>
            (
            Arrays.asList
                    (
                    new TwoThousand("2000", 0),
                    new FiveHundred("500", 0),
                    new TwoHundred("200", 0),
                    new Hundred("100", 0)
                    )
            );
    private static final ArrayList<Admin> adminList = new ArrayList<>();
    private static final ArrayList<User> userList = new ArrayList<>();
    private static String id ;

    static ArrayList<Notes> getNote()
    {
        return note;
    }

    static void setNote(ArrayList<Notes> temp)
    {
        note = temp;
    }

    static ArrayList<Admin> getAdminList()
    {
        new TwoHundred("200", 10);
        return adminList;
    }

    static ArrayList<User> getUserList()
    {
        return userList;
    }
    static void setBankBalance(double balance)
    {
        bankBalance = balance;
    }
    static double getBankBalance()
    {
        return bankBalance;
    }
    void setId(String id)
    {
        this.id = id;
    }
    public static String getId()
    {
        return id;
    }
    void start() throws CloneNotSupportedException {
        while (true)
        {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the ID: ");
            String id = in.nextLine();
            setId(id);

            if (getId().contains("ad"))
            {
                Actions adminAction = new AdminActions();
                int result = adminAction.login(getId());
                switch (result) {
                    case 1:
                        // Successful login
                        adminOptions();
                        break;
                    case -1:
                        // Invalid password
                        break;
                    case 0:
                        // Account creation failed (wrong PIN)
                        break;
                    case 2:
                        // New account created successfully
                        break;
                    default:
                        // Handle unexpected result
                        break;
                }

            }
            else if (getId().contains("us"))
            {
                Actions userAction = new UserActions();
                int result = userAction.login(getId());
                switch (result) {
                    case 1:
                        userOptions();
                    case 0, -1:
                        break;
                }
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



    static void adminOptions()
    {
        AdminActions admin = new AdminActions();
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
                admin.addUser();
            }
            else if (choice.equalsIgnoreCase("Delete User") || choice.equalsIgnoreCase("Delete"))
            {
                admin.deleteUser();
            }
            else if (choice.equalsIgnoreCase("View all User") || choice.equalsIgnoreCase("View"))
            {
                admin.viewUser();
            }
            else if (choice.equalsIgnoreCase("View all Transaction") || choice.equalsIgnoreCase("Transaction"))
            {
                admin.viewTransaction();
            }
            else if (choice.equalsIgnoreCase("deposit"))
            {
                admin.deposit(getId());
            }

        }

    }
    static void userOptions() throws CloneNotSupportedException {
        while (true)
        {
            UserActions user = new UserActions();
            Scanner in = new Scanner(System.in);
            System.out.println("Check balance \n Withdraw \n Deposit \n Change Pass \n View Transaction \n exit");
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("Check Balance") || choice.equalsIgnoreCase("Balance")) {
                user.balance(getId() );
            }
            else if (choice.equalsIgnoreCase("Withdraw")) {
                user.withdrawCash(getId());
            }
            else if (choice.equalsIgnoreCase("exit"))
            {

                break;
            }
            else if (choice.equalsIgnoreCase("Deposit"))
            {
                user.deposit(getId());
            }
            else if (choice.equalsIgnoreCase("Change password")|| choice.equalsIgnoreCase("change"))
            {
                user.changePass(getId());
            } else if (choice.equalsIgnoreCase("transaction" )|| choice.equalsIgnoreCase("Transaction history"))
            {
                user.viewTransactions(getId());
            }
        }

    }
}
