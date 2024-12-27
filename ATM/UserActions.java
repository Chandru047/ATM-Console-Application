import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions extends Actions
{

    int login(String id) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);
        int index = -1;

        for (int i = 0; i < Atm.getUserList().size(); i++) {
            if (Atm.getUserList().get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.out.println("Enter the Password:");
            String pass = in.nextLine();

            if (Atm.getUserList().get(index).getPass().equals(pass)) {
                System.out.println("Login Successful");
                Atm.userOptions();
                return 1;
            } else {
                System.out.println("Invalid password.");
                return -1;
            }
        } else {
            System.out.println("The user does not exist.");
            return 0;
        }
    }

    void balance(String id)
    {
        for (int i = 0; i < Atm.getUserList().size(); i++) {
            if (Atm.getUserList().get(i).getId().equals(id)) {
                System.out.println(Atm.getUserList().get(i).getBalance());
            }

        }
    }

    public double performWithdraw(double amount, Notes notes, ArrayList<String> denomination) {
        int noteValue = Integer.parseInt(notes.getNote());
        long count = (long) (amount / noteValue);
        if (noteValue <= amount && count <= notes.getCount()) {
            amount -= count * noteValue;
            notes.setCount(notes.getCount() - count);
            denomination.add("You got " + count + " notes of " + noteValue);
            return amount;
        }
        return amount;
    }

    public void withdrawCash(String id) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);
        var denominationList = new ArrayList<String>();
        ArrayList<Notes> copy = new ArrayList<>();

        for (Notes type : Atm.getNote()) {
            copy.add(type.clone());
        }

        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(in.nextLine());
        double tempAmount = amount;

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        for (int i = 0 ; i <Atm.getUserList().size(); i++)
        {
            if (Atm.getUserList().get(i).getId().equals(id))
            {
                if (amount >Double.parseDouble(Atm.getUserList().get(i).getBalance()))
                {
                    System.out.println("Insufficient balance");
                    return;
                }
            }
        }


        while (amount > 0) {
            for (Notes notes : copy) {
                switch (notes.getNote()) {
                    case "2000", "500", "200", "100" : {
                        double remaining = performWithdraw(amount, notes, denominationList);
                        if (remaining < amount) {
                            amount = remaining;
                        }
                    }
                }
                if (amount == 0)
                {
                    for (int i = 0 ; i <Atm.getUserList().size(); i++)
                    {
                        if (Atm.getUserList().get(i).getId().equals(id))
                        {
                            double oldBalance = Double.parseDouble(Atm.getUserList().get(i).getBalance());
                            String newBalance =Double.toString(oldBalance - tempAmount);
                            Atm.getUserList().get(i).setBalance(newBalance);
                            break;
                        }
                    }

                }
            }

        }

        if (amount == 0) {
            Atm.setNote(copy);

            for (var temp : denominationList) {
                System.out.println(temp);
            }
        } else {
            System.out.println("Unable to withdraw the full amount. Please try another value.");

        }
    }



    void deposit(String id)
    {
        System.out.println("Enter the Deposit amount");
        Scanner in =new Scanner(System.in);
        String depositAmountS = in.nextLine();
        double depositAmount = Double.parseDouble(depositAmountS);
        for (int i = 0; i < Atm.getUserList().size(); i++)
        {
            if (Atm.getUserList().get(i).getId().equals(id))
            {
                String oldbalance = Atm.getUserList().get(i).getBalance();
                double typeBalance = Double.parseDouble(oldbalance);
                if (depositAmount <= 0)
                {
                    System.out.println("Invalid Deposit amount , Try Again");


                    continue;
                }
                System.out.println("Enter the no of 2000 note: ");
                double twoThousand = Double.parseDouble(in.nextLine());

                System.out.println("Enter the no of 500 note: ");
                double fiveHundred = Double.parseDouble(in.nextLine());

                System.out.println("Enter the no of 200 note: ");
                double twoHundred = Double.parseDouble(in.nextLine());

                System.out.println("Enter the no of 100 note: ");
                double hundred = Double.parseDouble(in.nextLine());

                double noteBalance = twoThousand * 2000 + fiveHundred * 500 + twoHundred * 200 + hundred * 100;
                if (depositAmount == noteBalance)
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




                    double newBalance = typeBalance + depositAmount ;
                    String srtBalance = Double.toString(newBalance);
                    Atm.getUserList().get(i).setBalance(srtBalance);
                    System.out.println("Amount deposited sucessfully");
                    addTransactionToUser("Deposit", depositAmount , id);
                    double newBankBalance = Atm.getBankBalance() + depositAmount;
                    Atm.setBankBalance(newBankBalance);

                    System.out.println("Deposit Sucessfull");
                }



            }

        }

    }

    void changePass(String id)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("verify to change the password");
        String tempPass = in.nextLine();
        for (int i = 0 ; i<Atm.getUserList().size(); i ++)
        {
            if (Atm.getUserList().get(i).getId().equals(id))
            {
                String currentPass = Atm.getUserList().get(i).getPass();
                if (tempPass.equals(currentPass))
                {
                    System.out.println("Enter the new pass:");
                    String newPass = in.nextLine();
                    Atm.getUserList().get(i).setPass(newPass);
                    System.out.println("Password Updated Successfully");

                }
                else
                {
                    System.out.println("Wrong password cannot verify");

                }
            }
        }
    }

    void addTransactionToUser(String type, double amount , String id) {
        for (int i = 0; i < Atm.getUserList().size(); i++)
        {
            User user = Atm.getUserList().get(i);
            if (user.getId().equals(id))
            {
                Transaction transaction = new Transaction(type, amount);
                user.addTransaction(transaction);
                break;
            }
        }
    }

    public void viewTransactions(String id) {
        for (int i = 0; i < Atm.getUserList().size(); i++)
        {
            User user = Atm.getUserList().get(i);

            if (user.getId().equals(id))
            {
                ArrayList<Transaction> transactions = user.getTransactionList();

                if (transactions.isEmpty()) {
                    System.out.println("No transactions for user " + id);
                } else {
                    System.out.println("Transaction History for user " + id + ":");

                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                }
                break;
            }
        }
    }

}

