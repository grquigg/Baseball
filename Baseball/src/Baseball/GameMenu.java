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
	Team whitesox;
	Team tigers;
	Inning first;
	public GameMenu() {
		super();
        
        whitesox = new Team("White Sox");
        tigers = new Team("Tigers");
        Pitcher p = new Pitcher("Correa", "Pitcher");
        tigers.addPlayer(p);
        tigers.addToRotation(0);
        first = new Inning(whitesox, tigers, 0, 0, tigers.getPitcher(0));
        whitesox.addPlayer("Hernandez", "Catcher");
        whitesox.addPlayer("Jake", "Left Fielder");
        whitesox.addToLineup(0);
        whitesox.addToLineup(1);
		setSize(500, 500);
		createWindow();

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
	
	public static void main(String [] args) {
		GameMenu m = new GameMenu();
		m.runInning(0, 0);
	}
	
}


