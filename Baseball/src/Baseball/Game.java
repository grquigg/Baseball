package Baseball;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener{
	
	public enum GameState{SelectTeamsMenu, SelectPlayerMenu, GameMenu}
	
	public GameState state;
	public JPanel cards;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JButton next;
	
	public Game(String title) {
		super();
		state = GameState.SelectTeamsMenu;
		label1 = new JLabel("Select Teams Menu");
		label2 = new JLabel("Select Players Menu");
		label3 = new JLabel("Game Menu");
		next = new JButton("Next state");
	}
	
	public void start() {
		//currentMenu.createAndShowGUI();
	}
	
	public void onUpdate() {
		
	}
	
	public void changeStates() {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Game g = new Game("Title");
		g.start();
	}

}
