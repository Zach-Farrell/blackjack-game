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
		drawDealerHand(g, sim.getDealer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 4);
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
		int card = 0;
		int offset = 25;
		for (Card c : hand.getCards()) {
			Image cardImage = getCardImage(c.getImagePath());
			g.drawImage(cardImage, x + card * offset, y, null);
			card++;
		}
		offset += 1;
	}

	private void drawDealerHand(Graphics g, Hand hand, int x, int y) {
		int card = 0;
		int offset = 25;
		for (Card c : hand.getCards()) {
			if (card == 1 && !sim.isDealerRevealed()) {
				Image cardImage = getCardImage(c.getBackOfCard());
				g.drawImage(cardImage, x + card * offset, y, null);
				card++;
			} else {

				Image cardImage = getCardImage(c.getImagePath());
				g.drawImage(cardImage, x + card * offset, y, null);
				card++;
			}
		}
		offset += 1;
	}

	private Image getCardImage(String pathToCard) {
		Image card;
		URL cardUrl = getClass().getClassLoader().getResource(pathToCard);
		if (cardUrl == null) {
			System.err.println("Card image not found: " + pathToCard);
			return null;
		}
		try {
			card = ImageIO.read(cardUrl).getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return card;
	}
}
