import java.util.ArrayList;
import java.util.HashMap;

public class Screens
{
    private String screenName;
    private String noOfSeats;
    private static HashMap<Character , ArrayList<String>> seatingArrangement = new HashMap<>();
    Screens(String screenName , String noOfSeats ,HashMap<Character , ArrayList<String>> seatingArrangement)
    {
        this.screenName = screenName ;
        this.noOfSeats = noOfSeats ;
        this.seatingArrangement = seatingArrangement;

    }

    void setScreenName(String name)
    {
        this.screenName = name ;
    }

    void setNoOfSeats(String name)
    {
        this.noOfSeats = noOfSeats ;
    }

    String getScreenName()
    {
        return screenName ;
    }

    String getNoOfSeats()
    {
        return noOfSeats;
    }

    public static HashMap<Character, ArrayList<String>> getSeatingArrangement()
    {
        return seatingArrangement;
    }

    public void setSeatingArrangement(HashMap<Character, ArrayList<String>> seatingArrangement)
    {
        this.seatingArrangement = seatingArrangement;
    }
}
