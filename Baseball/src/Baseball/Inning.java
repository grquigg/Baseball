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
    public Inning(Team t, Team u,int x, int s, Pitcher opp)
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
    
    public void baseRunner(int n, boolean b) {
    	for (int i = n-1; i >= 0; i--) {
    		if (bases[i]) {
    			if (i + n > 2) {
    				System.out.print("A runner has scored");
    				team1.getPlayerFromLineup(order).increment(6);
    				pitcher.incrementPitch(5);
    			}
    			else {
    				bases[i+n] = true;
    			}
    		}
    	}
		if (b && n < 4) {
			bases[n] = true;
		}
    	if (bases[0]) {
    		System.out.print("Runner on 1st.");
    	}
    	
    	if (bases[1]) {
    		System.out.print("Runner on 2nd");
    	}
    	
    	if (bases[2]) {
    		System.out.print("Runner on 3rd");
    	}
    }
    
    public void run_inning(int i, int o) {
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
    			System.out.print(team1.getName(order) + " has tripled.");
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
            		baseRunner(1, false);
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
    	}
    }
    
    public void runInning(int i, int o)
    {
        inning = i+1;
        System.out.println(team1.getTeamName() + " are batting in Inning " + inning + ".");
        reader.nextLine();
        count = 0;
            while ((order <= team1.returnLineup()) && (outs < 3))
            {
                String temp;
                plays.add(new Plays(team1.getPlayerFromLineup(order)));
                plays.get(count).newAction();
                temp = plays.get(count).mostRecent();
                if (temp.equals("Single"))
                {
                    System.out.print(team1.getName(order) + " has singled. ");
                    pitcher.incrementPitch(6);
                        if (!base1 && !base2 && !base3)
                        {
                            System.out.print(team1.getName(order) + " on 1st. ");
                            base1 = true;
                        }
                            else if (base1 && !base2 && !base3)
                            {
                               System.out.print(team1.getName(order) + " on 1st. Runner on 2nd. ");
                               base1 = true;
                               base2 = true;
                            }
                                else if (!base1 && base2 && !base3)
                                {
                                    System.out.print(team1.getName(order) + " on 1st. Runner on 3rd. ");
                                    base1 = true;
                                    base2 = false;
                                    base3 = true;
                                }
                                    else if (!base1 && !base2 && base3)
                                    {
                                        System.out.print(team1.getName(order) + " on 1st! Runner from 3rd scores! ");
                                        base3 = false;
                                        base1 = true;
                                        team1.getPlayerFromLineup(order).increment(6);
                                        pitcher.incrementPitch(5);
                                        score++;
                                    }
                                        else if (base1 && base2 && !base3)
                                        {
                                            System.out.print(team1.getName(order) + " on 1st. Runner on 2nd and 3rd. Bases Loaded. ");
                                            base1 = true;
                                            base2 = true;
                                            base3 = true;
                                        }
                                            else if (base1 && !base2 && base3)
                                            {
                                                System.out.print(team1.getName(order) + " on 1st! Runner to 2nd! Runner from 3rd scores! ");
                                                base1 = true;
                                                base2 = true;
                                                base3 = false;
                                                team1.getPlayerFromLineup(order).increment(6);
                                                pitcher.incrementPitch(5);
                                                score++;
                                            }
                                                else if (!base1 && base2 && base3)
                                                {
                                                    System.out.print(team1.getName(order) + " on 1st! Runner to 3rd! Runner from 3rd scores! ");
                                                    base1 = true;
                                                    base2 = false;
                                                    base3 = true;
                                                    team1.getPlayerFromLineup(order).increment(6);
                                                    pitcher.incrementPitch(5);
                                                    score++;
                                                }
                                                    else if (base1 && base2 && base3)
                                                    {
                                                        System.out.print(team1.getName(order) + " on 1st! Runner to 2nd! Runner to 3rd! Runner from 3rd scores! Bases still loaded. ");
                                                        base1 = true;
                                                        base2 = true;
                                                        base3 = true;
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        pitcher.incrementPitch(5);
                                                        score++;
                                                    }
                    System.out.println(outs + " out.");
                }
                    else if(temp.equals("Double"))
                    {
                        System.out.print(team1.getName(order) + " has doubled. ");
                        pitcher.incrementPitch(6);
                        if (!base1 && !base2 && !base3)
                        {
                            System.out.print(team1.getName(order) + " on 2nd. ");
                            base2 = true;
                        }
                            else if (base1 && !base2 && !base3)
                            {
                               System.out.print(team1.getName(order) + " on 2nd. Runner on 3rd. ");
                               base1 = false;
                               base2 = true;
                               base3 = true;
                            }
                                else if (!base1 && base2 && !base3)
                                {
                                    System.out.print(team1.getName(order) + " on 2nd! Runner from 2nd scores! ");
                                    base2 = true;
                                    team1.getPlayerFromLineup(order).increment(6);
                                    pitcher.incrementPitch(5);
                                    score++;
                                }
                                    else if (!base1 && !base2 && base3)
                                    {
                                        System.out.print(team1.getName(order) + " on 2nd! Runner from 3rd scores! ");
                                        base3 = false;
                                        base2 = true;
                                        team1.getPlayerFromLineup(order).increment(6);
                                        pitcher.incrementPitch(5);
                                        score++;
                                    }
                                        else if (base1 && base2 && !base3)
                                        {
                                            System.out.print(team1.getName(order) + " on 2nd! Runner on 3rd! Runner from 2nd scores! ");
                                            base1 = false;
                                            base2 = true;
                                            base3 = true;
                                            team1.getPlayerFromLineup(order).increment(6);
                                            pitcher.incrementPitch(5);
                                            score++;
                                        }
                                            else if (base1 && !base2 && base3)
                                            {
                                                System.out.print(team1.getName(order) + " on 2nd and 3rd! Runner from 3rd scores! ");
                                                base1 = false;
                                                base2 = true;
                                                base3 = false;
                                                team1.getPlayerFromLineup(order).increment(6);
                                                pitcher.incrementPitch(5);
                                                score++;
                                            }
                                                else if (!base1 && base2 && base3)
                                                {
                                                    System.out.print(team1.getName(order) + " on 2nd! Runner from 3rd and 2nd both score! ");
                                                    base1 = false;
                                                    base2 = true;
                                                    base3 = false;
                                                    team1.getPlayerFromLineup(order).increment(6);
                                                    team1.getPlayerFromLineup(order).increment(6);
                                                    pitcher.incrementPitch(5);
                                                    pitcher.incrementPitch(5);
                                                    score = score + 2;
                                                }
                                                    else if (base1 && base2 && base3)
                                                    {
                                                        System.out.print(team1.getName(order) + " on 2nd! Runner to 3rd! Runner from 2nd and 3rd both score! ");
                                                        base1 = false;
                                                        base2 = true;
                                                        base3 = true;
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        pitcher.incrementPitch(5);
                                                        pitcher.incrementPitch(5);
                                                        score = score + 2;
                                                    }
                        System.out.println(outs + " out.");
                    }
                        else if (temp.equals("Triple"))
                        {
                            System.out.print(team1.getName(order) + " has tripled. ");
                            pitcher.incrementPitch(6);
                            if (!base1 && !base2 && !base3)
                            {
                                System.out.print(team1.getName(order) + " is on 3rd. ");
                                base3 = true;
                            }
                                else if (base1 && !base2 && !base3)
                                {
                                    System.out.print(team1.getName(order) + " is on 3rd! Runner from 1st scores! ");
                                    base1 = false;
                                    base3 = true;
                                    team1.getPlayerFromLineup(order).increment(6);
                                    pitcher.incrementPitch(5);
                                    score++;
                                }
                                    else if (!base1 && base2 && !base3)
                                    {
                                        System.out.print(team1.getName(order) + " is on 3rd! Runner from 2nd scores! ");
                                        base2 = false;
                                        base3 = true;
                                        team1.getPlayerFromLineup(order).increment(6);
                                        pitcher.incrementPitch(5);
                                        score++;
                                    }
                                        else if (!base1 && !base2 && base3)
                                        {
                                            System.out.print(team1.getName(order) + " is on 3rd! Runner from 3rd scores! ");
                                            base3 = true;
                                            team1.getPlayerFromLineup(order).increment(6);
                                            pitcher.incrementPitch(5);
                                            score++;
                                        }
                                            else if (base1 && base2 && !base3)
                                            {
                                                System.out.print(team1.getName(order) + " is on 3rd! Runner from 1st and 2nd score! ");
                                                base1 = false;
                                                base2 = false;
                                                base3 = true;
                                                team1.getPlayerFromLineup(order).increment(6);
                                                team1.getPlayerFromLineup(order).increment(6);
                                                pitcher.incrementPitch(5);
                                                pitcher.incrementPitch(5);
                                                score = score + 2;
                                            }
                                                else if (base1 && !base2 && base3)
                                                {
                                                    System.out.print(team1.getName(order) + " is on 3rd! Runner from 1st and 3rd score! ");
                                                    base1 = false;
                                                    base3 = true;
                                                    team1.getPlayerFromLineup(order).increment(6);
                                                    team1.getPlayerFromLineup(order).increment(6);
                                                    pitcher.incrementPitch(5);
                                                    pitcher.incrementPitch(5);
                                                    score = score + 2;
                                                }
                                                    else if (!base1 && base2 && base3)
                                                    {
                                                        System.out.print(team1.getName(order) + " is on 3rd! Runner from 2nd and 3rd score! ");
                                                        base2 = false;
                                                        base3 = true;
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        pitcher.incrementPitch(5);
                                                        pitcher.incrementPitch(5);
                                                        score = score + 2;
                                                    }
                                                        else if (base1 && base2 && base3)
                                                        {
                                                            System.out.print(team1.getName(order) + " is on 3rd! Runners from 1st, 2nd and 3rd score! ");
                                                            base1 = false;
                                                            base2 = false;
                                                            base3 = true;
                                                            team1.getPlayerFromLineup(order).increment(6);
                                                            team1.getPlayerFromLineup(order).increment(6);
                                                            team1.getPlayerFromLineup(order).increment(6);
                                                            pitcher.incrementPitch(5);
                                                            pitcher.incrementPitch(5);
                                                            pitcher.incrementPitch(5);
                                                            score = score + 3;
                                                        }
                            System.out.println(outs + " out.");
                        }
                            else if (temp.equals("Home Run"))
                            {
                                System.out.print(team1.getName(order) + " has hit a home run! All runners score! ");
                                pitcher.incrementPitch(6);
                                pitcher.incrementPitch(5);
                                if (!base1 && !base2 && !base3)
                                {
                                    score++;
                                }
                                    else if ((base1 && !base2 && !base3) || (!base1 && base2 && !base3) || (!base1 && !base2 && base3))
                                    {
                                        base1 = false;
                                        base2 = false;
                                        base3 = false;
                                        team1.getPlayerFromLineup(order).increment(6);
                                        pitcher.incrementPitch(5);
                                        score = score + 2;
                                    }
                                        else if ((base1 && base2 && !base3) || (base1 && !base2 && base3) || (!base1 && base2 && base3))
                                        {
                                            base1 = false;
                                            base2 = false;
                                            base3 = false;
                                            team1.getPlayerFromLineup(order).increment(6);
                                            team1.getPlayerFromLineup(order).increment(6);
                                            pitcher.incrementPitch(5);
                                            pitcher.incrementPitch(5);
                                            score = score + 3;
                                        }
                                            else if (base1 && base2 && base3)
                                            {
                                                base1 = false;
                                                base2 = false;
                                                base3 = false;
                                                team1.getPlayerFromLineup(order).increment(6);
                                                team1.getPlayerFromLineup(order).increment(6);
                                                team1.getPlayerFromLineup(order).increment(6);
                                                pitcher.incrementPitch(5);
                                                pitcher.incrementPitch(5);
                                                pitcher.incrementPitch(5);
                                                score = score + 4;
                                                System.out.print(" Grand Slam! ");
                                            }
                                System.out.println( outs + " out.");
                            }
                                else if (temp.equals("Strikeout"))
                                {
                                    System.out.print(team1.getName(order) + " has struck out. ");
                                    pitcher.incrementPitch(7);
                                    outs++;
                                    System.out.println(outs + " out. ");
                                }
                                    else if (temp.equals("Flyout"))
                                    { //now this is where it gets complicated
                                        pitcher.incrementPitch(9);
                                        if (outs < 2)
                                        {
                                            if (base2 && base3)
                                            {
                                                System.out.print(team1.getName(order) + " hits a Sacrifice Fly. Runner at 2nd tags and moves to 3rd. Runner at 3rd scores. ");
                                                base2 = false;
                                                base3 = true;
                                                team1.getPlayerFromLineup(order).increment(6);
                                                pitcher.incrementPitch(5);
                                                score++;
                                            }
                                                else if (base2 && !base3)
                                                {
                                                    System.out.print(team1.getName(order) + " flys out. Runner at 2nd tags and moves to 3rd. ");
                                                    base2 = false;
                                                    base3 = true;
                                                }
                                                    else if (!base2 && base3)
                                                    {
                                                        System.out.print(team1.getName(order) + " hits a Sacrifice Fly. Runner at 3rd scores. ");
                                                        score++;
                                                        team1.getPlayerFromLineup(order).increment(6);
                                                        pitcher.incrementPitch(5);
                                                        base3 = false;
                                                    }
                                                        else
                                                        {
                                                            System.out.print(team1.getName(order) + " flys out.");
                                                        }
                                        }
                                            else
                                            {
                                                System.out.print(team1.getName(order) + " flys out. ");
                                            }
                                        outs++;
                                        System.out.println(outs + " out.");
                                    }
                                        else if (temp.equals("Groundout"))
                                        {
                                            System.out.print(team1.getName(order) + " grounds out. ");
                                            pitcher.incrementPitch(10);
                                            if (base2 && !base3)
                                                {
                                                    System.out.print("Runner on 2nd moves to 3rd. ");
                                                    base2 = false;
                                                }
                                                    else if (!base2 && base3)
                                                    {
                                                        if (outs < 2)
                                                        {
                                                            System.out.print("Runner from 3rd scores. ");
                                                            score++;
                                                            team1.getPlayerFromLineup(order).increment(6);
                                                            pitcher.incrementPitch(5);
                                                            base3 = false;
                                                        }
                                                    }
                                            outs++;
                                            System.out.println(outs + " out.");
                                        }
                                            else if (temp.equals("Walk"))
                                            {
                                                System.out.print(team1.getName(order) + " has walked. ");
                                                pitcher.incrementPitch(8);
                                                if (!base1 && !base2 && !base3)
                                                {
                                                    System.out.print("Runner on 1st. ");
                                                    base1 = true;
                                                }
                                                    else if (base1 && !base2 && !base3)
                                                    {
                                                        System.out.print("Runner on 1st moves to 2nd. Runners on 1st and 2nd. ");
                                                        base2 = true;
                                                    }
                                                        else if (!base1 && base2 && !base3)
                                                        {
                                                            System.out.print("Runners on 1st and 2nd. ");
                                                            base1 = true;
                                                        }
                                                            else if (!base1 && !base2 && base3)
                                                            {
                                                                System.out.print("Runners on 1st and 3rd. ");
                                                                base1 = true;
                                                            }
                                                                else if (base1 && base2 && !base3)
                                                                {
                                                                    System.out.print("Runner on 1st moves to 2nd, runner on 2nd moves to 3rd. Bases loaded. ");
                                                                    base3 = true;
                                                                }
                                                                    else if (base1 && !base2 && base3)
                                                                    {
                                                                        System.out.print("Runner on 1st moves to 2nd. Bases loaded. ");
                                                                        base2 = true;
                                                                    }
                                                                        else if (!base1 && base2 && base3)
                                                                        {
                                                                            System.out.print("Bases loaded. ");
                                                                            base1 = true;
                                                                        }
                                                                            else if (base1 && base2 && base3)
                                                                            {
                                                                                System.out.print("Runner from 3rd scores! Bases still loaded. ");
                                                                                team1.getPlayerFromLineup(order).increment(6);
                                                                                pitcher.incrementPitch(5);
                                                                            }
                                            }
                if (order != team1.returnLineup())
                {
                    order++;
                }
                if (order == team1.returnLineup())
                {
                    order = 0;
                }
                count++;
                reader.nextLine();
                System.out.println("Prompt?");
                prompt();
            }
        System.out.println("End on inning " + inning + ". Score is " + score + " .");
    }
    public int reportScore()
    {
        return score;
    }
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
