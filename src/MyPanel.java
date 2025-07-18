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
	private BufferedImage background;
	private Simulation sim;
	private Dimension screenSize;

	public MyPanel(Simulation sim, Dimension screenSize) {
		this.sim = sim;
		this.screenSize = screenSize;
		drawBackground();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw image scaled to the panel size
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		drawHand(g, sim.getDealer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 4);
		drawHand(g, sim.getPlayer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 2);

	}

	private void drawBackground() {
		try (InputStream in = getClass()
				.getClassLoader()
				.getResourceAsStream("support/Blackjack_Background.jpg")) {
			if (in == null) {
				throw new IllegalArgumentException("Resource not found!");
			}
			background = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawHand(Graphics g, Hand hand, int x, int y) {
		int offset = 0;
		for (Card c : hand.getCards()) {
			URL cardUrl = getClass().getClassLoader().getResource(c.getImagePath());
			if (cardUrl == null) {
				System.err.println("Card image not found: " + c.getImagePath());
				continue;
			}
			try {
				Image cardImage = ImageIO.read(cardUrl);
				Image scaledCard = cardImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
				g.drawImage(scaledCard, x + offset * 25, y, null);
				offset++;
			} catch (IOException e) {
				e.printStackTrace();
			}
			offset += 1;
		}
	}
}
