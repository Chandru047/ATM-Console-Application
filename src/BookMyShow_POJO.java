import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookMyShow_POJO
{
    private static final ArrayList<Admin_POJO> adminList= new ArrayList<>();
    private static final ArrayList<User_POJO> userList = new ArrayList<>();
    private static ArrayList<Movie> movie = new ArrayList<>();
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static String currentId;


    static ArrayList<Admin_POJO> getAdminList()
    {
        return adminList;
    }

    static ArrayList<User_POJO> getUserList()
    {
        return userList;
    }

    public static String getCurrentId()
    {
        return currentId ;
    }
    public static void setCurrentId(String id)
    {
        currentId = id ;
    }

    public static DateTimeFormatter getDateFormatter()
    {
        return dateFormatter;
    }

    public static DateTimeFormatter getTimeFormatter()
    {
        return timeFormatter ;
    }

    public static ArrayList<Movie> getMovie() {
        return movie;
    }

    public static void setMovie(ArrayList<Movie> movie) {
        BookMyShow_POJO.movie = movie;
    }
}
