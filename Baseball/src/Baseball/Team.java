package Baseball;


/**
 * Write a description of class Team here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class Team extends XSSFObject
{
    private ArrayList<Player> roster = new ArrayList<Player>();
    public ArrayList<Player> lineup = new ArrayList<Player>();
    private ArrayList<Pitcher> pitching = new ArrayList<Pitcher>();
    private ArrayList<Player> listable = new ArrayList<Player>();
    private File storage;
    private XSSFSheet teamSheet;
    private String name;
    private boolean switchPitcher;
    private boolean lineupIsFull;
    private boolean activeRosterPrompt;
    private boolean activePitcherPrompt;
    private int index;
    private Pitcher starter;
    Scanner reader = new Scanner(System.in);
    public String [] hitterStats= {"G", "H", "AB", "2B", "3B", "HR", "RBI", "SO", "GO", "FO", "BB", "AVG"};
    public String [] pitcherStats = {"G", "GS", "W", "L", "SV", "ER", "H", "SO", "BB", "FO", "GO", "IP", "ERA"};
    
    public Team(String n)
    {  
        name = n;
        switchPitcher = true;
        lineupIsFull = true;
    }
    
    public Team(String n, int x)
    {
        name = n;
        switchPitcher = true;
        lineupIsFull = true;
        index = x;
    }
    public Team(String n, File f, int x) throws Exception
    {
        name = n;
        index = x;
        storage = f;
        FileInputStream fIS = new FileInputStream(storage);
        XSSFWorkbook teams = new XSSFWorkbook(fIS);
        int num;
        teamSheet = teams.getSheet(name);
        if (teamSheet == null)
        {
            FileOutputStream out = new FileOutputStream(storage);
            teamSheet = teams.createSheet(name);
            teams.write(out);
            out.close();
        }
    }
    public void addPlayer(String name, String position)
    {
        roster.add(new Player(name, position));
        if(position.equals("Pitcher")) {
        	int x = getPlayerIndexInRoster(name);
        	System.out.println(x);
        	addToRotation(new Pitcher(name, position));
        }
    }
    public void addPlayer(Player p)
    {
        roster.add(p);
        if(p.getPosition().equals("Pitcher")) {
        	addToRotation((Pitcher) p);
        }
    }
    public void removePlayer(int x)
    {
        roster.remove(x);
    }
    public void addToLineup(int x)
    {
        lineup.add(roster.get(x));
    }
    public void removeFromLineup(int x)
    {
        lineup.remove(x);
    }
    public void addToRotation(int x)
    {
        pitching.add((Pitcher) roster.get(x));
    }
    public void addToRotation(Pitcher t)
    {
        pitching.add(t);
    }
    public void removeFromRotation(int x)
    {
        pitching.remove(roster.get(x));
    }
    public int returnLineup()
    {
        return lineup.size();
    }
    public int returnRoster()
    {
        return roster.size();
    }
    public Player getPlayerFromLineup(int x)
    {
        return lineup.get(x);
    }
    public Player getPlayer(int x)
    {
        return roster.get(x);
    }
    public int getPlayerIndexInRoster(String x) {
    	System.out.println(x);
    	for (int i = 0; i < roster.size(); i++) {
    		if(roster.get(i).getName().equals(x)) {
    			System.out.println("Match");
    			return i;
    		}
    	}
    	return 10;
    }
    
    public ArrayList<Pitcher> getRotation() {
    	return pitching;
    }
    public String getName(int x)
    {
        return lineup.get(x).getName();
    }
    public void useStats(Player p)
    {
        Player statsPlayer = p;
        int fb = statsPlayer.getStat(1) - statsPlayer.getStat(3) - statsPlayer.getStat(4) - statsPlayer.getStat(5);
        for (int a = 0; a < fb; a++)
        {
            statsPlayer.addAction(0);
        }
        int sb = statsPlayer.getStat(3);
        for (int b = 0; b < sb; b++)
        {
            statsPlayer.addAction(1);
        }
        int tb = statsPlayer.getStat(4);
        for (int c = 0; c < tb; c++)
        {
            statsPlayer.addAction(2);
        }
        int hr = statsPlayer.getStat(5);
        for (int d = 0; d < hr; d++)
        {
            statsPlayer.addAction(3);
        }
        int bb = statsPlayer.getStat(10);
        for (int e = 0; e < bb; e++)
        {
            statsPlayer.addAction(4);
        }
        int so = statsPlayer.getStat(7);
        for (int f = 0; f < so; f++)
        {
            statsPlayer.addAction(5);
        }
        int go = statsPlayer.getStat(8);
        for (int g = 0; g < go; g++)
        {
            statsPlayer.addAction(6);
        }
        int fo = statsPlayer.getStat(9);
        for (int h = 0; h < fo; h++)
        {
            statsPlayer.addAction(7);
        }
    }
    public void usePitcherStats(Pitcher p)
    {
        Pitcher pitcher = p;
        for (Player l:  lineup)
        {
            l.addActionPitcher(pitcher);
        }
    }
    public void displayRoster()
    {
        System.out.println("Full roster: ");
        System.out.println("Name\tPosition");
        for (Player base: roster)
        {
            System.out.println(base.getName() + "\t" + base.getPosition());
        }
    }
    public String getTeamName()
    {
        return name;
    }
    public void displayLineup()
    {
        System.out.println("Lineup for game: ");
        System.out.println("Name\tPosition");
        for (Player order: lineup)
        {
            System.out.println(order.getName() + "\t" + order.getPosition());
        }
    }
    public void displayRotation()
    {
        System.out.println("Pitching Rotation: ");
        System.out.println("Name");
        for (Player rot: pitching)
        {
            System.out.println(rot.getName());
        }
    }
    public void displayTeamStats()
    {
        System.out.println("Stats: ");
        System.out.println("G\tH\tAB\t2B\t3B\tHR\tRBI\tSO\tGO\tFO\tBB\tAVG");
        for (Player p: roster)
        {
            p.displayStats();
        }
    }
    public Pitcher findPitcher(String x)
    {
        Pitcher newPitch = null;
        for (Player p: pitching)
        {
            if (p.getName().equals(x))
            {
                newPitch = (Pitcher) p;
            }
        }
        if (newPitch == null)
            return null;
            else
                return newPitch;
    }
//    public int findPitcherIndex(String x)
//    {
//        int newPitch = 0;
//        for (Player p: pitching)
//        {
//            if (p.getName().equals(x))
//            {
//                newPitch = (Pitcher) p;
//            }
//        }
//        if (newPitch == null)
//            return null;
//            else
//                return newPitch;
//    }
//    
    public Player findPlayer(String x)
    {
        Player newPlayer = null;
        for (Player p: roster)
        {
            if (p.getName().equals(x))
            {
                newPlayer = p;
            }
        }
        if (newPlayer == null)
            return null;
            else
                return newPlayer;
    }
    
    //add method for finding the index of a player
    public Pitcher getPitcher(int x)
    {
        return (Pitcher) pitching.get(x);
    }
    public void replacePitcherPrompt()
    {
        String input1;
        String input2;
        switchPitcher = false;
        Pitcher replace;
        while (!switchPitcher)
        {
            System.out.println("Would opposing team like to change pitchers? (Yes or No)");
            input1 = reader.nextLine();
                if (input1.equals("Yes"))
                {
                    System.out.println("Which pitcher would you like to use?");
                    input2 = reader.nextLine();
                    findPitcher(input2);
                    if (findPitcher(input2) != null)
                        {
                            replace = findPitcher(input2);
                            switchPitcher = true;
                        }
                        else 
                            System.out.println("Invalid input. Please try again.");
                }
                    else if (input1.equals("No"))
                    {
                        break;
                    }
        }
    }
    public void fillLineupPrompt()
    {
        lineupIsFull = false;
        for (Player p: roster)
        {
            listable.add(p);
        }
        
        for (int i = 0; i < listable.size(); i++)
        {
            System.out.println(i + "\t" + getPlayer(i).getName() + "\t" + getPlayer(i).getPosition());
        }
        String input3;
        int input4;
        int num;
        Player nP;
        reader.nextLine();
        while (!lineupIsFull && lineup.size() <= 9)
        {
            System.out.println("Would you like to add another player to the lineup?");
            input3 = reader.nextLine();
            if (input3.equals("Yes"))
            {
                System.out.println("Please select a number from the list above:");
                input4 = reader.nextInt();
                num = input4;
                if (num < roster.size())
                {
                    lineup.add(roster.get(num));
                }
            }
                else if (input3.equals("No"))
                {
                    lineupIsFull = true;
                    break;
                }
        }
    }
    public void addPlayerRosterPrompt()
    {
        Player p;
        String promptAnswer;
        String inName;
        String inPosition;
        boolean duplicates = false;
        activeRosterPrompt = true;
        reader.nextLine();
        while (activeRosterPrompt)
        {
            System.out.println("Would you like to add a player to the roster?");
            promptAnswer = reader.nextLine();
            if (promptAnswer.equals("Yes"))
            {
                System.out.println("Enter the name of the player:");
                inName = reader.nextLine();
                
                System.out.println("Enter the position of the player:");
                inPosition = reader.nextLine();
                
                p = new Player(inName, inPosition);
                addPlayer(p);
            }
                else if (promptAnswer.equals("No"))
                    activeRosterPrompt = false;
        }
        
    }
    public void addPitcherRosterPrompt()
    {
        Pitcher r;
        String promptResponse;
        String nName;
        activePitcherPrompt = true;
        while (activePitcherPrompt)
        {
            System.out.println("Would you like to add a pitcher to the rotation?");
            promptResponse = reader.nextLine();
            if (promptResponse.equals("Yes"))
            {
                System.out.println("Enter the name of the pitcher:");
                nName = reader.nextLine();
                r = new Pitcher(nName, "Pitcher");
                addToRotation(r);
                addPlayer((Player) r);
            }
                else if (promptResponse.equals("No"))
                {
                    activeRosterPrompt = false;
                    break;
                }
            reader.nextLine();
        }
    }
    public void setStartingPitcher(int x) {
    	starter = pitching.get(x);
    }
    public void setStartingPitcher()
    {
        Pitcher pS;
        int pNumber;
        int count = 1;
        for (Pitcher p: pitching)
        {
            System.out.println(count + "\t" + p.getName());
            count++;
        }
        System.out.println("Please enter the player's number based on the list above:");
        pNumber = reader.nextInt();
       
        starter = pitching.get(pNumber - 1);
        
    }
    public Pitcher getStartingPitcher()
    {
        return starter;
    }
    public int returnNumberOfPitchers()
    {
        return pitching.size();
    }
    public int returnIndex()
    {
        return index;
    }
    
	public void Unpack_data(XSSFSheet sheet, int split) {
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
            	addPlayer(newRow.getCell(0).getStringCellValue(), "Batter"); //if position is irrelevant, then why include it in the first place?
                for (int j = 1; j < 12; j++)
                {
                    int num = (int) newRow.getCell(j).getNumericCellValue();
                    //System.out.println(num);
                    getPlayer(playerIndex).setStat(j-1, num);
                }
                useStats(getPlayer(playerIndex)); //i don't know what this does
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
        	addToRotation(new Pitcher(newRow.getCell(0).getStringCellValue(), "Pitcher"));
            for (int k = 1; k < 11; k++)
            {
                int numeral = (int) newRow.getCell(k).getNumericCellValue();
                    //System.out.println(numeral);
                getPitcher(pitcherIndex).setPitchStat(k - 1, numeral);
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
	
    
    public void writeToFile(XSSFSheet sheet) {
    	int rowID = 1;
        XSSFRow row;
        for (int x = 0; x < returnRoster() - 1; x++)
        {
            row = sheet.createRow(rowID);
            Player p = getPlayer(x);
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
            row.getCell(y).setCellValue(pitcherStats[y - 1]);
        }
        rowID++;
        for (int z = 0; z < returnNumberOfPitchers(); z++)
        {
            row = sheet.createRow(rowID);
            Pitcher p = getPitcher(z);
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
    
}
