package ATM;

import ATM.NoteList.FiveHundred;
import ATM.NoteList.Hundred;
import ATM.NoteList.TwoHundred;
import ATM.NoteList.TwoThousand;
import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Atm
{
    static double bankBalance ; // variable to store the bank balance
    private static ArrayList<Notes> note = new ArrayList<>
            (
            Arrays.asList
                    (
                    new TwoThousand("2000", 0),
                    new FiveHundred("500", 0),
                    new TwoHundred("200", 0),
                    new Hundred("100", 0)
                    )
            ); // ArrayList of notes and created objects for all the notes with default value
    private static final ArrayList<Account> customer = new ArrayList<>(); // Arraylist of Customer containing all the user and admin


    //Getters and Setters
    static ArrayList<Notes> getNote()
    {
        return note;
    }

    static void setNote(ArrayList<Notes> temp)
    {
        note = temp;
    }

    static ArrayList<Account> getAdminList()
    {
        return customer;
    }

    static ArrayList<Account> getUserList()
    {
        return customer;
    }

    static void setBankBalance(double balance)
    {
        bankBalance = balance;
    }

    static double getBankBalance()
    {
        return bankBalance;
    }
}
