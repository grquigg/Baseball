package Baseball;


/**
 * Write a description of class PitcherTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class PitcherTester
{
    public static void main (String [] args)
    {
        Pitcher Verlander = new Pitcher("Justin Verlander", "Pitcher");
        Verlander.displayStats();
        Verlander.displayPitchStats();
    }
}
