import java.util.ArrayList;
import java.util.Scanner;

public class User{
    String ID;
    ArrayList<ArrayList<String>> userList = new ArrayList<>();
    ArrayList<ArrayList<String>> transactionHistory = new ArrayList<>();
    ArrayList<String> tempt = new ArrayList<>();




    void options()
    {
        tempt.add(ID);
        while (true)
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Check balance \n Withdraw \n Deposit \n Change Pass \n View Transaction \n exit");
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("Check Balance") || choice.equalsIgnoreCase("Balance")) {
                balance();
            }
            else if (choice.equalsIgnoreCase("Withdraw")) {
                withdraw();
            }
            else if (choice.equalsIgnoreCase("exit"))
            {

                break;
            }
            else if (choice.equalsIgnoreCase("Deposit"))
            {
                deposit();
            }
            else if (choice.equalsIgnoreCase("Change password")|| choice.equalsIgnoreCase("change"))
            {
                changePass();
            } else if (choice.equalsIgnoreCase("transaction" )|| choice.equalsIgnoreCase("Transaction history"))
            {
                transaction();
            }
        }

    }

    void User(String id , ArrayList<ArrayList<String>> user) {
        ID = id;
        userList = user ;
        Scanner in = new Scanner(System.in);
        boolean exist = false;
        int Index = -1;

        label: for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get(0).contains(id)) {
                exist = true;
                Index = i;

            }

            if (exist)
            {

                System.out.println("Enter the Password:");
                String pass = in.nextLine();

                for (int j = 0; j < userList.size(); j++) {
                    if (userList.get(i).get(1).equals(pass)) {
                        System.out.println("Login Successful");
                        options();
                        exist = false;
                        break label;

                    }
                }

            }
            else {
                System.out.println("The user does not exist");
            }

        }
    }

    void balance()
    {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get(0).contains(ID)) {
                System.out.println(userList.get(i).get(2));
            }

        }
    }

    void withdraw()
    {
        System.out.println("Enter the withdraw amount");
        Scanner in =new Scanner(System.in);
        double withdrawAmount = in.nextDouble();
        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).get(0).contains(ID))
            {
                String oldbalance = userList.get(i).get(2);
                double typeBlance = Double.parseDouble(oldbalance);
                if (withdrawAmount > typeBlance)
                {
                    System.out.println("Insufficient Fund , Try Again");
                    String temp = "Withdraw failed ! " ;
                    tempt.add(temp);
                    transactionHistory.add(tempt);

                    continue;
                }
                double newBalance = typeBlance - withdrawAmount ;
                String srtBalance = Double.toString(newBalance);
                userList.get(i).set(2 , srtBalance);
                System.out.println("Amount withdraw sucessfull");
                String temp = "Withdraw of amount" + srtBalance;
                tempt.add(temp);
                transactionHistory.add(tempt);
                break;


            }

        }

    }

    void deposit()
    {
        System.out.println("Enter the Deposit amount");
        Scanner in =new Scanner(System.in);
        double depositAmount = in.nextDouble();
        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).get(0).contains(ID))
            {
                String oldbalance = userList.get(i).get(2);
                double typeBalance = Double.parseDouble(oldbalance);
                if (depositAmount <= 0)
                {
                    System.out.println("Invalid Deposit amount , Try Again");
                    String temp = "Deposit failed !" ;
                    tempt.add(temp);
                    transactionHistory.add(tempt);

                    continue;
                }
                double newBalance = typeBalance + depositAmount ;
                String srtBalance = Double.toString(newBalance);
                userList.get(i).set(2 , srtBalance);
                System.out.println("Amount deposited sucessfully");
                String temp = "Deposit of amount" + srtBalance;
                tempt.add(temp);
                transactionHistory.add(tempt);


            }

        }

    }

    void changePass()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("verify to change the password");
        String tempPass = in.nextLine();
        for (int i = 0 ; i<userList.size(); i ++)
        {
            if (userList.get(i).get(0).equals(ID))
            {
                String currentPass = userList.get(i).get(1);
                if (tempPass.equals(currentPass))
                {
                    System.out.println("Enter the new pass:");
                    String newPass = in.nextLine();
                    userList.get(i).set(1 , newPass);
                    System.out.println("Password Updated Successfully");
                    System.out.println(userList);
                    String temp = "Password changed :" + newPass;
                    tempt.add(temp);
                    transactionHistory.add(tempt);

                }
                else
                {
                    System.out.println("Wrong password cannot verify");
                    String temp = "Password changed failed ! " ;
                    tempt.add(temp);
                    transactionHistory.add(tempt);

                }
            }
        }
    }

    void transaction()
    {
        System.out.println(transactionHistory);
    }
}
