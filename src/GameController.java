import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameController {
	private Simulation sim;
	private MyPanel panel;
	private ArrayList<JButton> buttons;
	private JPanel buttonPanel;

	/*
	 * The GameController class contains the simulation and MyPanel instances to
	 * manage all user input.
	 * When user input is entered, the GameController will update the game state
	 * through the simulation ad then update the view through the MyPanel.
	 * The GameController is modeled after the mvc design pattern.
	 */
	public GameController(Simulation sim, MyPanel panel, JPanel buttonPanel) {
		this.sim = sim;
		this.panel = panel;
		this.buttonPanel = buttonPanel;
		addButtons();
	}

	private void addButtons() {
		JButton placeBet = new JButton("place bet");
		JButton hit = new JButton("hit");
		JButton stand = new JButton("stand");
		JButton doubleDown = new JButton("double down");

		buttonPanel.add(placeBet);
		buttonPanel.add(hit);
		buttonPanel.add(stand);

		placeBet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sim.startNewRound(10);
				panel.repaint();
			}

		});

		hit.addActionListener(new ActionListener() {

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

			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI(sim.stand());
			}

		});

	}

	private void updateUI(int winCondition) {
		panel.repaint();
		sim.handleWinner(winCondition);
		panel.repaint();
	}
}
