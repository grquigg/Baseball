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
        player = p;
    }
    public void newAction()
    {
        varb = rn.nextInt(player.returnTempSize());
        play = player.addAtBat(varb);
        if (play.equals("Single"))
        {
            player.increment(1);
            player.increment(2);
        }
            else if (play.equals("Double"))
            {
                player.increment(1);
                player.increment(2);
                player.increment(3);
            }
                else if (play.equals("Triple"))
                {
                    player.increment(1);
                    player.increment(2);
                    player.increment(4);
                }
                    else if (play.equals("Home Run"))
                    {
                        player.increment(1);
                        player.increment(2);
                        player.increment(5);
                        player.increment(6);
                    }
                        else if (play.equals("Strikeout"))
                        {
                            player.increment(2);
                            player.increment(7);
                        }
                            else if (play.equals("Groundout"))
                            {
                                player.increment(2);
                                player.increment(8);
                            }
                                else if (play.equals("Flyout"))
                                {
                                    player.increment(2);
                                    player.increment(9);
                                }
                                    else if (play.equals("Walk"))
                                    {
                                        player.increment(10);
                                    }
        Return.add(play);
    }
    public String mostRecent()
    {
        return Return.get(Return.size() - 1);
    }
}
