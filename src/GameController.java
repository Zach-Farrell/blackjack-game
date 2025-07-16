public class GameController {
	private Simulation sim;
	private MyPanel panel;

	/*
	 * The GameController class contains the simulation and MyPanel instances to
	 * manage all user input.
	 * When user input is entered, the GameController will update the game state
	 * through the simulation ad then update the view through the MyPanel.
	 * The GameController is modeled after the mvc design pattern.
	 */
	public GameController(Simulation sim, MyPanel panel) {
		this.sim = sim;
		this.panel = panel;
	}
}
