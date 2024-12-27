import java.util.ArrayList;

public class Theatre
{
    private static String theatreName;
    ArrayList<Screens> screen;
    Theatre(String theatreName , ArrayList<Screens> screen)
    {
        this.theatreName = theatreName;
        this.screen = screen ;
    }

    public static String getTheatreName()
    {
        return theatreName;
    }

    static String setTheatreName()
    {
        return theatreName;
    }
}
