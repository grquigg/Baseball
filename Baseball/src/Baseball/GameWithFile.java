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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

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
		
        if (number <= 1) {
        	promptForSheets(number);
        }
        FileInputStream fIS = new FileInputStream("teams.xlsx");
        workbook = new XSSFWorkbook(fIS);
        if (number >= 2)
        {
            for (int i = 0; i < number; i++)
            {
                System.out.println(i + "\t" + workbook.getSheetAt(i).getSheetName());
            }
            int TeamAIndex;
            System.out.println("Which team would you like to have as Team 1 (the visiting team)?");
            TeamAIndex = reader.nextInt();
            teamA = new Team(workbook.getSheetAt(TeamAIndex).getSheetName(), TeamAIndex);
            XSSFSheet teamASheet = workbook.getSheetAt(TeamAIndex);
            Unpack_data(teamA, teamASheet, indexSplitA);
            
            int TeamBIndex;
            System.out.println("Which team would you like to have as Team 2?");
            TeamBIndex = reader.nextInt();
            teamB = new Team(workbook.getSheetAt(TeamBIndex).getSheetName(), TeamBIndex);
            XSSFSheet teamBSheet = workbook.getSheetAt(TeamBIndex);
            Unpack_data(teamB, teamBSheet, indexSplitB);
            System.out.println("Teams have been set");
        }
    }                

	//indexSplit seems to be redundant. I'm not 100% about the documentation for the XSSFSpreadsheet, but we could write something
	/*more along the lines of this:
	 * while (the first cell in the new row is not blank) {
	 * 		call the method to have the program read data from the spreadsheet and store it in the player's values
	 * }
	 * row = next row
	 * while (the first cell in the new row is not blank) {
	 * 		call the method to have the program read data from the spreadsheet and store it
	 * }
	 * 
	 * Writing the code like this makes life easier for two reasons. The first is that it's a step forward in the right direction of getting a 
	 * generic XSSFRow reader that simply given the contents of a row and an index turns that data into an array and returns it. This is extremely useful in the type of data analysis
	 * projects that are the crux of your foundation as a programmer. 
	 * 
	 * The second reason that we want to do this is to get rid of rather repetitive and redundant code that makes things feel clunky and rather poorly thought out.
	 * The code called in the first half of the for loop and the code in the second for loop essentially do the exact same thing in that they read data from a file until 
	 * they encounter a blank row. 
	 * 
	 */
	private void Unpack_data(Team team, XSSFSheet sheet, int split) {
		// TODO Auto-generated method stub
        Iterator < Row > rowIterator = sheet.iterator();
        int pitcherIndex = 0;
        int playerIndex = 0;
        int rowid = 0;
        split = 0;
        boolean end = false;
        XSSFRow newRow;
        newRow = (XSSFRow) rowIterator.next(); //skips over the line of headers
        //code has been split up into two while loop blocks; the first reads the data of the batters, while the second reads the data of the pitchers
        while (rowIterator.hasNext()) //iterates through each row in the excel spreadsheet
        {
        	System.out.println("Beginning of the first while loop");
            newRow = (XSSFRow) rowIterator.next(); //new row equals next
            System.out.println(newRow.getRowNum());
            if (newRow.getCell(0).getCellType() != CellType.BLANK)
            {
            	team.addPlayer(newRow.getCell(0).getStringCellValue(), "Batter"); //if position is irrelevant, then why include it in the first place?
                for (int j = 1; j < 12; j++)
                {
                    int num = (int) newRow.getCell(j).getNumericCellValue();
                    //System.out.println(num);
                    team.getPlayer(playerIndex).setStat(j-1, num);
                }
                team.useStats(team.getPlayer(playerIndex)); //i don't know what this does
                playerIndex++;
            }
            else if (newRow.getCell(0).getCellType() == CellType.BLANK)
            {
            	break;
            }
        }
        while (rowIterator.hasNext()) {
        	System.out.println("Beginning of the second while loop");
        	newRow = (XSSFRow) rowIterator.next();
        	team.addToRotation(new Pitcher(newRow.getCell(0).getStringCellValue(), "Pitcher"));
            for (int k = 1; k < 11; k++)
            {
                int numeral = (int) newRow.getCell(k).getNumericCellValue();
                    //System.out.println(numeral);
                team.getPitcher(pitcherIndex).setPitchStat(k - 1, numeral);
            }
            pitcherIndex++;
        }
	}
	
	/**
	 * This method should take in a XSSFSheet as a parameter and read a number of values n until the next row is blank 
	 * @param sheet - the XSSFSheet to be read from
	 * @param n - the number of values per line that we will be reading from the spreadsheet
	 * @return the ArrayList of arrays representing the data from the spreadsheet 
	 */
	private ArrayList<int []> readUntilBlank(XSSFSheet sheet, int n) {
		Iterator < Row > rowIterator = sheet.iterator();
		XSSFRow row = sheet.getRow(0);
		XSSFRow next = (XSSFRow) rowIterator.next();
		ArrayList<int []> arr = new ArrayList<int []>();
		while (rowIterator.hasNext()) {
			int [] data = new int[n];
			for (int i = 0; i < n; i++) {
				data[i] = (int) row.getCell(i).getNumericCellValue();
			}
			arr.add(data);
		}
		return arr;
	}
	
	
	/**
	 * This method does not use the Row iterator class; instead, it starts at the index of row x and reads values until a blank line is reached.
	 * @param sheet - the XSSFSheet to be read from
	 * @param x - the index to start reading from
	 * @param n - the number of value per line that we will be reading from the spreadsheet
	 * @return the ArrayLis of arrays representing the data from the spreadsheet
	 */
	private ArrayList<int []> readUntilBlank(XSSFSheet sheet, int x, int n) {
		XSSFRow row = sheet.getRow(x);
		ArrayList<int []> arr = new ArrayList<int []>();
		while (sheet.getRow(x+1).getCell(0).getCellType() != CellType.BLANK) {
			int [] array = new int[12];
			for (int i = 0; i < 12; i++) {
				array[i] = (int) row.getCell(i).getNumericCellValue();
			}
			arr.add(array);
		}
		return arr;
	}

	public void promptForSheets(int number) throws Exception {
		String response;
		for (int i = 0; i < number; i++) {
            System.out.println("Not enough data to properly execute. Create a new spreadsheet?");
            response = reader.nextLine();
            if (response.equals("Yes"))
            {
                commands.addTeam();
                number = 1;
            }
                else if (response.equals("No"))
                {
                    System.out.println("Error. Please exit the program and try again.");
                    //again, not sure what to do here
                    //exit game
                }
		}
	}

	public XSSFWorkbook locateFile() throws FileNotFoundException, IOException {
		File fs = new File("teams.xlsx");
        XSSFWorkbook workbook;
        if (!fs.exists())
        {
            workbook = new XSSFWorkbook();
            FileOutputStream fOS = new FileOutputStream("teams.xlsx");
            workbook.write(fOS);
            fOS.close();
        }
        workbook = new XSSFWorkbook("teams.xlsx");
        return workbook;
        
	}
	
    public void setup()
    {
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
	
	public void runInn() {
		
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
        for (int i = 0; i < 9; i++)
        {
            team1.add(new Inning(teamA, teamB, tempOrderA, scoreA, pitcherB));
            team1.get(i).runInning(i, 0);
            teamA.displayTeamStats();
            scoreboardA[i] = team1.get(1).reportScore() - scoreA;
            scoreA = team1.get(i).reportScore();
            tempOrderA = team1.get(i).getOrder();
            
            team2.add(new Inning(teamB, teamA, tempOrderB, scoreB, pitcherA));
            team2.get(i).runInning(i, 0);
            teamB.displayTeamStats();
            scoreboardB[i] = team2.get(1).reportScore() - scoreB;
            scoreB = team2.get(i).reportScore();
            tempOrderB = team2.get(i).getOrder();
        }
        if (scoreA == scoreB)
        {
            while (scoreA == scoreB)
            {
                int numeral = team1.size();
                tempOrderA = team1.get(numeral - 1).getOrder();
                team1.add(new Inning(teamA, teamB, tempOrderA, scoreA, pitcherB));
                team1.get(numeral).runInning(numeral, 0);
                //teamA.displayTeamStats();
                //scoreboardA[numeral] = team1.get(numeral).reportScore() - scoreA;
                scoreA = team1.get(numeral).reportScore();
                
                tempOrderB = team2.get(numeral - 1).getOrder();
                team2.add(new Inning(teamB, teamA, tempOrderB, scoreB, pitcherA));
                team2.get(numeral).runInning(numeral, 0);
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
    
    
    public void writeToFile(Team team, XSSFSheet sheet) {
    	int rowID = 1;
        XSSFRow row;
        for (int x = 0; x < teamA.returnRoster() - 1; x++)
        {
            row = sheet.createRow(rowID);
            Player p = teamA.getPlayer(x);
            row.createCell(0).setCellValue(p.getName());
            for (int j = 1; j < 12; j++)
            {
                row.createCell(j);
                row.getCell(j).setCellValue(p.getStat(j - 1));
            }
            row.createCell(12);
            row.getCell(12).setCellValue(p.getAvg());
            rowID++;
        }
        row = sheet.createRow(rowID);
        row.createCell(0);
        for (int y = 1; y < 14; y++)
        {
            row.createCell(y);
            row.getCell(y).setCellValue(teamA.pitcherStats[y - 1]);
        }
        rowID++;
        for (int z = 0; z < teamA.returnNumberOfPitchers(); z++)
        {
            row = sheet.createRow(rowID);
            Pitcher p = teamA.getPitcher(z);
            row.createCell(0);
            row.getCell(0).setCellValue(p.getName());
            for (int s = 1; s < 12; s++)
            {
                row.createCell(s);
                row.getCell(s).setCellValue(p.getPitchStat(s - 1));
            }
            row.createCell(12);
            row.getCell(12).setCellValue(p.getIP());
            row.createCell(13);
            row.getCell(13).setCellValue(p.getEra());
            rowID++;
        }
    }
    
    public void postGame() throws Exception
    {
        FileInputStream fIS = new FileInputStream("teams.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fIS);
        
        writeToFile(teamA, book.getSheetAt(teamA.returnIndex()));
        writeToFile(teamB, book.getSheetAt(teamB.returnIndex()));

        System.out.println("Data has been entered. Game is finished.");
        FileOutputStream out = new FileOutputStream("teams.xlsx");
        book.write(out);
        out.close();
    }
}

