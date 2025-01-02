import java.util.ArrayList;
import java.util.HashMap;

public class TheatrePOJO
{
    private  String theatreName;
    private ArrayList<Screens> screen;
    HashMap<MoviePOJO, ArrayList<ShowPOJO>> movieArrayListHashMap = new HashMap<>();
    TheatrePOJO(String theatreName , ArrayList<Screens> screen)
    {
        this.theatreName = theatreName;
        this.screen = screen ;
    }

    public  String getTheatreName()
    {
        return theatreName;
    }

    public ArrayList<Screens> getScreen()
    {
        return screen;
    }
}
