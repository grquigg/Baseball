package Baseball;


/**
 * This class is the basic "data structure" of the program and holds all of the statistics needed for the player. 
 * The various attributes that each player has include:
 * 1. A name
 * 2. A position
 * 3. An array of statistics and a double for the batting average
 * 4. An ArrayList of strings called temp that is all of the actions/plays that the player can make
 * 5. An ArrayList of strings called atBat that is all of the actions that the player has currently done within a game. 
 * 
 * @author Greg Quigg
 */

import java.util.ArrayList;

public class Player
{
    private int[] stats;
    private double avg;
    private String name;
    private String position;
    

    private ArrayList<String> atBat = new ArrayList<String>(); //this array is all of the actions that the player has currently done
    private ArrayList<String> temp = new ArrayList<String>();
    
    
    public Player(String n, String p)
    {
        name = n;
        position = p;
        stats = new int[11];
        
        //temp always starts out the same regardless of the previous history of the player 
        temp.add("Single");
        temp.add("Double");
        temp.add("Triple");
        temp.add("Home Run");
        temp.add("Walk");
        temp.add("Strikeout");
        temp.add("Groundout");
        temp.add("Flyout");
        avg = 0.0;
        System.out.println("Created players");
    }
    
    /**
     * This method allows for the user to get the name of the player
     * @return the name of the player
     */
    public String getName()
    {
        return name;
    }
    
    
    public String toPlayerString() {
    	String res = "";
    	for (int i = 0; i < stats.length; i++) {
    		res += stats[i] + ",";
    	}
		return res;
    	
    }
    /**
     * This method allows for the user to get the position of the player
     * @return the position of the player
     */
    public String getPosition()
    {
        return position;
    }
    
    /**
     * This method allows input directly into the data. 
     * User enters the index i and the value that they want to change stats[i] to
     */
    public void setStat(int i, int x)
    {
        stats[i] = x;
    }
    
    /**
     * This method returns the value in the array that the user requests
     * @param x - the index of the stats array that should be returned
     * @return the value of stats[x]
     */
    public int getStat(int x)
    {
        return stats[x];
    }
    
    /**
     * This method returns the value of the batting average as a double. 
     * @return the batting average of the player
     */
    public double getAvg()
    {
        return avg;
    }
    
    /**
     * This method allows the user to easily increment a specific value in a player's statistics
     * @param x - the index of the stats array that should be incremented
     */
    public void increment(int x)
    {
        stats[x]++;
    }
    
    /**
     * This method returns the size of the temporary array list.
     * @return the size of the array list temp
     */
    public int returnTempSize()
    {
        return temp.size() - 1;
    }
    
    /**
     * This method dynamically adds values to the temp class during the course of a game. The temp array should be reset after 
     * each game. Actions are decided randomly throughout the course of the game using the temp and atBat array lists. 
     * @param x - the index of the value in temp that will be added to the atBat array list
     * @return the action that was just decided
     */
    public String addAtBat(int x)
    {
        String action = temp.get(x);
        if (action.equals("Single") || action.equals("Double") || action.equals("Triple") || action.equals("Home Run"))
        {
            temp.add("Groundout");
            temp.add("Flyout");
            temp.add("Strikeout");
        }
        atBat.add(action);
        temp.add(action);
        return action;
    }
    
    /**
     * This method is the debugging version of the addAtBat method. It copies a value of temp at index x and adds it to
     * both the atBat and the temp
     * @param x - index of the value of temp to be copied
     */
    public void addAction(int x)
    {
        String action = temp.get(x);
        atBat.add(action);
        temp.add(action);
    }
    
    public void calculateAvg() {
    	avg = (double) stats[1] / stats[2];
    }
    /**
     * This method factors in the data of the player to the current pitcher of the game.
     * @param p - the pitcher who is throwing against the player
     */
    public void addActionPitcher(Pitcher p)
    {
        int hits;
        Pitcher pitch = p;
        hits = pitch.getPitchStat(6);
        for (int i = 0; i < hits; i++)
        {
            if (i % 4 == 0)
            {
                temp.add("Single");
            }
                else if (i % 4 == 1)
                {
                    temp.add("Double");
                }
                    else if (i % 4 == 2)
                    {
                        temp.add("Triple");
                    }
                        else if (i % 4 == 3)
                        {
                            temp.add("Home Run");
                        }
        }
        int so = pitch.getPitchStat(7);
        for (int j = 0; j < so; j++)
        {
            temp.add("Strikeout");
        }
        int bb = pitch.getPitchStat(8);
        for (int k = 0; k < bb; k++)
        {
            temp.add("Walk");
        }
        int fo = pitch.getPitchStat(9);
        for (int l = 0; l < fo; l++)
        {
            temp.add("Flyout");
        }
        int go = pitch.getPitchStat(10);
        for (int m = 0; m < go; m++)
        {
            temp.add("Groundout");
        }
    }
    
    /**
     * This method resets the ArrayList temp.
     */
    public void resetTemp()
    {
        temp = new ArrayList<String>();
    }
    
    /**
     * This value returns the most recently added value to the array list. This method is mostly used for debugging. 
     * @return the most recently added action to the atBat ArrayList.
     */
    public String returnValue()
    {
        return atBat.get(atBat.size() - 1);
    }
    
    /**
     * This method should print a string of the player's stats directly to the screen. 
     */
    public void displayStats()
    {
        for (int i  = 0; i < 11; i++)
        {
            System.out.print(stats[i] + "\t");
        }
        avg = (double) stats[1] / stats[2];
        System.out.print(avg);
        System.out.println();
    }
}
