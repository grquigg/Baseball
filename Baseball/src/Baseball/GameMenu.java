package Baseball;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

public class GameMenu extends JFrame {
	
	JTextArea textArea;
	SwingWorker inningWorker;
	Team teamA;
	Team teamB;
	int scoreTeamA;
	int scoreTeamB;
	Inning first;
	Game game;
	boolean processing = true;
	public GameMenu(Game g, Team a, Team b) {
		super();
        game = g;
        teamA = a;
        teamB = b;
        scoreTeamA = 0;
        scoreTeamB = 0;
        first = new Inning(teamB, teamA, 0, 0, teamA.getStartingPitcher());
		setSize(500, 500);
		createWindow();
		System.out.println("OUTPUT");
		runGame(teamA, teamB);
		

	}

	public void runGame(Team a, Team b) {
		SwingWorker gameWorker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				//TO-DO: Add code for the temporary variables, such as score per inning, the current place in the batting order, etc.
				int numInnings = 1;
				int aOrder = 0;
				int bOrder = 0;
				for (int i = 0; i < numInnings; i++) {
					processing = true;
					System.out.println("Running team A");
					Inning aInning = new Inning(teamB, teamA, aOrder, scoreTeamB, teamA.getStartingPitcher());
					runInning(i, 0, aInning);
					while(!inningWorker.isDone()) {
						System.out.println("Wait for team A");
						Thread.sleep(1000);
					}
					System.out.println(inningWorker.isDone());
					scoreTeamB = aInning.reportScore();
					aOrder = aInning.getOrder();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
					System.out.println("Running team B");
					Inning bInning = new Inning(teamA, teamB, bOrder, scoreTeamA, teamB.getStartingPitcher());
					runInning(i, 0, bInning);
					processing = true;
					while(processing) {
						System.out.println("Wait for team B");
						Thread.sleep(1000);
					}
					scoreTeamA = bInning.reportScore();
					bOrder = bInning.getOrder();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
				}
				int inningNum = numInnings;
				while(scoreTeamA == scoreTeamB) {
					System.out.println("Extra innings");
					processing = true;
					System.out.println("running team A");
					Inning aInning = new Inning(teamB, teamA, 0, scoreTeamB, teamA.getStartingPitcher());
					runInning(inningNum, 0, aInning);
					while(processing) {
						System.out.println("Wait for team B");
						Thread.sleep(500);
					}
					scoreTeamB = aInning.reportScore();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
					System.out.println("running team B");
					Inning bInning = new Inning(teamA, teamB, 0, scoreTeamA, teamB.getStartingPitcher());
					runInning(inningNum, 0, bInning);
					processing = true;
					while(processing) {
						System.out.println("Wait...");
						Thread.sleep(500);
					}
					scoreTeamA = bInning.reportScore();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
				}
				if(scoreTeamA > scoreTeamB) {
					textArea.append(teamA.getTeamName() + " wins!\n");
					teamA.getStartingPitcher().setPitchStat(2, teamA.getStartingPitcher().getPitchStat(2)+1);
					teamB.getStartingPitcher().setPitchStat(3, teamB.getStartingPitcher().getPitchStat(3)+1);
				} else {
					textArea.append(teamB.getTeamName() + " wins!\n");
					teamA.getStartingPitcher().setPitchStat(3, teamA.getStartingPitcher().getPitchStat(3)+1);
					teamB.getStartingPitcher().setPitchStat(2, teamB.getStartingPitcher().getPitchStat(2)+1);
				}
				return null;
				
			}
			//something's wrong here
			protected void done() {
				teamA.getPlayer(0).displayStats();
				teamB.getPlayer(0).displayStats();
//				game.WriteToFile();
			}
			
		};
		gameWorker.execute();
	}
	
	public void runInning(int i, int o, Inning inn) {
		int inning = i + 1;
		inningWorker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				String response = "";
				while(response != "DONE") {
					response = inn.getNextPlay();
					publish(response);
					Thread.sleep(1000);
				}
				return null;
				
			}
			
			protected void process(List<String> list) {
				System.out.println("call this function");
				String play = list.get(list.size()-1);
				if(play != "DONE") {
					textArea.append(play + "\n");
				}
				
			}
			
			@Override
			protected void done() {
				//Should print the score of the inning
				processing = false;
			}
			
		};
		inningWorker.execute();
	}
	private void createWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        textArea = new JTextArea(15, 50);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroller);
        getContentPane().add(BorderLayout.CENTER, panel);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
		
	}
	
    public void createAndShowGUI() {
        //Create and set up the window.
 
        //Create and set up the content pane.
    	this.setVisible(true);
        //Display the window.
    }
	
}


