package Baseball;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 759, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(200, 200));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Select Team");
		btnNewButton.setBounds(26, 362, 191, 41);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Please select a team from the list");
		lblNewLabel.setBounds(26, 12, 406, 33);
		panel.add(lblNewLabel);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(26, 60, 675, 274);
		panel.add(list);
		
		JButton btnNewButton_1 = new JButton("Add New Team");
		btnNewButton_1.setBounds(481, 362, 220, 41);
		panel.add(btnNewButton_1);
	}
}
