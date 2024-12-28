import java.time.LocalDateTime;
import java.util.HashMap;

public class ShowPOJO
{
    private static LocalDateTime startTime;
    private static LocalDateTime endTime;
    private static LocalDateTime date;
    HashMap<String ,Screens> show = new HashMap<>();

    public static LocalDateTime getStarttime() {
        return startTime;
    }

    public static void setStarttime(LocalDateTime  starttimee) {
        startTime = starttimee;
    }


    public static LocalDateTime getEndtime() {
        return endTime;
    }

    public static void setEndtime(LocalDateTime endtimee) {
        endTime = endtimee;
    }

    // Getter and Setter for date
    public static LocalDateTime getDate() {
        return date;
    }

    public static void setDate(LocalDateTime datee) {
        date = datee;
    }
}
