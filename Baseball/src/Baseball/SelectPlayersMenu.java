package Baseball;

import java.awt.Dimension;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
    Team teamA;
    Team teamB;
    JTable j2;
    JTable j;
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
    Game game;
	
	public SelectPlayersMenu(Game g, Team a, Team b) {
		super("Select Players");
		game = g;
		teamA = a;
		teamB = b;
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		listModelTeam1 = new DefaultListModel<String>();
        listModelTeam2 = new DefaultListModel<String>();
        
        for (int j = 0; j < b.returnRoster(); j++) {
        	String el = b.getPlayer(j).getName();
        	listModelTeam2.addElement(el);
        }
        JLabel setupTeam1 = new JLabel("Enter information for Team " + teamA.getTeamName());
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(10, 12, 0, 0);
        gc.anchor = gc.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		add(setupTeam1, gc);
		
		gc.anchor = gc.NORTHWEST;
		gc.insets = new Insets(10, 10, 0, 0);
        gc.gridy = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        
        // Column Names
        String[] columnNames = {"Name", "G", "H", "AB", "2B", "3B", "HR", "RBI", "SO", "GO", "FO", "BB", "AVG"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (int i = 0; i < a.returnRoster(); i++) {
        	System.out.println(a.returnRoster());
        	String[] data = new String[13];
        	String el = a.getPlayer(i).getName();
        	data[0] = el;
        	for (int j = 0; j < 11; j++) {
        		data[j+1] = Integer.toString(a.getPlayer(i).getStat(j));
        	}
        	data[12] = Double.toString(a.getPlayer(i).getAvg());
        	model.addRow(data);
        }
      
        // Initializing the JTable
        j2 = new JTable(model);
        JScrollPane teamAscrollPane2 = new JScrollPane(j2);
        teamAscrollPane2.setPreferredSize(new Dimension(530, 100));
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(teamAscrollPane2, gc);
		
        gc.fill = gc.NONE;
        gc.anchor = gc.EAST;
        selectPlayerTeam1 = new JButton("Select Player");
        gc.ipadx = 0;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.weighty = 0.2;
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
		
		gc.anchor = gc.NORTHWEST;
		gc.insets = new Insets(10, 10, 0, 0);
        gc.gridy = 6;
        gc.fill = GridBagConstraints.HORIZONTAL;
      
        // Column Names
        DefaultTableModel model2 = new DefaultTableModel(columnNames, 0);
        
        for (int i = 0; i < b.returnRoster(); i++) {
        	String[] data = new String[13];
        	String el = b.getPlayer(i).getName();
        	data[0] = el;
        	for (int j = 0; j < 11; j++) {
        		data[j+1] = Integer.toString(b.getPlayer(i).getStat(j));
        	}
        	data[12] = Double.toString(b.getPlayer(i).getAvg());
        	model2.addRow(data);
        }
      
        // Initializing the JTable
        j = new JTable(model2);
        JScrollPane teamBscrollPane = new JScrollPane(j);
        teamBscrollPane.setPreferredSize(new Dimension(530, 100));
        //gc.fill = GridBagConstraints.HORIZONTAL;
        add(teamBscrollPane, gc);
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
        
        startGameButton = new JButton("Select Pitchers");
        startGameButton.addActionListener(this);
        gc.gridx = 2;
        gc.gridy = 8;
        gc.anchor = gc.NORTH;
        gc.gridheight = 1;
        gc.ipadx = 50;
        add(startGameButton, gc);
        
		setSize(1000, 600);
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
					new createNewPlayerGUI("Create New Team", teamA, j2).createAndShowGUI(teamA);
				}
			});
		}
		else if (e.getSource() == createNewPlayerTeam2) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new createNewPlayerGUI("Create New Team", teamB, j).createAndShowGUI(teamB);
				}
			});
		}
		//user should be prevented from adding a player to position 3 when position 2 is not filled
		else if(e.getSource() == selectPlayerTeam1) {
			System.out.println(j2.getValueAt(j2.getSelectedRow(), 0).toString());
			int index = listLineup1.getSelectedIndex();
			if(index > 0) {
				if(!listModelLineupA.get(index-1).equals(Integer.toString(index))) {
					String player = listModelLineupA.get(index).charAt(0) + " " + j2.getValueAt(j2.getSelectedRow(), 0).toString();
					listModelLineupA.set(index, player);
					int x = teamA.getPlayerIndexInRoster(j2.getValueAt(j2.getSelectedRow(), 0).toString());
					teamA.addToLineup(x);
				} else {
					System.out.println("Error");
				}
			} else {
				String player = listModelLineupA.get(index).charAt(0) + " " + j2.getValueAt(j2.getSelectedRow(), 0).toString();
				listModelLineupA.set(index, player);
				int x = teamA.getPlayerIndexInRoster(j2.getValueAt(j2.getSelectedRow(), 0).toString());
				teamA.addToLineup(x);
			}
		}
		else if(e.getSource() == selectPlayerTeam2) {
			int index = listLineup2.getSelectedIndex();
			if (index > 0) {
				if(!listModelLineupB.get(index-1).equals(Integer.toString(index))) {
					String player = listModelLineupB.get(index).charAt(0) + " " + j.getValueAt(j.getSelectedRow(), 0).toString();
					System.out.println(index);
					listModelLineupB.set(index, player);
					int x = teamB.getPlayerIndexInRoster(j.getValueAt(j.getSelectedRow(), 0).toString());
					teamB.addToLineup(x);
				} else {
					System.out.println("Error");
				}
			} else {
				String player = listModelLineupB.get(index).charAt(0) + " " + j.getValueAt(j.getSelectedRow(), 0).toString();
				//System.out.println(index);
				listModelLineupB.set(index, player);
				int x = teamB.getPlayerIndexInRoster(j.getValueAt(j.getSelectedRow(), 0).toString());
				teamB.addToLineup(x);
			}
		} else if (e.getSource() == startGameButton) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					System.out.println("run");
					game.switchToSelectPitchersMenu();
					dispose();
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
	public void createAndShowGUI() {
     //Create and set up the window.
     //Create and set up the content pane.
     //Display the window.
		this.setVisible(true);
	}
	
    public class createNewPlayerGUI extends JFrame implements ActionListener {
    	
    	JButton create;
    	JTextField playerName;
    	JTextField playerPosition;
    	Team t;
    	JTable table;
    	public createNewPlayerGUI(String title, Team team, JTable tb) {
    		super(title);
    		
    		setLayout(new GridBagLayout());
    		t = team;
    		table = tb;
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
    		JFrame frame = new createNewPlayerGUI("Create New Team", t, table);
    		
    		frame.setVisible(true);
    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
//				m.addRow(rowData);
//				mode.addElement(playerName.getText());
				t.addPlayer(playerName.getText(), playerPosition.getText());
				String[] data = new String[13];
				Player p = t.getPlayer(t.returnRoster()-1);
	        	data[0] = p.getName();
	        	for (int j = 0; j < 11; j++) {
	        		data[j+1] = Integer.toString(p.getStat(j));
	        	}
	        	data[12] = Double.toString(p.getAvg());
	        	m.addRow(data);
				dispose();
			}
			
		}
    }
	
}
