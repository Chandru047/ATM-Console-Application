import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions extends Actions
{

    int login(String id) {
        Scanner in = new Scanner(System.in);
        int index = -1;

        for (int i = 0; i < getUserList().size(); i++) {
            if (getUserList().get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.out.println("Enter the Password:");
            String pass = in.nextLine();

            if (getUserList().get(index).getPass().equals(pass)) {
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
        for (int i = 0; i < getUserList().size(); i++) {
            if (getUserList().get(i).getId().equals(id)) {
                System.out.println(getUserList().get(i).getBalance());
            }

        }
    }

    public static double performWithdraw(double amount , Notes notes , ArrayList<String> denomination)
    {
        long count = (long) (amount/Integer.parseInt(notes.getNote()));
        if (Long.parseLong(notes.getNote()) < amount && count <=notes.getCount())
        {
            amount -= count*Integer.parseInt(notes.getNote());
            notes.setCount(notes.getCount() - count);
            String temp = "you got" + notes.getCount() +"of count" + count;
            denomination.add(temp);

        }
    }

    public static void withdrawCash(String id) throws CloneNotSupportedException
    {
        Scanner in = new Scanner(System.in);
        var denominationList = new ArrayList<String>();
        ArrayList<Notes> copy = new ArrayList<>();
        for (Notes type : Atm.getNote())
        {
            copy.add(type.clone());
        }
        System.out.println("Enter the amount to withdraw:");
        double amount =Double.parseDouble(in.nextLine());
        while (amount !=0)
        {
            for (Notes notes : copy)
            {
                var noteType = notes.getNote();
                switch (noteType)
                {
                    case "2000","500","200","100":
                    {
                        amount =performWithdraw(amount , notes , denominationList);
                    }
                }
            }
        }
    }

    void withdraw(String id)
    {
        System.out.println("Enter the withdraw amount");
        Scanner in =new Scanner(System.in);
        double withdrawAmount = in.nextDouble();
        for (int i = 0; i < getUserList().size(); i++)
        {
            if (getUserList().get(i).getId().equals(id))
            {
                String oldbalance = getUserList().get(i).getBalance();
                double typeBlance = Double.parseDouble(oldbalance);
                if (withdrawAmount > typeBlance || withdrawAmount > getBankBalance())
                {
                    System.out.println("Insufficient Fund , Try Again");
                    continue;
                }
                else if (!(withdrawAmount % 100 == 0))
                {
                    System.out.println("Enter a round amount");
                    continue;
                }
                double newBalance = typeBlance - withdrawAmount ;
                String srtBalance = Double.toString(newBalance);
                getUserList().get(i).setBalance(srtBalance);
                System.out.println("Amount withdraw sucessfull");
                addTransactionToUser("Withdraw", withdrawAmount , id);
                break;


            }


        }

    }

    void deposit(String id)
    {
        System.out.println("Enter the Deposit amount");
        Scanner in =new Scanner(System.in);
        String depositAmountS = in.nextLine();
        double depositAmount = Double.parseDouble(depositAmountS);
        for (int i = 0; i < getUserList().size(); i++)
        {
            if (getUserList().get(i).getId().equals(id))
            {
                String oldbalance = getUserList().get(i).getBalance();
                double typeBalance = Double.parseDouble(oldbalance);
                if (depositAmount <= 0)
                {
                    System.out.println("Invalid Deposit amount , Try Again");


                    continue;
                }
                Notes notes = new Notes();
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
                    for (Notes notesHere:getNote())
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
                    getUserList().get(i).setBalance(srtBalance);
                    System.out.println("Amount deposited sucessfully");
                    addTransactionToUser("Deposit", depositAmount , id);
                    double newBankBalance = getBankBalance() + depositAmount;
                    setBankBalance(newBankBalance);

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
        for (int i = 0 ; i<getUserList().size(); i ++)
        {
            if (getUserList().get(i).getId().equals(id))
            {
                String currentPass = getUserList().get(i).getPass();
                if (tempPass.equals(currentPass))
                {
                    System.out.println("Enter the new pass:");
                    String newPass = in.nextLine();
                    getUserList().get(i).setPass(newPass);
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
        for (int i = 0; i < getUserList().size(); i++)
        {
            User user = getUserList().get(i);
            if (user.getId().equals(id))
            {
                Transaction transaction = new Transaction(type, amount);
                user.addTransaction(transaction);
                break;
            }
        }
    }

    public void viewTransactions(String id) {
        for (int i = 0; i < getUserList().size(); i++)
        {
            User user = getUserList().get(i);

            if (user.getId().equals(id))
            {
                ArrayList<Transaction> transactions = user.getTransactionList();

                if (transactions.isEmpty()) {
                    System.out.println("No transactions for user " + id);
                } else {
                    System.out.println("Transaction History for user " + id + ":");

                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println(transactions.get(j));
                    }
                }
                break;
            }
        }
    }

}

