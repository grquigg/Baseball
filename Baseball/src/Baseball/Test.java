package Baseball;


/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    private String name;
    private int [] array;
    private int index;
    private String type;
    public Test(String n, int [] ray, String t, int i)
    {
        name = n;
        array = ray;
        type = t;
        index = i;
    }
    public int getIndex()
    {
        return index;
    }
    public String getName()
    {
        return name;
    }
    public String getType()
    {
        return type;
    }
}
