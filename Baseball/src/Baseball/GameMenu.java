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
	Inning first;
	public GameMenu(Team a, Team b) {
		super();
        
        teamA = a;
        teamB = b;
        Pitcher p = new Pitcher("Correa", "Pitcher");
        teamA.addPlayer(p);
        
        teamA.addToRotation(teamA.returnRoster()-1); //this number cannot be zero
        first = new Inning(teamB, teamA, 0, 0, teamA.getPitcher(0));
//        teamB.addPlayer("Hernandez", "Catcher");
//        teamB.addPlayer("Jake", "Left Fielder");
//        teamB.addToLineup(0);
//        teamB.addToLineup(1);
		setSize(500, 500);
		createWindow();
		runInning(0, 0);

	}

	
	public void runInning(int i, int o) {
		int inning = i + 1;
		SwingWorker inningWorker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				String response = "";
				while(response != "DONE") {
					response = first.getNextPlay();
					System.out.println(response);
					publish(response);
					Thread.sleep(1000);
				}
				return null;
				
			}
			
			protected void process(List<String> list) {
				System.out.println("call this function");
				String play = list.get(list.size()-1);
				textArea.append(play + "\n");
				
			}
			
			@Override
			protected void done() {
				
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
	
    public static void createAndShowGUI(Team t1, Team t2) {
        //Create and set up the window.
 
        //Create and set up the content pane.
        JFrame frame = new GameMenu(t1, t2);
        //Display the window.
    }
	
}


