package gui;

import geometry.Vector3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.Randomizer;

/**
 * Exploring the space through the view of a spaceship.
 * 
 * @author Judy
 */
public class SpaceExplorer extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int MAX_NUM_STARS = 1000;
	private static ArrayList<Star> stars = new ArrayList<Star>();
	private Rectangle frameBounds;
	private int framePositionX;
	private int framePositionY;

	public SpaceExplorer() {

		super("Space Explorer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ExploringPane pane = new ExploringPane(stars);
		setContentPane(pane);
		pack();
		setVisible(true);
		setResizable(false);

		Dimension screenSize = getToolkit().getScreenSize();
		frameBounds = getBounds();
		framePositionX = (screenSize.width - frameBounds.width) / 2;
		framePositionY = (screenSize.height - frameBounds.height) / 2;
		setLocation(framePositionX, framePositionY);

		// Prompt user for number of stars to generate.
		int numStars = -1;
		while (numStars < 0) {
			String userInput = JOptionPane
					.showInputDialog("Enter number of stars to create [1-"
							+ MAX_NUM_STARS + "]:");
			if (userInput == null) {
				System.exit(0);
			}
			try {
				numStars = Integer.parseInt(userInput);
				if (numStars < 1 || numStars > MAX_NUM_STARS)
					showErrorMessage();
			} catch (NumberFormatException e) {
				showErrorMessage();
			}
		}

		// Display instruction
		String instruction = "To control the spaceship: \r\n"
				+ "- press up to move the spaceship forward \r\n"
				+ "- press left to rotate the spaceship counter-clockwise \r\n"
				+ "- press right to rotate the spaceship clockwise \r\n"
				+ "- drag the mouse to change the direction of the spaceship";
		JOptionPane.showMessageDialog(this, instruction, "Instruction",
				JOptionPane.QUESTION_MESSAGE);

		// create stars
		double bound = 1000;
		double maxRadius = 100;
		for (int i = 0; i < numStars; ++i) {
			double x = 2 * bound * Randomizer.random() - bound;
			double y = 2 * bound * Randomizer.random() - bound;
			double z = 2 * bound * Randomizer.random() - bound;
			stars.add(new Star(new Vector3D(x, y, z), new Color(
					(int) (256 * Randomizer.random()), (int) (256 * Randomizer
							.random()), (int) (256 * Randomizer.random())),
					maxRadius * Randomizer.random()));
		}
		repaint();
	}

	private void showErrorMessage() {
		JOptionPane.showMessageDialog(this,
				"Invalid input! Input must be a real integer between 1 to"
						+ MAX_NUM_STARS + ".");
	}
}
