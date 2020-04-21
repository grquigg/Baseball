package Baseball;


/**
 * Write a description of class GameTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class GameTester
{
    public static void main (String [] args) throws Exception
    {
        GameWithFile newGame = new GameWithFile();
        Commands.rewriteFile();
        //commandLine.addTeam();
        System.out.println("Done.");
        newGame.chooseTeams();
        System.out.println("Done.");
        newGame.setup();
        System.out.println("Done.");
        newGame.game();
        System.out.println("Done.");
        newGame.postGame();
        System.out.println("Done.");
    }
}
