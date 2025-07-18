import javax.swing.JFrame;
import java.awt.Dimension;
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

		GameController gameController = new GameController(sim, panel);

		// create the frame for the game
		JFrame frame = new JFrame("Blackjack");

		// on start, size the frame to the screen size
		frame.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		frame.setLayout(null);

		// allow the frame to be resized by the user
		frame.setResizable(true);

		// default to terminating the program when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add custom panel to the JFrame
		frame.setContentPane(panel);
		frame.setVisible(true);

	}
}
