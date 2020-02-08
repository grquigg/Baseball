package Baseball;


/**
 * Write a description of class PlaysTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlaysTester
{
    public static void main (String [] args)
    {
        Player p = new Player("Fransisco", "Catcher");
        Plays test = new Plays(p);
        test.newAction();
        test.newAction();
        test.newAction();
        test.newAction();
    }
}
