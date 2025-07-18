import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private Dimension screenSize;

	public MyPanel(Simulation sim, Dimension screenSize) {
		this.sim = sim;
		this.screenSize = screenSize;
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

		// updateUI();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw image scaled to the panel size
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		drawHand(g, sim.getDealer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 4);
		drawHand(g, sim.getPlayer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 2);

	}

	private void drawHand(Graphics g, Hand hand, int x, int y) {

	}
}
