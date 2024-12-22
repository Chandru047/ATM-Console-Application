import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction
{
    Admin admin = new Admin();
    private static final int pin = 1920;
    ArrayList<ArrayList<String>> arrayList = new ArrayList<>();


    void start()
    {
        System.out.println("ATM Console Application");
        adminLogin();
    }

    void adminLogin()
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            System.out.println("Enter the ID: ");
            String id = in.nextLine();
            if (id.contains("ad"))
            {


                boolean exists = false;
                int Index = -1;


                for (int i = 0; i < arrayList.size(); i++)
                {
                    if (arrayList.get(i).get(0).equals(id))
                    {
                        exists = true;
                        Index = i;
                        break;
                    }
                }

                if (exists)
                {


                    System.out.println("Enter the password: ");
                    String password = in.nextLine();

                    if (arrayList.get(Index).get(1).equals(password))
                    {
                        System.out.println("Login successful!");
                        admin.options();
                    }
                    else
                    {
                        System.out.println("Invalid password. Try again.");
                    }
                }
                else
                {

                    System.out.println("ID does not exist. Creating a new account...");
                    System.out.println("Enter the pin: ");
                    int enteredPin = in.nextInt();
                    in.nextLine();

                    if (enteredPin != pin)
                    {
                        System.out.println("Incorrect pin. Account creation failed.");
                        continue;
                    }

                    System.out.println("Enter the new password: ");
                    String newPassword = in.nextLine();

                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(id);
                    temp.add(newPassword);
                    arrayList.add(temp);
                    System.out.println(arrayList);

                    System.out.println("Account created successfully!");
                }
            }
            else
            {
                admin.User(id);
            }
        }
    }




    }



