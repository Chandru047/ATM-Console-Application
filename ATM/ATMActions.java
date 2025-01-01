import java.util.Scanner;

public class ATMActions {
    static Scanner in = new Scanner(System.in);

    // Entry point for the ATM system
    void start() throws CloneNotSupportedException {
        while (true) {
            // Prompt user to enter their ID
            System.out.println("Enter the ID: ");
            String id = in.nextLine();
            Atm.setId(id);

            // Handle admin actions
            if (Atm.getId().contains("ad")) {
                AdminActions adminAction = new AdminActions();
                Admin loggedInAdmin = adminAction.login(Atm.getId());

                if (loggedInAdmin != null) {
                    // Successful login or account creation
                    System.out.println("Welcome, " + loggedInAdmin.getId() + "!");
                    adminOptions(loggedInAdmin); // Open admin options menu
                } else {
                    // Login or account creation failed
                    System.out.println("Login or account creation failed.");
                }
            }

            // Handle user actions
            else if (Atm.getId().contains("us")) {
                UserActions userAction = new UserActions();
                User loggedInUser = userAction.login(Atm.getId());

                if (loggedInUser != null) {
                    // Successful login
                    System.out.println("Welcome, " + loggedInUser.getId() + "!");
                    userOptions(loggedInUser); // Open user options menu
                } else {
                    // Login failed
                    System.out.println("Login failed.");
                }
            }

            // Exit the system
            else if (id.contains("exit")) {
                break; // Exit the loop
            }

            // Invalid input
            else {
                System.out.println("Invalid Choice !");
            }
        }
    }

    // Admin options menu
    static void adminOptions(Admin currentAdmin) {
        AdminActions admin = new AdminActions();

        while (true) {
            // Display admin menu options
            System.out.println("Add User \nDelete User \nView all Users \nView all Transactions \nDeposit \nexit");
            String choice = in.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                break; // Exit admin menu
            } else if (choice.equalsIgnoreCase("Add User") || choice.equalsIgnoreCase("Add")) {
                admin.addUser(); // Add a new user
            } else if (choice.equalsIgnoreCase("Delete User") || choice.equalsIgnoreCase("Delete")) {
                admin.deleteUser(); // Delete a user
            } else if (choice.equalsIgnoreCase("View all Users") || choice.equalsIgnoreCase("View")) {
                admin.viewUser(); // View all users
            } else if (choice.equalsIgnoreCase("View all Transactions") || choice.equalsIgnoreCase("Transaction")) {
                admin.viewTransaction(); // View all transactions
            } else if (choice.equalsIgnoreCase("deposit")) {
                admin.deposit(currentAdmin); // Deposit money to ATM
            }
        }
    }

    // User options menu
    static void userOptions(User currentUser) throws CloneNotSupportedException {
        while (true) {
            UserActions user = new UserActions();

            // Display user menu options
            System.out.println("Check Balance \nWithdraw \nDeposit \nChange Pass \nView Transaction \nexit");
            String choice = in.nextLine();

            if (choice.equalsIgnoreCase("Check Balance") || choice.equalsIgnoreCase("Balance")) {
                user.balance(currentUser); // Check balance
            } else if (choice.equalsIgnoreCase("Withdraw")) {
                user.withdrawCash(currentUser); // Withdraw money
            } else if (choice.equalsIgnoreCase("Deposit")) {
                user.deposit(currentUser); // Deposit money
            } else if (choice.equalsIgnoreCase("Change password") || choice.equalsIgnoreCase("Change")) {
                user.changePass(currentUser); // Change user password
            } else if (choice.equalsIgnoreCase("Transaction") || choice.equalsIgnoreCase("Transaction history")) {
                user.viewTransactions(currentUser); // View transaction history
            } else if (choice.equalsIgnoreCase("exit")) {
                break; // Exit user menu
            }
        }
    }
}
