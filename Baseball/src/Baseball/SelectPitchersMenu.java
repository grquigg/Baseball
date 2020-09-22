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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Baseball.SelectPlayersMenu.createNewPlayerGUI;

public class SelectPitchersMenu extends JFrame implements ActionListener, ListSelectionListener {

    JList<String> listPitchersTeam1;
    JList<String> listPitchersTeam2;
    DefaultListModel<String> listModelTeam1;
    DefaultListModel<String> listModelTeam2;
    JScrollPane listScrollPane;
    JScrollPane listScrollPane2;
    JLabel team1;
    JLabel team2;
    static Team teamA;
    static Team teamB;
    JButton selectPitcherTeam1;
    JButton selectPitcherTeam2;
    JButton createNewPitcherTeam1;
    JButton createNewPitcherTeam2;
    JButton startGameButton;
    JButton next;
    JLabel pitcherForTeam1;
    JLabel pitcherForTeam2;
	
	public SelectPitchersMenu() {
		super("Select Pitchers");
		teamA = new Team("a");
		teamB = new Team("b");
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		listModelTeam1 = new DefaultListModel<String>();
        listModelTeam2 = new DefaultListModel<String>();
        
        JLabel setupTeam1 = new JLabel("Enter information for Team " + teamA.getTeamName());
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTHWEST;
        //gc.weightx = 1;
        gc.weighty = 0.01;
		gc.gridx = 0;
		gc.gridy = 0;
		add(setupTeam1, gc);
		
		JLabel label1 = new JLabel("Name");
		gc.weighty = 0.03;
		gc.gridy = 1;
		gc.insets = new Insets(0, 12, 0, 0);
		add(label1, gc);
		
		//gc.anchor = gc.NORTHWEST;
		gc.insets = new Insets(0, 10, 0, 0);
		listPitchersTeam1 = new JList<String>(listModelTeam1);
        listPitchersTeam1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPitchersTeam1.setSelectedIndex(0);
        listPitchersTeam1.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(listPitchersTeam1);
        gc.gridy = 2;
        gc.weighty = 0.05;
        gc.ipadx = 100;
        gc.gridwidth = 2;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane, gc);
		
        gc.fill = gc.NONE;
        gc.anchor = gc.EAST;
        selectPitcherTeam1 = new JButton("Select Pitcher");
        gc.ipadx = 0;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.weighty = 0.2;
        //gc.ipadx = 60;
        add(selectPitcherTeam1, gc);
        selectPitcherTeam1.addActionListener(this);
        
        gc.anchor = gc.WEST;
        createNewPitcherTeam1 = new JButton("Add New Pitcher");
        gc.ipadx = 0;
        gc.insets = new Insets(0, 10, 0, 0);
        gc.gridx = 0;
        add(createNewPitcherTeam1, gc);
        createNewPitcherTeam1.addActionListener(this);
        
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTHWEST;
		JLabel setupTeam2 = new JLabel("Enter information for Team " + teamB.getTeamName());
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 0.01;
		add(setupTeam2, gc);
		
		JLabel label2 = new JLabel("Name");
		gc.weighty = 0.03;
		gc.gridy = 5;
		gc.insets = new Insets(0, 12, 0, 0);
		add(label2, gc);
		
		gc.anchor = gc.NORTHWEST;
		listPitchersTeam2 = new JList<String>(listModelTeam2);
        listPitchersTeam2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPitchersTeam2.setSelectedIndex(0);
        listPitchersTeam2.setVisibleRowCount(5);
        listScrollPane2 = new JScrollPane(listPitchersTeam2);
        gc.gridy = 6;
        //gc.gridx = 0;
        gc.weighty = 0.03;
        gc.ipadx = 100;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane2, gc);
        
        gc.fill = gc.NONE;
        gc.anchor = gc.EAST;
        gc.ipadx = 0;
        selectPitcherTeam2 = new JButton("Select Pitcher");
        //gc.insets = new Insets(0, 0, 0, 50);
        gc.gridy = 7;
        gc.gridx = 1;
        gc.weighty = 0.2;
        add(selectPitcherTeam2, gc);
        selectPitcherTeam2.addActionListener(this);
        
        gc.anchor = gc.WEST;
        createNewPitcherTeam2 = new JButton("Add New Pitcher");
        gc.insets = new Insets(0, 10, 0, 0);
        gc.gridx = 0;
        add(createNewPitcherTeam2, gc);
        createNewPitcherTeam2.addActionListener(this);
        
        JLabel lab = new JLabel("Team 1's starting pitcher");
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTH;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weighty = 0.03;
        gc.weightx = 1;
        add(lab, gc);
        
        JLabel lab2 = new JLabel("Team 2's starting pitcher");
        gc.ipadx = 0;
		gc.gridx = 2;
		gc.gridy = 4;
		add(lab2, gc);
        
        startGameButton = new JButton("Start Game!");
        startGameButton.addActionListener(this);
        gc.gridx = 2;
        gc.gridy = 8;
        gc.anchor = gc.NORTH;
        gc.gridheight = 1;
        gc.ipadx = 50;
        add(startGameButton, gc);
        
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createNewPitcherTeam1) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new newGUI("Create New Team", teamA, listModelTeam1).createAndShowGUI(teamA);
				}
			});
		}
		else if (e.getSource() == createNewPitcherTeam2) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new newGUI("Create New Team", teamB, listModelTeam2).createAndShowGUI(teamB);
				}
			});
		}
		//user should be prevented from adding a player to position 3 when position 2 is not filled
		else if(e.getSource() == selectPitcherTeam1) {
			int index = listPitchersTeam1.getSelectedIndex();
			String player = listModelTeam1.get(index).charAt(0) + " " + listPitchersTeam1.getSelectedValue();
		}
		else if(e.getSource() == selectPitcherTeam2) {
			int index = listPitchersTeam2.getSelectedIndex();
			String player = listModelTeam2.get(index).charAt(0) + " " + listPitchersTeam2.getSelectedValue();
		} else if (e.getSource() == startGameButton) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					System.out.println("run");
					dispose();
					new GameMenu(teamA, teamB);
				}
			});
		}
	}
	
	
	   /**
  * Create the GUI and show it.  For thread safety,
  * this method should be invoked from the
  * event-dispatching thread.
	 * @param t2 
	 * @param t1 
  */
	public static void createAndShowGUI() {
     //Create and set up the window.

     //Create and set up the content pane.
		JFrame frame = new SelectPitchersMenu();

     //Display the window.
		frame.setVisible(true);
	}
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SelectPitchersMenu().createAndShowGUI();
            }
        });
    }
    
    public class newGUI extends JFrame implements ActionListener {
    	
    	JButton create;
    	JTextField playerName;
    	JTextField playerPosition;
    	Team t;
    	DefaultListModel<String> mode;
    	public newGUI(String title, Team team, DefaultListModel<String> list) {
    		super(title);
    		mode = list;
    		setLayout(new GridBagLayout());
    		t = team;
    		GridBagConstraints gc = new GridBagConstraints();
    		gc.anchor = gc.CENTER;
    		gc.gridx=0;
    		gc.gridy=0;
    		gc.fill = GridBagConstraints.NONE;
    		gc.insets = new Insets(0, 0, 0, 5);
    		playerName = new JTextField("Player");
    		create = new JButton("Create Pitcher");
    		gc.ipady = 7;
    		gc.ipadx = 50;
    		add(playerName, gc);
    		gc.insets = new Insets(0, 0, 0, 5);
    		gc.gridx=1;
    		gc.gridy = 0;
    		gc.ipady = 0;
    		gc.ipadx = 0;
    		add(create, gc);
    		
    		create.addActionListener(this);
    		
    		
    		setSize(300, 100);
    		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	}
		public void createAndShowGUI(Team t) {
    		JFrame frame = new newGUI("Create New Team", t, mode);
    		
    		frame.setVisible(true);
    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				mode.addElement(playerName.getText());
				t.addPlayer(playerName.getText(), "Pitcher");
				dispose();
			}
			
		}
    }
}
