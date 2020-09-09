package Baseball;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	/*this class is essentially designed as a way for the application to have control over which 
	 * GUIs are displayed and which ones are not. If we have each GUI calling each other GUI then there's
	 * no guarantee that the program won't terminate early, etc, etc. 
	 * 
	 * tl:dr, this class should be the central unit of control over the entire application. 
	 * All of the relevant information about save data, file storage etc
	 * should be stored here. 
	 */
	public enum GameState{SelectTeamsMenu, SelectPlayersMenu, GameMenu}
	
	public GameState state;
	public Team teamA;
	public Team teamB;
	
	public Game(String title) {
		super();
		state = GameState.SelectTeamsMenu;
		start();
	}
	
	public void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SelectTeamsMenu.createAndShowGUI();
            }
        });
	}
	public void onUpdate() {
		switch(state) {
		case SelectPlayersMenu:
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                SelectPlayersMenu.createAndShowGUI();
	            }
	        });
	        break;
		case GameMenu:
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                GameMenu.createAndShowGUI();
	            }
	        });
		default:
			break;
		}
	}
	
	public void setTeamA(Team x) {
		teamA = x;
	}
	
	public void setTeamB(Team x) {
		teamB = x;
	}
	
	public Team getTeamA() {
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

}
