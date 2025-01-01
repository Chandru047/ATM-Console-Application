import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions
{
    // Handles user login process
    User login(String id) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);

        // Find the user by ID
        for (User user : Atm.getUserList()) {
            if (user.getId().equals(id)) {
                System.out.println("Enter the Password:");
                String pass = in.nextLine();

                // Verify password
                if (user.getPass().equals(pass)) {
                    System.out.println("Login Successful");
                    ATMActions.userOptions(user); // Redirect to user options menu
                    return user; // Return the User object on success
                } else {
                    System.out.println("Invalid password.");
                    return null; // Return null for invalid password
                }
            }
        }

        // If the user does not exist
        System.out.println("The user does not exist.");
        return null;
    }

    // Displays the current balance of the user
    void balance(User currentUser) {
        System.out.println(currentUser.getBalance());
    }

    // Handles withdrawal of a specific denomination
    public double performWithdraw(double amount, Notes notes, ArrayList<String> denomination) {
        long count = (long) (amount / Integer.parseInt(notes.getNote()));

        // Check if the denomination can be used
        if (Long.parseLong(notes.getNote()) <= amount && notes.getCount() > 0) {
            if (count <= notes.getCount()) {
                amount -= count * Integer.parseInt(notes.getNote());
                notes.setCount(notes.getCount() - count);
                denomination.add("You got " + count + " notes of " + notes.getNote());
            } else {
                amount -= notes.getCount() * Integer.parseInt(notes.getNote());
                denomination.add("You got " + notes.getCount() + " notes of " + notes.getNote());
                notes.setCount(0);
            }
            return amount;
        }
        return amount;
    }

    // Withdraws cash from the ATM
    public void withdrawCash(User currentUser) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);
        var denominationList = new ArrayList<String>();
        ArrayList<Notes> copy = new ArrayList<>();

        // Clone the notes list to calculate denominations without affecting the original
        for (Notes type : Atm.getNote()) {
            copy.add(type.clone());
        }

        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(in.nextLine());
        double tempAmount = amount;

        // Validate input amount
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        if (amount > Double.parseDouble(currentUser.getBalance())) {
            System.out.println("Insufficient balance");
            return;
        }

        // Attempt to withdraw amount using available denominations
        while (amount > 0) {
            for (Notes notes : copy) {
                switch (notes.getNote()) {
                    case "2000", "500", "200", "100": {
                        double remaining = performWithdraw(amount, notes, denominationList);
                        if (remaining < amount) {
                            amount = remaining;
                        }
                    }
                }
                if (amount == 0) {
                    double oldBalance = Double.parseDouble(currentUser.getBalance());
                    String newBalance = Double.toString(oldBalance - tempAmount);
                    currentUser.setBalance(newBalance);
                    break;
                }
            }
        }

        // Update the ATM notes and print results
        if (amount == 0) {
            Atm.setNote(copy);

            for (var temp : denominationList) {
                System.out.println(temp);
            }
        } else {
            System.out.println("Unable to withdraw the full amount. Please try another value.");
        }
    }

    // Handles deposit process
    void deposit(User currentUser) {
        System.out.println("Enter the Deposit amount");
        Scanner in = new Scanner(System.in);
        String depositAmountS = in.nextLine();
        double depositAmount = Double.parseDouble(depositAmountS);

        String oldbalance = currentUser.getBalance();
        double typeBalance = Double.parseDouble(oldbalance);

        // Validate deposit amount
        if (depositAmount <= 0) {
            System.out.println("Invalid Deposit amount, Try Again");
            return;
        }

        // Collect denomination counts from the user
        System.out.println("Enter the no of 2000 note: ");
        double twoThousand = Double.parseDouble(in.nextLine());

        System.out.println("Enter the no of 500 note: ");
        double fiveHundred = Double.parseDouble(in.nextLine());

        System.out.println("Enter the no of 200 note: ");
        double twoHundred = Double.parseDouble(in.nextLine());

        System.out.println("Enter the no of 100 note: ");
        double hundred = Double.parseDouble(in.nextLine());

        double noteBalance = twoThousand * 2000 + fiveHundred * 500 + twoHundred * 200 + hundred * 100;

        // Verify that the total matches the deposit amount
        if (depositAmount == noteBalance) {
            double oldCount;
            double newCount;

            // Update the count of each denomination
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

            // Update user balance and ATM bank balance
            double newBalance = typeBalance + depositAmount;
            String srtBalance = Double.toString(newBalance);
            currentUser.setBalance(srtBalance);
            System.out.println("Amount deposited successfully");
            addTransactionToUser("Deposit", depositAmount, currentUser);
            double newBankBalance = Atm.getBankBalance() + depositAmount;
            Atm.setBankBalance(newBankBalance);

            System.out.println("Deposit Successful");
        }
    }

    // Allows the user to change their password
    void changePass(User currentUser) {
        Scanner in = new Scanner(System.in);
        System.out.println("Verify to change the password");
        String tempPass = in.nextLine();

        String currentPass = currentUser.getPass();
        if (tempPass.equals(currentPass)) {
            System.out.println("Enter the new pass:");
            String newPass = in.nextLine();
            currentUser.setPass(newPass);
            System.out.println("Password Updated Successfully");
        } else {
            System.out.println("Wrong password cannot verify");
        }
    }

    // Adds a transaction to the user's transaction list
    void addTransactionToUser(String type, double amount, User currentUser) {
        Transaction transaction = new Transaction(type, amount);
        currentUser.addTransaction(transaction);
    }

    // Displays the transaction history of the user
    public void viewTransactions(User currentUser) {
        ArrayList<Transaction> transactions = currentUser.getTransactionList();

        if (transactions.isEmpty()) {
            System.out.println("No transactions for user " + currentUser.getId());
        } else {
            System.out.println("Transaction History for user " + currentUser.getId() + ":");

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}
