package Baseball;


/**
 * Write a description of class Pitcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pitcher extends Player
{
    private int[] pitcher;
    private double ip;
    private double era;
    //pitcher is a subclass of Player because a pitcher has their own stats and potentially the need for stats of a batter
    public Pitcher(String n, String p)
    {
        super(n, p);
        pitcher = new int[11];
    }
    public int getPitchStat(int x)
    {
        return pitcher[x];
    }
    public void incrementPitch(int x)
    {
        pitcher[x]++;
    }
    public void setPitchStat(int i, int x)
    {
        pitcher[i] = x;
    }
    public void displayPitchStats()
    {
        ip = (pitcher[7] + pitcher[9] + pitcher[10]) / 3;
        for (int i = 0; i < 11; i++)
        {
            System.out.print(pitcher[i] + "\t");
        }
        era = (double) (pitcher[5] * 9)/ ip;
        System.out.println(ip + "\t" + era);
    }
    public double getIP()
    {
        ip = (pitcher[7] + pitcher[9] + pitcher[10]) / 3;
        return ip;
    }
    public double getEra()
    {
        era = (double) (pitcher[5] * 9) / ip;
        return era;
    }
    public String toString() {
    	System.out.println("Pitcher stats");
    	String res = "";
    	for (int i = 0; i < pitcher.length; i++) {
    		res += pitcher[i] + ",";
    	}
    	res += Double.toString(ip) + ", " + Double.toString(era);
		return res;
    	
    }
}
