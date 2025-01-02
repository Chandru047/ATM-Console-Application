import java.util.ArrayList;

public class Theatre_POJO
{
    private String theatreName;
    private String location;
    private ArrayList<ScreenPOJO> screen;

    public String getTheatreName()
    {
        return theatreName;
    }

    public void setTheatreName(String theatreName)
    {
        this.theatreName = theatreName;
    }

    public String getTheatreLocation()
    {
        return location;
    }

    public void setTheatreLocation(String location)
    {
        this.location = location;
    }

    public ArrayList<ScreenPOJO> getScreen() {
        return screen;
    }

    public void setScreen(ArrayList<ScreenPOJO> screen) {
        this.screen = screen;
    }
}
