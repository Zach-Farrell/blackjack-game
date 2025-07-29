import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @class GameController
 * @brief Controls user input and updates the simulation and UI accordingly.
 *
 *        The GameController class connects the simulation logic with the user
 *        interface.
 *        It follows the Model-View-Controller (MVC) design pattern, where:
 *        - Simulation is the model,
 *        - MyPanel is the view,
 *        - GameController is the controller.
 * 
 *        It handles button creation, event binding, and updates to the game
 *        state.
 */
public class GameController {
	private Simulation sim;
	private MyPanel panel;
	private ArrayList<JButton> buttons;

	/**
	 * @brief Constructs a GameController with references to the simulation and
	 *        panel.
	 * 
	 * @param sim   The Simulation instance representing game logic.
	 * @param panel The MyPanel instance representing the user interface.
	 */
	public GameController(Simulation sim, MyPanel panel) {
		this.sim = sim;
		this.panel = panel;
		addButtons();
	}

	/**
	 * @brief Adds and configures user control buttons (place bet, hit, stand).
	 *
	 *        This method creates the main button panel, adds action listeners for
	 *        each button,
	 *        and attaches it to the game panel.
	 */
	private void addButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		JButton placeBet = new JButton("place bet");
		JButton hit = new JButton("hit");
		JButton stand = new JButton("stand");
		JButton doubleDown = new JButton("double down"); // Currently unused

		buttonPanel.add(placeBet);
		buttonPanel.add(hit);
		buttonPanel.add(stand);

		panel.add(buttonPanel);

		placeBet.addActionListener(new ActionListener() {
			/**
			 * @brief Starts a new round with a default bet when "place bet" is clicked.
			 * @param e The action event.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.startNewRound(10);
				panel.repaint();
			}
		});

		hit.addActionListener(new ActionListener() {
			/**
			 * @brief Performs a "hit" action when the user clicks "hit".
			 *        If the player busts, starts a new round.
			 * @param e The action event.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sim.hit()) {
					// player busts
					panel.repaint();
					sim.displayBoard("You Busted!");
					sim.startNewRound(sim.getBetAmount());
				}
				panel.repaint();
			}
		});

		stand.addActionListener(new ActionListener() {
			/**
			 * @brief Resolves the round and updates UI when "stand" is clicked.
			 * @param e The action event.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI(sim.stand());
			}
		});
	}

	/**
	 * @brief Updates the game state and UI based on the game result.
	 * 
	 * @param winCondition Integer representing the result of the round:
	 *                     0 = draw, 1 = player win, -1 = dealer win, etc.
	 */
	private void updateUI(int winCondition) {
		panel.repaint();
		sim.handleWinner(winCondition);
		panel.repaint();
	}
}
