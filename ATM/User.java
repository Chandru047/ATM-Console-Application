import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    String ID ;
    ArrayList<ArrayList<String>> userList;
    User()
    {

    }
    User(ArrayList<ArrayList<String>> user)
    {
        this.userList = user ;
    }
    void options()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Check balance \n Withdraw \n Deposit \n Change Pass \n View Transaction");
        String choice = in.nextLine();
        if(choice.equalsIgnoreCase("Check Balance") || choice.equalsIgnoreCase("Balance"))
        {
            balance();
        }
    }

    void User(String id) {
        ID = id ;
        Scanner in = new Scanner(System.in);
        boolean exist = false;
        int Index = -1;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get(0).contains(id)) {
                exist = true;
                Index = i;
            }

            if (exist) {

                System.out.println("Enter the Password:");
                String pass = in.nextLine();

                for (int j = 0; j < userList.size(); j++) {
                    if (userList.get(i).get(1).equals(pass)) {
                        System.out.println("Login Successful");
                        options();
                    }

                }

            }
            else
            {
                System.out.println("The user does not exist");
            }

        }
    }

    void balance()
    {
        System.out.println(userList);
    }
}
