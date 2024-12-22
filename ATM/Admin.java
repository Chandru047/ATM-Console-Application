import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    ArrayList<ArrayList<String>> userList = new ArrayList<>();


    void options() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(" Add User \n Delete User  \n View all User\n View all Transaction \n exit");
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            } else if (choice.equalsIgnoreCase("Add User") || choice.equalsIgnoreCase("Add")) {
                addUser();
            } else if (choice.equalsIgnoreCase("Delete User") || choice.equalsIgnoreCase("Delete")) {
                deleteUser();
            } else if (choice.equalsIgnoreCase("View all User") || choice.equalsIgnoreCase("View")) {
                viewUser();
            } else if (choice.equalsIgnoreCase("View all Transaction") || choice.equalsIgnoreCase("Transaction")) {
                viewTransaction();
            }

        }

    }

    void addUser() {
        Scanner in = new Scanner(System.in);
        boolean exists = false;
        int Index = -1;
        System.out.println("Enter the ID of the new User");
        String newId = in.nextLine();
        if (newId.contains("us")) {


            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).get(0).equals(newId)) {
                    exists = true;
                    Index = i;
                    break;
                }
            }

            if (exists) {
                System.out.println("User Already exists");
            } else {
                System.out.println("ID does not exist. Creating a new account...");

                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                System.out.println("Enter the Initial Balance: ");
                String initialBalance = in.nextLine();

                ArrayList<String> temp = new ArrayList<>();
                temp.add(newId);
                temp.add(newPassword);
                temp.add(initialBalance);
                userList.add(temp);
                System.out.println(userList);

                System.out.println("Account created successfully!");
                User user =new User(userList);

            }
        } else {
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
            if (userList.get(i).get(0).equals(dAccount)) {
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
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).get(0));
        }
    }

    void viewTransaction() {

    }


}
