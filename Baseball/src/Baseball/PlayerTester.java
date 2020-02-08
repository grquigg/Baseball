package Baseball;


/**
 * Write a description of class PlayerTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerTester
{
    public static void main (String [] args)
    {
        Player p1 = new Player("Hernandez", "Catcher");
        p1.displayStats();
        p1.increment(1);
        p1.increment(2);
        p1.displayStats();
    }
}
