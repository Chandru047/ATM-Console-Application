import java.util.ArrayList;
import java.util.HashMap;

public class MoviePOJO
{
    private static String movieName ;
    private static HashMap<String , ArrayList<TheatrePOJO>> movie = new HashMap<>();

    MoviePOJO(String movieNamee)
    {
        movieName = movieNamee;
    }


    public String getMovieName()
    {
        return movieName;
    }

    public static HashMap<String , ArrayList<TheatrePOJO>> getList()
    {
        return  movie ;
    }
}
