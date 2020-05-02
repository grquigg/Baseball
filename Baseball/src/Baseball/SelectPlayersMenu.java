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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
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
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTHWEST;
        //gc.weightx = 1;
        gc.weighty = 0.01;
		gc.gridx = 0;
		gc.gridy = 0;
		add(setupTeam1, gc);
		
		JLabel label1 = new JLabel("Name");
		gc.weighty = 0.01;
		gc.gridy = 1;
		gc.insets = new Insets(0, 12, 0, 0);
		add(label1, gc);
		
		gc.anchor = gc.NORTH;
		gc.insets = new Insets(0, 10, 0, 0);
		listPlayersTeam1 = new JList(listModelTeam1);
        listPlayersTeam1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPlayersTeam1.setSelectedIndex(0);
        listPlayersTeam1.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listPlayersTeam1);
        gc.gridy = 2;
        gc.weighty = 0.05;
        gc.gridwidth = 2;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane, gc);
		
        gc.fill = gc.NONE;
        gc.anchor = gc.CENTER;
        JButton button = new JButton("Select Player");
        gc.insets = new Insets(0, 0, 0, 50);
        gc.gridwidth = 1;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.weighty = 0.3;
        //gc.ipadx = 60;
        add(button, gc);
        
        JButton addNewTeamMember = new JButton("Add New Player");
        gc.ipadx = 0;
        gc.insets = new Insets(0, 20, 0, 0);
        gc.gridx = 0;
        add(addNewTeamMember, gc);
        
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTHWEST;
		JLabel setupTeam2 = new JLabel("Enter information for Team White");
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 0.01;
		add(setupTeam2, gc);
		
		JLabel label2 = new JLabel("Name");
		gc.weighty = 0.01;
		gc.gridy = 5;
		gc.insets = new Insets(0, 12, 0, 0);
		add(label2, gc);
		
		gc.anchor = gc.NORTH;
		gc.gridwidth = 2;
		listPlayersTeam2 = new JList(listModelTeam1);
        listPlayersTeam2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPlayersTeam2.setSelectedIndex(0);
        listPlayersTeam2.setVisibleRowCount(5);
        JScrollPane listScrollPane2 = new JScrollPane(listPlayersTeam2);
        gc.gridy = 6;
        //gc.gridx = 0;
        gc.weighty = 0.03;
        gc.ipadx = 100;
        gc.gridwidth = 2;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane2, gc);
        
        gc.fill = gc.NONE;
        gc.anchor = gc.CENTER;
        gc.ipadx = 0;
        JButton button2 = new JButton("Select Player");
        gc.insets = new Insets(0, 0, 0, 50);
        gc.gridwidth = 1;
        gc.gridy = 7;
        gc.gridx = 1;
        gc.weighty = 0.3;
        add(button2, gc);
        
        JButton addNewTeamMember2 = new JButton("Add New Player");
        gc.insets = new Insets(0, 20, 0, 0);
        gc.gridx = 0;
        add(addNewTeamMember2, gc);
        
        JLabel lab = new JLabel("Section for lineups");
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.CENTER;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 1;
        add(lab, gc);
		setSize(800, 500);
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
