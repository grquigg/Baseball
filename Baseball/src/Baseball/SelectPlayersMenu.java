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

import Baseball.SelectTeamsMenu.createNewTeamGUI;

public class SelectPlayersMenu extends JFrame implements ActionListener, ListSelectionListener {

    JList<String> listPlayersTeam1;
    JList<String> listPlayersTeam2;
    JList<String> listLineup1;
    JList<String> listLineup2;
    DefaultListModel<String> listModelTeam1;
    DefaultListModel<String> listModelTeam2;
    DefaultListModel<String> listModelLineupA;
    DefaultListModel<String> listModelLineupB;
    JScrollPane listScrollPane;
    JScrollPane listScrollPane2;
    JLabel team1;
    JLabel team2;
    static Team teamA;
    static Team teamB;
    JButton selectPlayerTeam1;
    JButton selectPlayerTeam2;
    JButton createNewPlayerTeam1;
    JButton createNewPlayerTeam2;
    JButton startGameButton;
    String[] array = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] arrayTeam2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    JScrollPane listScrollPaneTeamA;
    JScrollPane listScrollPaneTeamB;
    JButton next;
	
	public SelectPlayersMenu(Team a, Team b) {
		super("Select Players");
		teamA = a;
		teamB = b;
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
		listPlayersTeam1 = new JList<String>(listModelTeam1);
        listPlayersTeam1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPlayersTeam1.setSelectedIndex(0);
        listPlayersTeam1.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(listPlayersTeam1);
        gc.gridy = 2;
        gc.weighty = 0.05;
        gc.ipadx = 320;
        gc.gridwidth = 2;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane, gc);
		
        gc.fill = gc.NONE;
        gc.anchor = gc.EAST;
        selectPlayerTeam1 = new JButton("Select Player");
        gc.ipadx = 0;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.weighty = 0.2;
        //gc.ipadx = 60;
        add(selectPlayerTeam1, gc);
        selectPlayerTeam1.addActionListener(this);
        
        gc.anchor = gc.WEST;
        createNewPlayerTeam1 = new JButton("Add New Player");
        gc.ipadx = 0;
        gc.insets = new Insets(0, 10, 0, 0);
        gc.gridx = 0;
        add(createNewPlayerTeam1, gc);
        createNewPlayerTeam1.addActionListener(this);
        
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
		listPlayersTeam2 = new JList<String>(listModelTeam2);
        listPlayersTeam2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPlayersTeam2.setSelectedIndex(0);
        listPlayersTeam2.setVisibleRowCount(5);
        listScrollPane2 = new JScrollPane(listPlayersTeam2);
        gc.gridy = 6;
        //gc.gridx = 0;
        gc.weighty = 0.03;
        gc.ipadx = 320;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(listScrollPane2, gc);
        
        gc.fill = gc.NONE;
        gc.anchor = gc.EAST;
        gc.ipadx = 0;
        selectPlayerTeam2 = new JButton("Select Player");
        //gc.insets = new Insets(0, 0, 0, 50);
        gc.gridy = 7;
        gc.gridx = 1;
        gc.weighty = 0.2;
        add(selectPlayerTeam2, gc);
        selectPlayerTeam2.addActionListener(this);
        
        gc.anchor = gc.WEST;
        createNewPlayerTeam2 = new JButton("Add New Player");
        gc.insets = new Insets(0, 10, 0, 0);
        gc.gridx = 0;
        add(createNewPlayerTeam2, gc);
        createNewPlayerTeam2.addActionListener(this);
        
        JLabel lab = new JLabel("Section for lineups");
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTH;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weighty = 0.03;
        gc.weightx = 1;
        add(lab, gc);
       
        listModelLineupA = new DefaultListModel<String>();
        listModelLineupB = new DefaultListModel<String>();
        for (int i = 0; i < 9; i++) {
        	listModelLineupA.addElement(Integer.toString(i+1));
        	listModelLineupB.addElement(Integer.toString(i+1));
        }
        
        listLineup1 = new JList<String>(listModelLineupA);
        listLineup1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listLineup1.setSelectedIndex(0);
        listLineup1.setVisibleRowCount(9);
        listScrollPaneTeamA = new JScrollPane(listLineup1);
        gc.gridx = 2;
        gc.gridy = 1;
        gc.weighty = 0.03;
        gc.gridheight = 3;
        gc.ipadx = 200;
        add(listScrollPaneTeamA, gc);
        
        JLabel lab2 = new JLabel("Section for second lineup");
        gc.ipadx = 0;
		gc.gridx = 2;
		gc.gridy = 4;
		add(lab2, gc);
		
		
		listLineup2 = new JList<String>(listModelLineupB);
        listLineup2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listLineup2.setSelectedIndex(0);
        listLineup2.setVisibleRowCount(9);
        listScrollPaneTeamB = new JScrollPane(listLineup2);
        gc.gridx = 2;
        gc.gridy = 5;
        gc.weighty = 0.03;
        gc.gridheight = 3;
        gc.ipadx = 200;
        add(listScrollPaneTeamB, gc);
        
        startGameButton = new JButton("Start Game!");
        startGameButton.addActionListener(this);
        gc.gridx = 2;
        gc.gridy = 8;
        gc.anchor = gc.NORTH;
        gc.gridheight = 1;
        gc.ipadx = 50;
        add(startGameButton, gc);
        
		setSize(900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createNewPlayerTeam1) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new createNewPlayerGUI("Create New Team", teamA, listModelTeam1).createAndShowGUI(teamA);
				}
			});
		}
		else if (e.getSource() == createNewPlayerTeam2) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new createNewPlayerGUI("Create New Team", teamB, listModelTeam2).createAndShowGUI(teamB);
				}
			});
		}
		else if(e.getSource() == selectPlayerTeam1) {
			int index = listLineup1.getSelectedIndex();
			String player = listModelLineupA.get(index).charAt(0) + " " + listPlayersTeam1.getSelectedValue();
			System.out.println(index);
			listModelLineupA.set(index, player);
		}
		else if(e.getSource() == selectPlayerTeam2) {
			int index = listLineup2.getSelectedIndex();
			String player = listModelLineupB.get(index).charAt(0) + " " + listPlayersTeam2.getSelectedValue();
			System.out.println(index);
			listModelLineupB.set(index, player);
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
	public static void createAndShowGUI(Team a, Team b) {
     //Create and set up the window.

     //Create and set up the content pane.
		JFrame frame = new SelectPlayersMenu(a, b);

     //Display the window.
		frame.setVisible(true);
	}
	
    public class createNewPlayerGUI extends JFrame implements ActionListener {
    	
    	JButton create;
    	JTextField playerName;
    	JTextField playerPosition;
    	Team t;
    	DefaultListModel<String> mode;
    	public createNewPlayerGUI(String title, Team team, DefaultListModel<String> model) {
    		super(title);
    		
    		setLayout(new GridBagLayout());
    		t = team;
    		mode = model;
    		GridBagConstraints gc = new GridBagConstraints();
    		gc.anchor = gc.CENTER;
    		gc.gridx=0;
    		gc.gridy=0;
    		gc.fill = GridBagConstraints.NONE;
    		gc.insets = new Insets(0, 0, 0, 5);
    		playerName = new JTextField("Player");
    		playerPosition = new JTextField("Position");
    		create = new JButton("Create Player");
    		gc.ipady = 7;
    		gc.ipadx = 50;
    		add(playerName, gc);
    		gc.insets = new Insets(20, 0, 0, 5);
    		gc.gridx=1;
    		gc.gridy = 1;
    		gc.ipady = 0;
    		gc.ipadx = 0;
    		add(create, gc);
    		
    		gc.gridy = 1;
    		gc.gridx = 0;
    		gc.ipadx = 40;
    		gc.ipady = 7;
    		add(playerPosition, gc);
    		create.addActionListener(this);
    		
    		
    		setSize(400, 400);
    		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	}
    	public void createAndShowGUI(Team t) {
    		JFrame frame = new createNewPlayerGUI("Create New Team", t, mode);
    		
    		frame.setVisible(true);
    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				mode.addElement(playerName.getText());
				t.addPlayer(playerName.getText(), playerPosition.getText());
				dispose();
			}
			
		}
    }
	
}
