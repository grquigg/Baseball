package Baseball;


/**
 * Write a description of class Demo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;
public class Demo
{
    private ArrayList<String> list = new ArrayList<String>();
    private Random rn = new Random();
    private int num = 0;
    private int outs;
    private int c = 0;
    public Demo()
    {
        outs = 0;
        list.add("Single");
        list.add("Double");
        list.add("Triple");
        list.add("Home Run");
        list.add("Groundout");
        list.add("Flyout");
        list.add("Strikeout");
        list.add("Walk");
        num = 8;
    }
    public String newAction()
    {
        int a;
        a = rn.nextInt(num);
        String action = list.get(a);
        list.add(list.get(a));
        if (action.equals("Single") || action.equals("Double") || action.equals("Triple") || action.equals("Home Run"))
        {
            list.add(0, "Groundout");
            list.add(0, "Strikeout");
            list.add(0, "Flyout");
            num += 3;
            c++;
        }
        num++;
        return action;
    }
    public void runInning()
    {
        String s;
        while (outs < 3)
        {
            s = newAction();
            if (s.equals("Groundout") || (s.equals("Flyout")) || (s.equals("Strikeout")))
            {
                System.out.println(s);
                outs++;
            }
                else
                {
                    System.out.println(s);
                }
        }
        System.out.println("End of inning half.");
        if (c != 0)
        {
            for (int i = 0; i < (c * 3); i++)
            {
                list.remove(0);
            }
        }
        num = num - (3 * c);
        outs = 0;
    }
}
