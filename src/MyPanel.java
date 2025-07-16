import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel {
	private BufferedImage img;
	private Simulation sim;

	public MyPanel(Simulation sim) {
		this.sim = sim;
		// ImageIcon icon = new ImageIcon(imgURL);
		// JLabel thumb = new JLabel();
		// thumb.setIcon(icon);

		// try {
		// img = ImageIO.read(new File("support/Blackjack_Background.jpg"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		try (InputStream in = getClass()
				.getClassLoader()
				.getResourceAsStream("support/Blackjack_Background.jpg")) {
			if (in == null) {
				throw new IllegalArgumentException("Resource not found!");
			}
			img = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL cardUrl = getClass().getClassLoader()
				.getResource("support/deck-images/2_of_clubs.png");
		JLabel cardLabel = new JLabel();
		if (cardUrl == null) {
			System.err.println("Card image not found on classpath!");
		} else {
			ImageIcon ic = new ImageIcon(cardUrl);
			Image imgCard = ic.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
			cardLabel.setIcon(new ImageIcon(imgCard));
		}
		cardLabel.setBounds(0, 0, 100, 150);
		add(cardLabel);

		updateUI();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw image scaled to the panel size
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
