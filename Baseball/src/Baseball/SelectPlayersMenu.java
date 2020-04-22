package Baseball;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectPlayersMenu extends JFrame implements ActionListener, ListSelectionListener {

    JList listPlayersTeam1;
    JList listPlayersTeam2;
    DefaultListModel listModelTeam1;
    DefaultListModel listModelTeam2;
    JLabel team1;
    JLabel team2;
    JButton select;
    JButton select2;
    JButton createNewTeam;
    JButton startGameButton;
	
	public SelectPlayersMenu() {
		super("Select Players");
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		listModelTeam1 = new DefaultListModel();
        listModelTeam1.addElement("Potato");
        listModelTeam1.addElement("Tomato");
        
        listModelTeam2 = new DefaultListModel();
        listModelTeam2.addElement("Strawberry");
        listModelTeam2.addElement("Elephant");
        
        JLabel setupTeam1 = new JLabel("Enter information for Team Black");
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(10, 10, 0, 0);
        gc.anchor = gc.NORTHWEST;
        gc.weightx = 1;
        gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		add(setupTeam1, gc);
		
		JLabel setupTeam2 = new JLabel("Enter information for Team White");
		gc.gridx = 0;
		gc.gridy = 1;
		add(setupTeam2, gc);
		setSize(500, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	   /**
  * Create the GUI and show it.  For thread safety,
  * this method should be invoked from the
  * event-dispatching thread.
  */
	private static void createAndShowGUI() {
     //Create and set up the window.

     //Create and set up the content pane.
		JFrame frame = new SelectPlayersMenu();

     //Display the window.
		frame.setVisible(true);
	}
	
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
