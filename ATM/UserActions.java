import java.util.Scanner;

public class UserActions extends Actions
{
    String inId;
    void options()
    {
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
//                transaction();
            }
        }

    }

    void login(String id)
    {
        inId = id;
        Scanner in = new Scanner(System.in);
        int index = -1;

        label: for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(id)) {
                index = i;

            }

            if (index != -1)
            {

                System.out.println("Enter the Password:");
                String pass = in.nextLine();

                for (int j = 0; j < userList.size(); j++) {
                    if (userList.get(i).getPass().equals(pass)) {
                        System.out.println("Login Successful");
                        options();
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
            if (userList.get(i).getId().equals(inId)) {
                System.out.println(userList.get(i).getBalance());
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
            if (userList.get(i).getId().equals(inId))
            {
                String oldbalance = userList.get(i).getBalance();
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
                userList.get(i).setBalance(srtBalance);
                System.out.println("Amount withdraw sucessfull");
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
            if (userList.get(i).getId().equals(inId))
            {
                String oldbalance = userList.get(i).getBalance();
                double typeBalance = Double.parseDouble(oldbalance);
                if (depositAmount <= 0)
                {
                    System.out.println("Invalid Deposit amount , Try Again");


                    continue;
                }
                double newBalance = typeBalance + depositAmount ;
                String srtBalance = Double.toString(newBalance);
                userList.get(i).setBalance(srtBalance);
                System.out.println("Amount deposited sucessfully");
                double newBankBalance = getBankBalance() + depositAmount;
                setBankBalance(newBankBalance);




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
            if (userList.get(i).getId().equals(inId))
            {
                String currentPass = userList.get(i).getPass();
                if (tempPass.equals(currentPass))
                {
                    System.out.println("Enter the new pass:");
                    String newPass = in.nextLine();
                    userList.get(i).setPass(newPass);
                    System.out.println("Password Updated Successfully");

                }
                else
                {
                    System.out.println("Wrong password cannot verify");

                }
            }
        }
    }
}
