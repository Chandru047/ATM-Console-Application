import java.util.ArrayList;

public class Admin {
    private static final double PIN = 1920;
    private String id ;
    private String pass;
    private ArrayList<String> transactions;

    static double getPIN(){
        return PIN;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {

        this.id = id;
    }


    public String getPass() {
        return pass;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }





}
