import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

/*
 * The Runner class creates the JFrame for the game. 
 * This class contains the main function so it is where the simulation, panel, and gamecontroller are instantiated.
 */
public class Runner {
	public static void main(String[] args) {

		// size of the user screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Simulation sim = new Simulation();

		// instantiate the custom panel with game content and set to visible
		MyPanel panel = new MyPanel(sim, screenSize);
		panel.setVisible(true);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(screenSize);

		panel.setBounds(0, 0, screenSize.width, screenSize.height);
		layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);

		// Button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		buttonPanel.setPreferredSize(new Dimension(1, 30));
		buttonPanel.setBackground(Color.DARK_GRAY);

		JPanel infoPanel = new InfoPanel();
		infoPanel.setBounds(screenSize.width - 120, 20, 100, 100); // Positioned top-right
		layeredPane.add(infoPanel, JLayeredPane.PALETTE_LAYER);

		GameController gameController = new GameController(sim, panel, buttonPanel);

		// create the frame for the game
		JFrame frame = new JFrame("Blackjack");

		// on start, size the frame to the screen size
		frame.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		frame.setLayout(new BorderLayout());

		// allow the frame to be resized by the user
		frame.setResizable(true);

		// default to terminating the program when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add custom panel to the JFrame
		frame.add(layeredPane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

}
