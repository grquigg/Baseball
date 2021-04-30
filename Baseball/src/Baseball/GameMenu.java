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
		runGame(teamA, teamB);
		

	}

	public void runGame(Team a, Team b) {
		SwingWorker gameWorker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				//TO-DO: Add code for the temporary variables, such as score per inning, the current place in the batting order, etc.
				for (int i = 0; i < 1; i++) {
					processing = true;
					System.out.println("running team A");
					Inning aInning = new Inning(teamB, teamA, 0, scoreTeamB, teamA.getStartingPitcher());
					runInning(i, 0, aInning);
					while(processing) {
						System.out.println("Wait...");
						Thread.sleep(1000);
					}
					scoreTeamB = aInning.reportScore();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
					System.out.println("running team B");
					Inning bInning = new Inning(teamA, teamB, 0, scoreTeamA, teamB.getStartingPitcher());
					runInning(i, 0, bInning);
					processing = true;
					while(processing) {
						System.out.println("Wait...");
						Thread.sleep(1000);
					}
					scoreTeamA = bInning.reportScore();
					textArea.append("Score is " + teamA.getTeamName() + ": " + Integer.toString(scoreTeamA) + " to " + teamB.getTeamName() + ": " + Integer.toString(scoreTeamB) + "\n");
				}
				return null;
			}
			
			protected void done() {
				System.out.println("Game is done");
				System.out.println("Team A stats");
				System.out.println(teamA.returnRoster());
				System.out.println(game.teamA.getPlayer(0).toString());
				game.WriteToFile();
			}
			
		};
		gameWorker.execute();
	}
	
	public void runInning(int i, int o, Inning inn) {
		int inning = i + 1;
		SwingWorker inningWorker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				String response = "";
				while(response != "DONE") {
					response = inn.getNextPlay();
					System.out.println(response);
					publish(response);
					Thread.sleep(1000);
				}
				return null;
				
			}
			
			protected void process(List<String> list) {
				processing = true;
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


