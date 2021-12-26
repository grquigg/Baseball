package Baseball;


/**
 * Write a description of class Plays here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;
public class Plays
{
    private Player player;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> Return = new ArrayList<String>();
    Random rn = new Random();
    private int varb;
    private String play;
    
    /**
     * A play has a player P that it is associated with. 
     * @param p - the player that this particular play is associated with
     */
    public Plays(Player p)
    {
//    	rn.setSeed(5);
        player = p;
    }
    public void newAction()
    {
        varb = rn.nextInt(player.returnTempSize());
        play = player.addAtBat(varb);
        switch(play) {
        case "Single":
        	player.increment(1);
        	player.increment(2);
        	break;
        case "Double":
        	player.increment(1);
        	player.increment(2);
        	player.increment(3);
        	break;
        case "Triple":
        	player.increment(1);
        	player.increment(2);
        	player.increment(4);
        	break;
        case "Home Run":
        	player.increment(1);
            player.increment(2);
            player.increment(5);
            player.increment(6);
            break;
        case "Strikeout":
        	player.increment(2);
            player.increment(7);
            break;
        case "Groundout":
        	player.increment(2);
            player.increment(8);
            break;
        case "Flyout":
        	player.increment(2);
            player.increment(9);
            break;
        case "Walk":
        	player.increment(10);
        	break;
        }
        Return.add(play);
    }
    public String mostRecent()
    {
        return Return.get(Return.size() - 1);
    }
}
