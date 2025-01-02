import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Screens
{
    private String screenName;
    private String noOfSeats;
    private   HashMap<Character , ArrayList<String>> seatingArrangement = new HashMap<>();

    Screens(String screenName , String noOfSeats ,HashMap<Character , ArrayList<String>> seatingArrangement)
    {
        this.screenName = screenName ;
        this.noOfSeats = noOfSeats ;
        this.seatingArrangement = seatingArrangement;

    }

    String getScreenName()
    {
        return screenName ;
    }

    String getNoOfSeats()
    {
        return noOfSeats;
    }

    public   HashMap<Character, ArrayList<String>> getSeatingArrangement()
    {
        return seatingArrangement;
    }

}
