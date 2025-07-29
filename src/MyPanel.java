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

/**
 * @class MyPanel
 * @brief A custom JPanel used to render the background and card hands for a
 *        Blackjack game.
 * 
 *        This panel handles:
 *        - Drawing the game background
 *        - Rendering the dealer's and player's hands using card images
 * 
 *        It integrates with the Simulation class to get game state and updates
 *        dynamically.
 */
public class MyPanel extends JPanel {
	private BufferedImage background; /// < Background image of the game table
	private Simulation sim; /// < Simulation object containing game state
	private Dimension screenSize; /// < Screen size used for layout calculations

	/**
	 * @brief Constructs the game panel with a reference to the simulation and
	 *        screen size.
	 * 
	 * @param sim        The Simulation object containing game logic and state.
	 * @param screenSize The screen size used to scale elements.
	 */
	public MyPanel(Simulation sim, Dimension screenSize) {
		this.sim = sim;
		this.screenSize = screenSize;
		setBackground();
	}

	/**
	 * @brief Paints the background and the current state of the player's and
	 *        dealer's hands.
	 * 
	 *        This method is automatically called whenever the panel is repainted.
	 * 
	 * @param g The Graphics object used for drawing.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw image scaled to the panel size
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		drawDealerHand(g, sim.getDealer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 4);
		drawHand(g, sim.getPlayer(), (int) screenSize.getWidth() / 2 - 100, (int) screenSize.getHeight() / 2);
	}

	/**
	 * @brief Loads and sets the background image from resources.
	 * 
	 *        If the image file is missing or cannot be read, prints a stack trace.
	 */
	private void setBackground() {
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

	/**
	 * @brief Draws a hand of cards at the given (x, y) position.
	 * 
	 * @param g    The Graphics object for drawing.
	 * @param hand The hand of cards to draw.
	 * @param x    The x-coordinate where the hand starts.
	 * @param y    The y-coordinate where the hand starts.
	 */
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

	/**
	 * @brief Draws the dealer's hand with the second card hidden if not revealed.
	 * 
	 * @param g    The Graphics object for drawing.
	 * @param hand The dealer's hand.
	 * @param x    The x-coordinate where the hand starts.
	 * @param y    The y-coordinate where the hand starts.
	 */
	private void drawDealerHand(Graphics g, Hand hand, int x, int y) {
		int card = 0;
		int offset = 25;
		for (Card c : hand.getCards()) {
			if (card == 1 && !sim.isDealerRevealed()) {
				Image cardImage = getCardImage(c.getBackOfCard());
				g.drawImage(cardImage, x + card * offset, y, null);
			} else {
				Image cardImage = getCardImage(c.getImagePath());
				g.drawImage(cardImage, x + card * offset, y, null);
			}
			card++;
		}
		offset += 1;
	}

	/**
	 * @brief Loads and scales a card image from the given path.
	 * 
	 * @param pathToCard Path to the card image in the resource folder.
	 * @return A scaled Image object of the card, or null if loading failed.
	 */
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