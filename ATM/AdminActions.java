import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions
{
    // Method to log in as an admin
    Account login(String id) {
        Scanner in = new Scanner(System.in);

        while (true) // infinite loop for the login process
        {
            Account foundAdmin = null; // initialize it as null

            // Search for admin by ID
            for (Account admin : Atm.getAdminList()) // for loop to get all the CustomerList
            {
                if (admin instanceof Admin) // check if admin is an Admin type
                {
                    if (admin.getId().equals(id))  // if the id matches store the admin object in the foundAdmin
                    {
                        foundAdmin = admin;
                        break;
                    }
                }

            }

            if (foundAdmin != null)  // if not null then the admin already exist
            {
                // Prompt for password
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (foundAdmin.getPass().equals(password)) // if the password matches then print login successful
                {
                    System.out.println("Login successful!");
                    ATMActions.adminOptions((Admin) foundAdmin);
                    return foundAdmin;
                }
                else
                {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }
            else
            {
                // Create a new admin account if ID does not exist
                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = in.nextInt();
                in.nextLine();

                if (enteredPin != Admin.getPIN())
                {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return null; // if the pin does not match then return null
                }



                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();
                Account admin = new Admin(id , newPassword); // create object of admin with reference Account
                Atm.getAdminList().add(admin); // add the admin object to the list

                System.out.println("Account created successfully!");
                return admin; // if login successfully then return admin object
            }
        }
    }

    // Method to add a new user
    void addUser()
    {
        Scanner in = new Scanner(System.in);
        int index = -1;
        System.out.println("Enter the ID of the new User");
        String newId = in.nextLine();

        if (newId.contains("us"))
        {
            // Check if user already exists
            for (int i = 0; i < Atm.getUserList().size(); i++)  // for loop to get all the user object
            {
                if (Atm.getUserList().get(i).getId().equals(newId)) // if the id matches then set the index
                {
                    index = i;
                    break;
                }
            }

            if (index != -1)
            {
                System.out.println("User Already exists");
            }
            else
            {
                // Create a new user account
                System.out.println("ID does not exist. Creating a new account...");

                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                System.out.println("Enter the Initial Balance: ");
                String initialBalance = in.nextLine();

                Account user = new User(newId , newPassword); // create the user object with Account ref
                ((User) user).setBalance(initialBalance); // set balance for the user
                Atm.getUserList().add(user); // add the user object to the List
                System.out.println("Account created successfully!");
            }
        }
        else // fi login not successful
        {
            System.out.println("Invalid user");
        }
    }

    // Method to delete an existing user
    void deleteUser()
    {
        System.out.println("Enter the User ID to Delete");
        Scanner in = new Scanner(System.in);
        String dAccount = in.nextLine();
        boolean exists = false;
        int Index = -1;

        // Check if user exists
        for (int i = 0; i < Atm.getUserList().size(); i++) // go through the list
        {
            if (Atm.getUserList().get(i).getId().equals(dAccount))  // if the id matches then set the index
            {
                exists = true;
                Index = i;
                break;
            }
        }

        if (exists) // if the id exist then remove the object in that index
        {
            Atm.getUserList().remove(Index);
            System.out.println("User Deleted");
        }
        else
        {
            System.out.println("User Does not exist");
        }
    }

    // Method to view all users and their balances
    void viewUser() {
        for (Account user : Atm.getUserList()) // get all the object in the list
        {
            if (user instanceof User) // check if the object is User
            {
                System.out.print(user.getId());
                System.out.println(" | " + ((User) user).getBalance()); // if user then get the balance and cast it to UserType
            }

        }
    }

    // Method to deposit cash into the ATM
    void deposit(Admin currentAdmin)
    {
        while (true)
        {
            System.out.println("Amount available:" + Atm.getBankBalance());
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the deposit amount: ");
            String bankBalanceD = in.nextLine();
            double bankBalance = Double.parseDouble(bankBalanceD);

            // get all the denominations

            System.out.println("Enter the no of 2000 note: ");
            double twoThousand = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 500 note: ");
            double fiveHundred = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 200 note: ");
            double twoHundred = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 100 note: ");
            double hundred = Double.parseDouble(in.nextLine());
            // get the total amount
            double noteBalance = twoThousand * 2000 + fiveHundred * 500 + twoHundred * 200 + hundred * 100;

            if (bankBalance == noteBalance) // if the deposit matches then deposit successful
            {
                double oldCount;
                double newCount;

                // Update the note counts in the ATM
                for (Notes notesHere : Atm.getNote())  // go through all the Notes Object
                {
                    if (notesHere.getNote().equals("2000"))
                    {
                        oldCount = notesHere.getCount(); // get the old Count
                        newCount = oldCount + twoThousand; // get the new count
                        notesHere.setCount(newCount); // set the new count
                    }

                    if (notesHere.getNote().equals("500")) {
                        oldCount = notesHere.getCount();// get the old Count
                        newCount = oldCount + fiveHundred;// get the new count
                        notesHere.setCount(newCount);// set the new count
                    }

                    if (notesHere.getNote().equals("200")) {
                        oldCount = notesHere.getCount();// get the old Count
                        newCount = oldCount + twoHundred;// get the new Count
                        notesHere.setCount(newCount);// set the new count
                    }

                    if (notesHere.getNote().equals("100")) {
                        oldCount = notesHere.getCount();// get the old Count
                        newCount = oldCount + hundred;// set the new Count
                        notesHere.setCount(newCount);// set the new count
                    }
                }

                double newBalance = bankBalance + Atm.getBankBalance(); // get the new balance
                Atm.setBankBalance(newBalance); // set the new balance
                System.out.println("Deposit Successful");
                addTransactionToAdmin(bankBalance, currentAdmin.getId()); // add transaction to the current admin
                break;
            }
            else
            {
                System.out.println("Count doesn't match. Try again.");
                break;
            }
        }
    }

    // Method to view transactions for users or admins
    void viewTransaction()
    {
        System.out.println("Enter option (admin user)");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("user")) // if the input is user then print user transactions
        {
            for (Account user : Atm.getUserList()) // go through the List
            {
                ArrayList<Transaction> transactionu = user.getTransactionList(); // create an arrayList of Transaction

                if (transactionu.isEmpty()) // if the transaction is empty then print empty
                {
                    System.out.println("No Transaction Found");
                }
                else
                {
                    System.out.println("Transaction of the user: " + user.getId());
                    for (Transaction transaction : transactionu)  // go through teh transactions and print it
                    {
                        System.out.println(transaction);
                    }
                }
            }
        }

        if (choice.equals("admin"))// if the input is amin then print admin transactions
        {
            for (Account admin : Atm.getAdminList()) {
                ArrayList<Transaction> transactiona = admin.getTransactionList();

                if (transactiona.isEmpty()) // if the transaction is empty then print empty
                {
                    System.out.println("No Transaction Found");
                }
                else
                {
                    System.out.println("Transaction of the admin: " + admin.getId());
                    for (Transaction transaction : transactiona)// go through teh transactions and print it
                    {
                        System.out.println(transaction);
                    }
                }
            }
        }
    }

    // Helper Method to add a transaction to the admin's transaction list
    void addTransactionToAdmin(double amount, String id) {
        for (Account admin : Atm.getAdminList())  // go through the list
        {
            if (admin.getId().equals(id))
            {
                Transaction transaction = new Transaction("Deposit", amount); //  create new transaction
                admin.addTransaction(transaction); // add transaction to the teh Transaction List
                break;
            }
        }
    }
}
