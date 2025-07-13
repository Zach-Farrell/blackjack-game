import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Runner {
	public static void main(String[] args) {
		// create the frame for the game
		JFrame frame = new JFrame("Blackjack");

		// on start, size the frame to the screen size
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
		frame.setLayout(null);

		// allow the frame to be resized by the user
		frame.setResizable(true);

		// default to terminating the program when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiate the custom panel with game content and set to visible
		MyPanel panel = new MyPanel();
		panel.setVisible(true);

		// add custom panel to the JFrame
		frame.add(panel);
		frame.setVisible(true);

	}
}
