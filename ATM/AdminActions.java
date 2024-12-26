import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions extends Actions
{
    int login(String id) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int index = -1;

            for (int i = 0; i < Atm.getAdminList().size(); i++) {
                if (Atm.getAdminList().get(i).getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (Atm.getAdminList().get(index).getPass().equals(password)) {
                    System.out.println("Login successful!");
                    Atm.adminOptions();
                    return 1;
                } else {
                    System.out.println("Invalid password. Try again.");
                    return -1;
                }
            } else {
                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = in.nextInt();
                in.nextLine();

                if (enteredPin != Admin.getPIN()) {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return 0;
                }

                Admin admin = new Admin();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                admin.setPass(newPassword);
                Atm.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return 2;
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


            for (int i = 0; i < Atm.getUserList().size(); i++)
            {
                if (Atm.getUserList().get(i).getId().equals(newId))
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

                User user = new User();
                user.setId(newId);
                user.setPass(newPassword);
                user.setBalance(initialBalance);
                Atm.getUserList().add(user);
                System.out.println(Atm.getUserList());

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

        for (int i = 0; i < Atm.getUserList().size(); i++) {
            if (Atm.getUserList().get(i).getId().equals(dAccount)) {
                exists = true;
                Index = i;
                break;
            }
        }

        if (exists) {
            Atm.getUserList().remove(Index);
            System.out.println("User Deleted");
        } else {
            System.out.println("User Does not exist");
        }

    }

    void viewUser() {
        for (User user : Atm.getUserList()) {
            System.out.println(user.getId());
        }
    }

    void deposit(String id)
    {
        while (true)
        {

            System.out.println("Amount available:" +Atm.getBankBalance());
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the deposit amount: ");
            String bankBalanceD = in.nextLine();
            double bankBalance = Double.parseDouble(bankBalanceD);
            System.out.println("Enter the no of 2000 note: ");
            double twoThousand =Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 500 note: ");
            double fiveHundred =Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 200 note: ");
            double twoHundred =Double.parseDouble(in.nextLine());


            System.out.println("Enter the no of 100 note: ");
            double hundred =Double.parseDouble(in.nextLine());

            double noteBalance = twoThousand*2000 + fiveHundred*500 + twoHundred*200 + hundred*100;
            if (bankBalance == noteBalance)
            {
                double oldCount;
                double newCount;
                for (Notes notesHere:Atm.getNote())
                {
                    if (notesHere.getNote().equals("2000"))
                    {
                        oldCount = notesHere.getCount();
                        newCount = oldCount+twoThousand;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("500"))
                    {
                        oldCount = notesHere.getCount();
                        newCount = oldCount+fiveHundred;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("200"))
                    {
                        oldCount = notesHere.getCount();
                        newCount = oldCount+twoHundred;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("100"))
                    {
                        oldCount = notesHere.getCount();
                        newCount = oldCount+hundred;
                        notesHere.setCount(newCount);
                    }
                }

                double newBalance = bankBalance + Atm.getBankBalance();
                Atm.setBankBalance(newBalance);
                System.out.println("Deposit Sucessfull");
                addTransactionToAdmin(bankBalance , id );
                break;
            }
            else
            {
                System.out.println("Count doesn't match try again");
                break;

            }
        }

    }

    void viewTransaction()
    {
        System.out.println("Enter option (admin user)");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (choice.equals("user"))
        {
            for (int i = 0 ; i<Atm.getUserList().size(); i ++)
            {
                ArrayList<Transaction> transactionu = Atm.getUserList().get(i).getTransactionList();
                if (transactionu.isEmpty())
                {
                    System.out.println("No Transaction Found");
                }
                else
                {
                    System.out.println("Transaction of the user: " + Atm.getUserList().get(i).getId());
                    for (int j = 0 ; j<transactionu.size();  j++)
                    {
                        System.out.println(transactionu.get(j));
                    }
                }


            }
        }

        if (choice.equals("admin"))
        {
            for (int i = 0 ; i<Atm.getAdminList().size(); i ++)
            {
                ArrayList<Transaction> transactiona = Atm.getAdminList().get(i).getTransactionList();
                if (transactiona.isEmpty())
                {
                    System.out.println("No Transaction Found");
                }
                else
                {
                    System.out.println("Transaction of the admin: " + Atm.getAdminList().get(i).getId());
                    for (int j = 0 ; j<transactiona.size();  j++)
                    {
                        System.out.println(transactiona.get(j));
                    }
                }


            }
        }

    }

    void addTransactionToAdmin(double amount , String id) {
        for (int i = 0; i < Atm.getAdminList().size(); i++)
        {
            Admin admin = Atm.getAdminList().get(i);
            if (admin.getId().equals(id))
            {
                Transaction transaction = new Transaction("Deposit", amount);
                admin.addTransaction(transaction);
                break;
            }
        }
    }
}
