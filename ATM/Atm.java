import Notes.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Atm
{
    static double bankBalance ;
    private static ArrayList<Notes> note = new ArrayList<>
            (
            Arrays.asList
                    (
                    new TwoThousand("2000", 0),
                    new FiveHundred("500", 0),
                    new TwoHundred("200", 0),
                    new Hundred("100", 0)
                    )
            );
    private static final ArrayList<Admin> adminList = new ArrayList<>();
    private static final ArrayList<User> userList = new ArrayList<>();
    private static String id ;

    static ArrayList<Notes> getNote()
    {
        return note;
    }

    static void setNote(ArrayList<Notes> temp)
    {
        note = temp;
    }

    static ArrayList<Admin> getAdminList()
    {
        new TwoHundred("200", 10);
        return adminList;
    }

    static ArrayList<User> getUserList()
    {
        return userList;
    }
    static void setBankBalance(double balance)
    {
        bankBalance = balance;
    }
    static double getBankBalance()
    {
        return bankBalance;
    }
    static void setId(String Id)
    {
        id = Id;
    }
    public static String getId()
    {
        return id;
    }

}
