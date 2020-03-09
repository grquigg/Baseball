package Baseball;


/**
 * Write a description of class InningTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Scanner;
public class InningTester
{
    public static void main (String [] args)
    {
        Team whitesox = new Team("White Sox");
        Team tigers = new Team("Tigers");
        Pitcher p = new Pitcher("Correa", "Pitcher");
        tigers.addPlayer(p);
        tigers.addToRotation(0);
        Inning first = new Inning(whitesox, tigers, 0, 0,tigers.getPitcher(0));
        whitesox.addPlayer("Hernandez", "Catcher");
        whitesox.addPlayer("Jake", "Left Fielder");
        whitesox.addToLineup(0);
        whitesox.addToLineup(1);
        first.runInning(0, 0);
        whitesox.displayTeamStats();
        System.out.println("G\tGS\tW\tL\tSV\tER\tH\tSO\tBB\tFO\tGO\tIP\tERA");
        p.displayPitchStats();
        //needs info for the team in order to run
    }
}
