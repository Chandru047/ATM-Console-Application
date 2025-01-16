import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions
{
    // Handles user login process
    Account login(String id) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);

        // Find the user by ID
        for (Account user : Atm.getUserList()) //go through the List
        {
            if (user instanceof User)// check if user is a User type
            {
                if (user.getId().equals(id)) // if the id matches then proceed to get pass
                {
                    System.out.println("Enter the Password:");
                    String pass = in.nextLine();

                    // Verify password
                    if (user.getPass().equals(pass))  // if the pass matches then login successful
                    {
                        System.out.println("Login Successful");
                        ATMActions.userOptions((User) user); // Redirect to user options menu
                        return user; // Return the User object on success
                    }
                    else
                    {
                        System.out.println("Invalid password.");
                        return null; // Return null for invalid password
                    }
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
        if (Long.parseLong(notes.getNote()) <= amount && notes.getCount() > 0) // if the denomination is less than amount and notes count is greater than zero
        {
            if (count <= notes.getCount()) // if the count is less than count
            {
                amount -= count * Integer.parseInt(notes.getNote()); // set the amount as count times the noteCount
                notes.setCount(notes.getCount() - count); // set the updated noteCount
                denomination.add("You got " + count + " notes of " + notes.getNote()); // Add denomination History
            } else
            {
                amount -= notes.getCount() * Integer.parseInt(notes.getNote());// set the amount as count times the noteCount
                denomination.add("You got " + notes.getCount() + " notes of " + notes.getNote());// Add denomination History
                notes.setCount(0); // set the count to zero
            }
            return amount; // return the amount
        }
        return amount; // if the condition does not satisfy then return the same amount
    }

    // Withdraws cash from the ATM
    public void withdrawCash(User currentUser) throws CloneNotSupportedException
    {
        Scanner in = new Scanner(System.in);
        ArrayList<String> denominationList = new ArrayList<String>(); // create an arrayList of String
        ArrayList<Notes> copy = new ArrayList<>(); // create an arrayList of Notes

        // Clone the notes list to calculate denominations without affecting the original
        for (Notes type : Atm.getNote()) // go through all the notes
        {
            copy.add((Notes) type.clone()); // clone the note to the copy ArrayList
        }

        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(in.nextLine());
        double tempAmount = amount;

        // Validate input amount
        if (amount <= 0) // if less than zero then not possible
        {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        if (amount > Double.parseDouble(currentUser.getBalance())) // if the withdraw amount is greater than balance than not possible
        {
            System.out.println("Insufficient balance");
            return;
        }

        // Attempt to withdraw amount using available denominations
        while (amount > 0) // if amount is greater than 0
        {
            for (Notes notes : copy) // go through all the notes in copy
            {
                switch (notes.getNote())
                {
                    case "2000", "500", "200", "100":  // iterate through all the notes
                    {
                        double remaining = performWithdraw(amount, notes, denominationList); // call the helper method
                        if (remaining < amount) // if amount remains then the cash remains
                        {
                            amount = remaining;
                        }
                    }
                }
                if (amount == 0)  // if amount = zero then all note are possible
                {
                    double oldBalance = Double.parseDouble(currentUser.getBalance());
                    String newBalance = Double.toString(oldBalance - tempAmount);
                    currentUser.setBalance(newBalance); // set the updated balance
                    break;
                }
            }
        }

        // Update the ATM notes and print results
        if (amount == 0) // after successful withdraw
        {
            Atm.setNote(copy); // update the original note

            for (var temp : denominationList) // print all the denomination List
            {
                System.out.println(temp);
            }
        }
        else // Withdraw failed
        {
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
        if (depositAmount <= 0)
        {
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
                    oldCount = notesHere.getCount();// get the old Count
                    newCount = oldCount + twoThousand;// get the new count
                    notesHere.setCount(newCount);// set the new count
                }

                if (notesHere.getNote().equals("500")) {
                    oldCount = notesHere.getCount();// get the old Count
                    newCount = oldCount + fiveHundred;// get the new count
                    notesHere.setCount(newCount);// set the new count
                }

                if (notesHere.getNote().equals("200")) {
                    oldCount = notesHere.getCount();// get the old Count
                    newCount = oldCount + twoHundred;// get the new count
                    notesHere.setCount(newCount);// set the new count
                }

                if (notesHere.getNote().equals("100")) {
                    oldCount = notesHere.getCount();// get the old Count
                    newCount = oldCount + hundred;// get the new count
                    notesHere.setCount(newCount);// set the new count
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
    void changePass(User currentUser)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Verify to change the password"); // get input to verify
        String tempPass = in.nextLine();

        String currentPass = currentUser.getPass();
        if (tempPass.equals(currentPass)) // if the pass matches then change it
        {
            System.out.println("Enter the new pass:");
            String newPass = in.nextLine();
            currentUser.setPass(newPass); // update the pass
            System.out.println("Password Updated Successfully");
        }
        else // if the verification fails then not change
        {
            System.out.println("Wrong password cannot verify");
        }
    }

    // Adds a transaction to the user's transaction list
    void addTransactionToUser(String type, double amount, User currentUser)
    {
        Transaction transaction = new Transaction(type, amount);
        currentUser.addTransaction(transaction);
    }

    // Displays the transaction history of the user
    public void viewTransactions(User currentUser)
    {
        ArrayList<Transaction> transactions = currentUser.getTransactionList(); // create an ArrayList of transaction and store all the users Transaction

        if (transactions.isEmpty())  // if empty then print empty
        {
            System.out.println("No transactions for user " + currentUser.getId());
        }
        else
        {
            System.out.println("Transaction History for user " + currentUser.getId() + ":");

            for (Transaction transaction : transactions) // go through all the Transactions
            {
                System.out.println(transaction);
            }
        }
    }
}
