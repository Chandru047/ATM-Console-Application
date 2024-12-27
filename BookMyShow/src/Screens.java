public class Screens
{
    private String screenName;
    private String noOfSeats;
    Screens(String screenName , String noOfSeats)
    {
        this.screenName = screenName ;
        this.noOfSeats = noOfSeats ;
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
}
