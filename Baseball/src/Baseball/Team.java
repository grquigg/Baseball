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
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.*;
import java.util.ArrayList;
import java.util.Scanner;
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
    }
    public void addPlayer(Player p)
    {
        roster.add(p);
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
        return roster.size() + 1;
    }
    public Player getPlayerFromLineup(int x)
    {
        return lineup.get(x);
    }
    public Player getPlayer(int x)
    {
        return roster.get(x);
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
}
