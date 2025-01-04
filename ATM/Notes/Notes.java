package Notes;

public class Notes implements Cloneable
{
    private String note;
    private double count;

    protected Notes(String note , double count)
    {
        this.count=count;
        this.note=note;
    }



    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
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
        return  super.clone();
    }
}
