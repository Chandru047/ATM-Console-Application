import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions
{
    // Method to log in as an admin
    Admin login(String id) {
        Scanner in = new Scanner(System.in);

        while (true) {
            Admin foundAdmin = null;

            // Search for admin by ID
            for (Admin admin : Atm.getAdminList()) {
                if (admin.getId().equals(id)) {
                    foundAdmin = admin;
                    break;
                }
            }

            if (foundAdmin != null) {
                // Prompt for password
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (foundAdmin.getPass().equals(password)) {
                    System.out.println("Login successful!");
                    ATMActions.adminOptions(foundAdmin);
                    return foundAdmin;
                } else {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            } else {
                // Create a new admin account if ID does not exist
                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = in.nextInt();
                in.nextLine();

                if (enteredPin != Admin.getPIN()) {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return null;
                }

                Admin admin = new Admin();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                admin.setPass(newPassword);
                Atm.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return admin;
            }
        }
    }

    // Method to add a new user
    void addUser() {
        Scanner in = new Scanner(System.in);
        int index = -1;
        System.out.println("Enter the ID of the new User");
        String newId = in.nextLine();

        if (newId.contains("us")) {
            // Check if user already exists
            for (int i = 0; i < Atm.getUserList().size(); i++) {
                if (Atm.getUserList().get(i).getId().equals(newId)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.out.println("User Already exists");
            } else {
                // Create a new user account
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
        } else {
            System.out.println("Invalid user");
        }
    }

    // Method to delete an existing user
    void deleteUser() {
        System.out.println("Enter the User ID to Delete");
        Scanner in = new Scanner(System.in);
        String dAccount = in.nextLine();
        boolean exists = false;
        int Index = -1;

        // Check if user exists
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

    // Method to view all users and their balances
    void viewUser() {
        for (User user : Atm.getUserList()) {
            System.out.print(user.getId());
            System.out.println(" | " + user.getBalance());
        }
    }

    // Method to deposit cash into the ATM
    void deposit(Admin currentAdmin) {
        while (true) {
            System.out.println("Amount available:" + Atm.getBankBalance());
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the deposit amount: ");
            String bankBalanceD = in.nextLine();
            double bankBalance = Double.parseDouble(bankBalanceD);

            System.out.println("Enter the no of 2000 note: ");
            double twoThousand = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 500 note: ");
            double fiveHundred = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 200 note: ");
            double twoHundred = Double.parseDouble(in.nextLine());

            System.out.println("Enter the no of 100 note: ");
            double hundred = Double.parseDouble(in.nextLine());

            double noteBalance = twoThousand * 2000 + fiveHundred * 500 + twoHundred * 200 + hundred * 100;

            if (bankBalance == noteBalance) {
                double oldCount;
                double newCount;

                // Update the note counts in the ATM
                for (Notes notesHere : Atm.getNote()) {
                    if (notesHere.getNote().equals("2000")) {
                        oldCount = notesHere.getCount();
                        newCount = oldCount + twoThousand;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("500")) {
                        oldCount = notesHere.getCount();
                        newCount = oldCount + fiveHundred;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("200")) {
                        oldCount = notesHere.getCount();
                        newCount = oldCount + twoHundred;
                        notesHere.setCount(newCount);
                    }

                    if (notesHere.getNote().equals("100")) {
                        oldCount = notesHere.getCount();
                        newCount = oldCount + hundred;
                        notesHere.setCount(newCount);
                    }
                }

                double newBalance = bankBalance + Atm.getBankBalance();
                Atm.setBankBalance(newBalance);
                System.out.println("Deposit Successful");
                addTransactionToAdmin(bankBalance, currentAdmin.getId());
                break;
            } else {
                System.out.println("Count doesn't match. Try again.");
                break;
            }
        }
    }

    // Method to view transactions for users or admins
    void viewTransaction() {
        System.out.println("Enter option (admin user)");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("user")) {
            for (User user : Atm.getUserList()) {
                ArrayList<Transaction> transactionu = user.getTransactionList();

                if (transactionu.isEmpty()) {
                    System.out.println("No Transaction Found");
                } else {
                    System.out.println("Transaction of the user: " + user.getId());
                    for (Transaction transaction : transactionu) {
                        System.out.println(transaction);
                    }
                }
            }
        }

        if (choice.equals("admin")) {
            for (Admin admin : Atm.getAdminList()) {
                ArrayList<Transaction> transactiona = admin.getTransactionList();

                if (transactiona.isEmpty()) {
                    System.out.println("No Transaction Found");
                } else {
                    System.out.println("Transaction of the admin: " + admin.getId());
                    for (Transaction transaction : transactiona) {
                        System.out.println(transaction);
                    }
                }
            }
        }
    }

    // Method to add a transaction to the admin's transaction list
    void addTransactionToAdmin(double amount, String id) {
        for (Admin admin : Atm.getAdminList()) {
            if (admin.getId().equals(id)) {
                Transaction transaction = new Transaction("Deposit", amount);
                admin.addTransaction(transaction);
                break;
            }
        }
    }
}
