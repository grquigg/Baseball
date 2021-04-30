package Baseball;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Game extends JPanel {
	/*this class is essentially designed as a way for the application to have control over which 
	 * GUIs are displayed and which ones are not. If we have each GUI calling each other GUI then there's
	 * no guarantee that the program won't terminate early, etc, etc. 
	 * 
	 * tl:dr, this class should be the central unit of control over the entire application. 
	 * All of the relevant information about save data, file storage etc
	 * should be stored here. 
	 */
	public enum GameState{SelectTeamsMenu, SelectPlayersMenu, SelectPitchersMenu, GameMenu}
	
	public GameState state;
	public Team teamA;
	public Team teamB;
	public ArrayList<Team> teamList;
	public ArrayList<String []> fileData = new ArrayList<String []>();
	private String fileName = "teams.csv";
	
	FileWriter csvWriter;
	
	public Game(String title) {
		super();
		state = GameState.SelectTeamsMenu;
		teamList = new ArrayList<Team>();
		checkForFile();
		start();
	}
	
	private void checkForFile() {
		File fs = new File(fileName);
		if(!fs.exists()) {
			System.out.println("File does not exist"); //file has clearly not been created before
			try {
				createFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("File exists");
			try {
				readFromFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void readFromFile() throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
		String row;
		while((row = csvReader.readLine()) != null) {
//			System.out.println(row);
			String [] data = row.split(",");
			fileData.add(data);
		}
		if(fileData.size() == 0) {
			System.out.println("File empty");
		} else {
			processFileData();
		}
//		System.out.println("Create writer");
//		csvWriter = new FileWriter(fileName);
		
	}

	private void processFileData() {
		System.out.println(fileData.size());
		int numTeams = Integer.parseInt(fileData.get(0)[1]);
		for (int i = 0; i < numTeams; i++) {
			Team team = new Team(fileData.get(i+1)[0]);
			teamList.add(team);
		}
		int cursor = 3 + numTeams;
		System.out.println(cursor);
		for (int j = 0; j < numTeams; j++) {
			System.out.println("New team");
			System.out.println(cursor);
			int numPlayers = Integer.parseInt(fileData.get(cursor)[1]);
			System.out.println(numPlayers);
			cursor++;
			for (int k = 0; k < numPlayers; k++) {
				String name = fileData.get(cursor)[0];
				System.out.println(name);
				String position = fileData.get(cursor)[1];
				Player p;
				if(position.equals("Pitcher")) {
					p = new Pitcher(name, position);
				} else {
					p = new Player(name, position);
				}
				for (int n = 0; n < 11; n++) {
					int temp = Integer.parseInt(fileData.get(cursor)[n+2]);
					p.setStat(n, temp);
				}
				p.calculateAvg();
				cursor++;
				teamList.get(j).addPlayer(p);
			}
			System.out.println(cursor);
			System.out.println("pitchers");
			System.out.println(fileData.get(cursor)[0]);
			int numPitchers = Integer.parseInt(fileData.get(cursor)[1]);
			cursor++;
			for (int m = 0;  m < numPitchers; m++) {
				String name = fileData.get(cursor)[0];
				System.out.println(name);
				if(teamList.get(j).findPlayer(name) != null && teamList.get(j).findPlayer(name).getPosition().equals("Pitcher")) {
					System.out.println("Player already in list");
					Pitcher temp = teamList.get(j).findPitcher(name);
					for (int c = 0; c < 11; c++) {
						int t = Integer.parseInt(fileData.get(cursor)[c+2]);
						temp.setPitchStat(c, t);
						temp.getEra();
						temp.getIP();
					}
					teamList.get(j).findPitcher(name).displayPitchStats();
				} else {
					Pitcher p = new Pitcher(name, "Pitcher");
					for (int c = 0; c < 11; c++) {
						int t = Integer.parseInt(fileData.get(cursor)[c+2]);
						p.setPitchStat(c, t);
						p.getEra();
						p.getIP();
					}
					teamList.get(j).addPlayer(p);
					teamList.get(j).addToRotation(p);
				}
				cursor++;
			}
			cursor+=2;
		}
		
		
	}

	private void createFile() throws IOException {
		csvWriter = new FileWriter(fileName);
		csvWriter.flush();
		csvWriter.close();
	}
	
	private void writeLineToFile(String s) throws IOException {
		csvWriter.append(s + "\n");
	}

	public void start() {
		Game g = this;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SelectTeamsMenu(g, "Select Teams").createAndShowGUI();
            }
        });
	}
	public void onUpdate() {
		Game g = this;
		switch(state) {
			case SelectPlayersMenu:
		        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                new SelectPlayersMenu(g, teamA, teamB).createAndShowGUI();
		            }
		        });
		        break;
			case GameMenu:
		        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                new GameMenu(g, teamA, teamB).createAndShowGUI();
		            }
		        });
		        break;
			case SelectPitchersMenu:
				javax.swing.SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						new SelectPitchersMenu(g, teamA, teamB).createAndShowGUI();
						
					}
				});
				break;
			default:
				break;
			}
	}
	
	public void setTeamA(Team x) {
		teamA = x;
		System.out.println("Changed Team A to " + teamA.getTeamName());
	}
	
	public void setTeamB(Team x) {
		teamB = x;
		System.out.println("Changed Team B to " + teamB.getTeamName());
	}
	
	public Team getTeamA() {
		System.out.println("Team A to " + teamA.getTeamName());
		if(teamA.returnRoster() != 0) {
			System.out.println(teamA.getPlayer(0));
		}
		return teamA;
	}
	
	public Team getTeamB() {
		return teamB;
	}

	public void switchToSelectPlayerState() {
		state = GameState.SelectPlayersMenu;
		onUpdate();
	}
	
	public void switchToGameMenuState() {
		state = GameState.GameMenu;
		onUpdate();
	}
	
	public static void main(String[] args) {
		Game g = new Game("Title");
	}

	public void WriteToFile() {
//		System.out.println("Write to file");
//		System.out.println(teamA.getPlayer(0));
//		System.out.println(teamB.getPlayer(0));
		String teams = "Teams," + Integer.toString(teamList.size());
		try {
			writeLineToFile(teams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Team t: teamList) {
			String teamName = t.getTeamName();
			try {
				writeLineToFile(teamName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writeLineToFile("");
			for (Team t: teamList) {
				writeLineToFile(t.getTeamName());
				String temp = "Players," + Integer.toString(t.returnRoster());
				writeLineToFile(temp);
				for(int i = 0; i < t.returnRoster(); i++) {
					Player p = t.getPlayer(i);
					p.displayStats();
					String player = p.getName() + "," + p.getPosition() + "," + p.toString() + p.getAvg();
					writeLineToFile(player);
				}
				String pitcher = "Pitchers," + Integer.toString(t.returnNumberOfPitchers());
				writeLineToFile(pitcher);
				writeLineToFile("");
				System.out.println(t.returnNumberOfPitchers());
				for(int j = 0; j < t.returnNumberOfPitchers(); j++) {
					Pitcher p = t.getPitcher(0);
					System.out.println(p.toString());
				}
			}
			csvWriter.flush();
			csvWriter.close();
			System.out.println("Successfully wrote to file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Teams,2
//		Black
//		White
//
//		Black
//		Players,0
//
//		White
//		Players,0
	}
	public String returnStr() {
		return "Test String";
	}
	public ArrayList<Team> getTeams() {
		return teamList;
	}
	
	public void addTeam(Team team) {
		teamList.add(team);
	}

	public void switchToSelectPitchersMenu() {
		state = GameState.SelectPitchersMenu;
		onUpdate();
		
	}

}
