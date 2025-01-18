package ATM.Notes;

public class Notes implements Cloneable
{
    private String note;// variable to store the type of the note ( eg:2000 or 500 )
    private double count; // variable to store the count of the note

    //Constructor
    protected Notes(String note , double count)
    {
        this.count=count;
        this.note=note;
    }


    //Getters and Setters

    public String getNote()
    {
        return note;
    }

    public double getCount()
    {
        return count;
    }

    public void setCount(double count)
    {
        this.count = count;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
