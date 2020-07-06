package Baseball;


/**
 * Write a description of class GameWithFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

/*TO-DO
 * Figure out why the scores in each inning aren't updating properly
 * Figure out a command for skipping the adding player to roster menu nonsense
 * Figure out a good UI for this game
 * Figure out how to connect to a database
 * Figure out how to create a default lineup
 * 
 */
public class GameWithFile
{
	Scanner reader = new Scanner(System.in);
    private int scoreA;
    private int scoreB;
    private int [] scoreboardA;
    private int [] scoreboardB;
    private Team teamA;
    private Team teamB;
    private Commands commands;
    private Pitcher pitcherA;
    private Pitcher pitcherB;
    private ArrayList<Inning> team1; //an array list of the various results 
    private ArrayList<Inning> team2;
    private int indexSplitA;
    private int indexSplitB;
    
    
    public GameWithFile()
    {
        scoreA = 0;
        scoreB = 0;
        scoreboardA = new int[9];
        scoreboardB = new int[9];
        commands = new Commands(); //???
        team1 = new ArrayList<Inning>(); //each team has its own arraylist of innings
        team2 = new ArrayList<Inning>();
    }
    
    /*
     * This method gives the user a prompt for choosing teams 
     */
    public void chooseTeams() throws Exception
    {
        XSSFWorkbook workbook = locateFile();
		int number = workbook.getNumberOfSheets();
		
        if (number == 0) {
        	promptForZeroSheets(number);
        }
        else if (number == 1) {
        	promptForOneSheet(number);
        }
        FileInputStream fIS = new FileInputStream("teams.xlsx");
        workbook = new XSSFWorkbook(fIS);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++)
            {
                System.out.println(i + "\t" + workbook.getSheetAt(i).getSheetName());
            }
            int TeamAIndex;
            System.out.println("Which team would you like to have as Team 1 (the visiting team)?");
            TeamAIndex = reader.nextInt();
            while (TeamAIndex < 0 || TeamAIndex >= workbook.getNumberOfSheets()) {
            	System.out.println("Invalid entry. Please choose a diferent index");
            	TeamAIndex = reader.nextInt();
            }
            teamA = new Team(workbook.getSheetAt(TeamAIndex).getSheetName(), TeamAIndex);
            XSSFSheet teamASheet = workbook.getSheetAt(TeamAIndex);
            teamA.Unpack_data(teamASheet, indexSplitA);
            
            int TeamBIndex;
            System.out.println("Which team would you like to have as Team 2?");
            TeamBIndex = reader.nextInt();
            while (TeamBIndex < 0 || TeamBIndex >= workbook.getNumberOfSheets() || TeamBIndex == TeamAIndex) {
            	System.out.println("Invalid entry. Please choose a diferent index");
            	TeamBIndex = reader.nextInt();
            }
            teamB = new Team(workbook.getSheetAt(TeamBIndex).getSheetName(), TeamBIndex);
            XSSFSheet teamBSheet = workbook.getSheetAt(TeamBIndex);
            teamB.Unpack_data(teamBSheet, indexSplitB);
            System.out.println("Teams have been set");
        
    }                


	private void promptForZeroSheets(int n) throws Exception{
		String response;
        System.out.println("Not enough data to properly execute. Create a new spreadsheet?");
        response = reader.nextLine();
        if (response.equals("Yes"))
        {
            commands.addTeam();
            n++;
        }
            else if (response.equals("No"))
            {
                System.out.println("Error. Please exit the program and try again.");
                //again, not sure what to do here
                //exit game
            }
        promptForOneSheet(n);
	}

	private void promptForOneSheet(int n) throws Exception{
		String response;
        System.out.println("Not enough data to properly execute. Create a new spreadsheet?");
        response = reader.nextLine();
        if (response.equals("Yes"))
        {
            commands.addTeam();
            n++;
        }
            else if (response.equals("No"))
            {
                System.out.println("Error. Please exit the program and try again.");
                //again, not sure what to do here
                //exit game
            }
	}

	public XSSFWorkbook locateFile() throws FileNotFoundException, IOException {
		File fs = new File("teams.xlsx");
        XSSFWorkbook workbook = null;
        if (!fs.exists())
        {
            workbook = new XSSFWorkbook();
            FileOutputStream fOS = new FileOutputStream("teams.xlsx");
            workbook.write(fOS);
            fOS.close();
        }
        try {
            workbook = new XSSFWorkbook("teams.xlsx");
        }
        catch (EmptyFileException e) {
        	try {
				commands.rewriteFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        workbook =  new XSSFWorkbook("teams.xlsx");
        return workbook;
        
	}
	
    public void setup()
    {
    	System.out.println("Setup method");
        if (teamA != null && teamB != null)
        {
            System.out.println("Enter data for Team 1.");
            readyTeam(teamA);
            pitcherA = teamA.getStartingPitcher();
            
            System.out.println("Enter data for Team 2.");
            readyTeam(teamB);
            pitcherB = teamB.getStartingPitcher();
            System.out.println("Ready to start!");
            
            //not sure what this code does yet, but it seems redundant
            teamA.usePitcherStats(pitcherB);
            teamB.usePitcherStats(pitcherA);
        }
    }

	private void readyTeam(Team t) {
		t.addPitcherRosterPrompt();
		t.addPlayerRosterPrompt();
		t.fillLineupPrompt();
		t.setStartingPitcher();
		t.displayLineup();
	}
	
    public void game()
    {
        for (int c = 0; c < teamA.returnLineup(); c++)
        {
            teamA.getPlayerFromLineup(c).increment(0);
        }
        for (int d = 0; d < teamB.returnLineup(); d++)
        {
            teamB.getPlayerFromLineup(d).increment(0);
        }
        pitcherA.incrementPitch(0);
        pitcherA.incrementPitch(1);
        pitcherB.incrementPitch(0);
        pitcherB.incrementPitch(1);
        int tempOrderA = 0;
        int tempOrderB = 0;
//        team1.add(new Inning(teamA, teamB, 0, 0, pitcherB));
//        team1.get(0).runInning(0, 0);
//        scoreboardA[0] = team1.get(0).reportScore() - scoreA;
//        scoreA = team1.get(0).reportScore();
//        
//        team2.add(new Inning(teamB, teamA, 0, 0, pitcherA));
//        team2.get(0).runInning(0, 0);
//        scoreboardB[0] = team2.get(0).reportScore() - scoreB;
//        scoreB = team2.get(0).reportScore();
//        
        teamA.displayTeamStats();
        teamB.displayTeamStats();
        for (int i = 0; i < 1; i++)
        {
            team1.add(new Inning(teamA, teamB, tempOrderA, scoreA, pitcherB));
            //team1.get(i).runInning(i, 0);
            teamA.displayTeamStats();
            scoreboardA[i] = team1.get(i).reportScore() - scoreA;
            scoreA = team1.get(i).reportScore();
            System.out.println(scoreA);
            tempOrderA = team1.get(i).getOrder();
            
            team2.add(new Inning(teamB, teamA, tempOrderB, scoreB, pitcherA));
            //team2.get(i).runInning(i, 0);
            teamB.displayTeamStats();
            scoreboardB[i] = team2.get(i).reportScore() - scoreB;
            scoreB = team2.get(i).reportScore();
            System.out.println(scoreB);
            tempOrderB = team2.get(i).getOrder();
        }
        System.out.println(scoreA + " " + scoreB);
        if (scoreA == scoreB)
        {
            while (scoreA == scoreB)
            {
                int numeral = team1.size();
                tempOrderA = team1.get(numeral - 1).getOrder();
                team1.add(new Inning(teamA, teamB, tempOrderA, scoreA, pitcherB));
                //team1.get(numeral).runInning(numeral, 0);
                //teamA.displayTeamStats();
                //scoreboardA[numeral] = team1.get(numeral).reportScore() - scoreA;
                scoreA = team1.get(numeral).reportScore();
                
                tempOrderB = team2.get(numeral - 1).getOrder();
                team2.add(new Inning(teamB, teamA, tempOrderB, scoreB, pitcherA));
                //team2.get(numeral).runInning(numeral, 0);
                //teamB.displayTeamStats();
                //scoreboardB[numeral] = team2.get(numeral).reportScore() - scoreB;
                scoreB = team2.get(numeral).reportScore();
                    }
                }
                
        if (scoreA > scoreB)
        {
            System.out.println(teamA.getTeamName() + " has won against " + teamB.getTeamName());
            pitcherA.incrementPitch(2);
            pitcherB.incrementPitch(3);
        }
            else if (scoreA < scoreB)
            {
                System.out.println(teamB.getTeamName() + " has won against " + teamA.getTeamName());
                pitcherB.incrementPitch(2);
                pitcherA.incrementPitch(3);
            }
    }
    
    public void postGame() throws Exception
    {
        FileInputStream fIS = new FileInputStream("teams.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fIS);
        
        teamA.writeToFile(book.getSheetAt(teamA.returnIndex()));
        teamB.writeToFile(book.getSheetAt(teamB.returnIndex()));

        System.out.println("Data has been entered. Game is finished.");
        FileOutputStream out = new FileOutputStream("teams.xlsx");
        book.write(out);
        out.close();
    }
}

