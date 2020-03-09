package Baseball;


/**
 * Write a description of class Inning here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Inning
{
    private int score;
    private Team team1;
    private Team team2;
    private int outs;
    private int inning;
    private boolean base1;
    private boolean base2;
    private boolean base3;
    private boolean[] bases = new boolean[3];
    private Pitcher pitcher;
    Scanner reader = new Scanner(System.in);
    private ArrayList<Plays> plays = new ArrayList<Plays>();
    private int order;
    private int count;
    
    public Inning(Team t, Team u, int x, int s, Pitcher opp)
    {
        team1 = t;
        team2 = u;
        order = x;
        outs = 0;
        base1 = false;
        base2 = false;
        base3 = false;
        score = s;
        pitcher = (Pitcher) opp;
    }
    
    /**
     * This method takes in an integer n and a boolean b and handles the different cases for getting runners on and off base and scoring. We treat the bases as an array of booleans where
     * if a "runner" falls off the end of the array then they are assumed to have scored. If b is true then a new baserunner is added to the (n-1)th spot in the array.
     * @param n - the number of places for the base runners to move. 
     * @param b - whether or not a new base runner should be added into the array. 
     */
    public void baseRunner(int n, boolean b) {

    	for (int i = 2; i >= 0; i--) {
    		if (bases[i]) {
    			if (i + n >= bases.length) {
    				System.out.print("A runner has scored! ");
    				team1.getPlayerFromLineup(order).increment(6);
    				pitcher.incrementPitch(5);
    				bases[i] = false;
    			}
    			else {
    				boolean temp = bases[i];
    				bases[i+n] = temp;
    				bases[i] = !bases[i];
    			}
    		}
    	}
		if (b && n < 3) {
			bases[n-1] = true;
		}
    	if (bases[0]) {
    		System.out.print("Runner on 1st. ");
    	}
    	
    	if (bases[1]) {
    		System.out.print("Runner on 2nd. ");
    	}
    	
    	if (bases[2]) {
    		System.out.print("Runner on 3rd. ");
    	}
    }
    
    /**This method plays through an inning i of baseball for the Team that it is assigned to. The while loop goes through the order of the lineup that was given (starting at o) and goes through until
     * three outs have been reached.
     * @param i - the inning number
     * @param o - i don't actually remember what this does lmao 
     */
    public void runInning(int i, int o) {
    	inning = i + 1; //this is the correct inning order
    	System.out.println(team1.getTeamName() + " are batting in Inning " + inning + ".");
    	reader.nextLine();
    	count = 0;
    	while ((order <= team1.returnLineup()) && (outs < 3)) {
    		plays.add(new Plays(team1.getPlayerFromLineup(order)));
    		plays.get(count).newAction();
    		String temp = plays.get(count).mostRecent();
    		
    		if (temp.equals("Single"))
    		{
    			System.out.print(team1.getName(order) + " has singled. ");
                pitcher.incrementPitch(6);
                baseRunner(1, true);
    		}
    		else if(temp.equals("Double")) {
    			System.out.print(team1.getName(order) + " has doubled. ");
    			pitcher.incrementPitch(6);
    			baseRunner(2, true);
    		}
    		else if (temp.equals("Triple")) {
    			System.out.print(team1.getName(order) + " has tripled. ");
    			pitcher.incrementPitch(6);
    			baseRunner(3, true);
    		}
    		else if (temp.equals("Home Run")) {
    			System.out.print(team1.getName(order) + " has hit a home run! ");
    			pitcher.incrementPitch(6);
    			baseRunner(4, true);
    		}
            else if (temp.equals("Strikeout"))
            {
                System.out.print(team1.getName(order) + " has struck out. ");
                pitcher.incrementPitch(7);
                outs++;
            }
            else if (temp.equals("Flyout")) {
            	if (outs < 2) {
            		if (bases[2]) {
            			System.out.print(team1.getName(order) + " has hit a sacrifice fly.");
            		}
            		else {
            			System.out.print(team1.getName(order) + " has flied out.");
            		}
            		baseRunner(0, false);
            	}
            	else {
            		System.out.print(team1.getName(order) + " has flied out.");
            	}
        		pitcher.incrementPitch(9);
        		outs++;
            }
            else if (temp.equals("Groundout"))
            {
                System.out.print(team1.getName(order) + " grounds out. ");
                pitcher.incrementPitch(10);
                if (outs < 2) {
                	baseRunner(1, false);
                }
                outs++;
            }
            else if (temp.equals("Walk")) {
            	System.out.print(team1.getName(order) + " has walked. ");
                pitcher.incrementPitch(8);
                baseRunner(1, true);
            }
    		System.out.println(outs + " out.");
    		reader.nextLine();
    		order = (order + 1) % team1.returnLineup();
    	}
    }
    /**
     * This method returns the score of an inning to the user.
     * @return the score of the inning.
     */
    public int reportScore()
    {
        return score;
    }
    
    /**
     * Returns the last player to have batted in the inning. 
     * @return the index in the lineup of the last player to have batted in the inning. 
     */
    public int getOrder()
    {
        return order;
    }
    
    public void prompt()
    {
        String input;
        input = reader.nextLine();
        if (input.equals("Change Pitchers"))
        {
            team2.replacePitcherPrompt();
        }
    }
}
