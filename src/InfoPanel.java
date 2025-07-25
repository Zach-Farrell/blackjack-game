import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
	public InfoPanel() {
		setPreferredSize(new Dimension(100, 100));
		setBackground(Color.lightGray);
		setLayout(new FlowLayout());

		add(new JLabel("Player: 0"));
		add(new JLabel("Dealer: ?"));
		add(new JLabel("Chips: $100"));
		add(new JLabel("Bet: $10"));
	}
}
