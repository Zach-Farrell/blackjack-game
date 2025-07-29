import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @class Runner
 * @brief Initializes and runs the Blackjack game application.
 * 
 *        This class contains the main method which sets up the JFrame window,
 *        creates the game simulation, UI panel, and controller, and starts the
 *        game.
 */
public class Runner {
	/**
	 * @brief The program entry point.
	 * 
	 *        Initializes the simulation, creates the main game panel and
	 *        controller,
	 *        sets up the JFrame window with appropriate size and behavior, then
	 *        displays it.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {

		// Get the size of the user screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Create the game simulation (model)
		Simulation sim = new Simulation();

		// Create the custom game panel (view) and make it visible
		GamePanel panel = new GamePanel(sim, screenSize);
		panel.setVisible(true);

		// Create the game controller to manage interactions
		GameController gameController = new GameController(sim, panel);

		// Create the main JFrame for the game window
		JFrame frame = new JFrame("Blackjack");

		// Set the frame size to fill the entire screen
		frame.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		frame.setLayout(null);

		// Allow user to resize the window
		frame.setResizable(true);

		// Exit the program when the window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the custom game panel as the content pane of the JFrame
		frame.setContentPane(panel);

		// Make the frame visible on screen
		frame.setVisible(true);

	}
}
