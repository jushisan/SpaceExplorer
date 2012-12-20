package gui;

import geometry.Vector2D;
import geometry.Vector3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import movement.Navigator;
import movement.Rotation;

/**
 * This is the main frame for the SpaceExplore application.
 * @author Judy
 *
 */
public class ExploringPane extends JPanel implements MouseMotionListener, KeyListener {

	// spaceship navigator and star objects
	private Navigator nav = new Navigator(0, 0, 0);
	private static ArrayList<Star> stars;
	
	public ExploringPane(ArrayList<Star> stars) {
		this.stars = stars;
		this.setBackground(Color.BLACK);
		setPreferredSize(new Dimension(650, 650));
		this.repaint();
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addKeyListener(this);
	    setVisible(true);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// change the moving direction
		nav.changeDirection(e.getX(), e.getY());
		repaint();
	}
	
	private int x = 0;
	private int y = 0;
	@Override
	public void mouseMoved(MouseEvent e) {
		// Do Nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			// move the ship forward
			nav.move();
			break;
		case KeyEvent.VK_LEFT:
			// rotate the ship leftward
			nav.rotate(Rotation.ROTATE_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			// rotate the ship rightward
			nav.rotate(Rotation.ROTATE_RIGHT);
			break;
		}
		repaint();
	}
	
	/**
	 * Paint the stars at the updated locations.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int bound = 1;
		for (Star star : stars) {
			Vector2D pt = nav.getPlaneViews(star.location);
			if (pt != null) {
				int pointSize = (int) (getSize().width/(2*bound)*Math.atan(star.radius/star.location.subtract(nav.getLocation()).size()));
				System.out.println("Pont size = " + pointSize + "; " + star.location.subtract(nav.getLocation()).size());
				System.out.println("david's results: (" + pt.x  + ", " + pt.y + ")");
				int xPos = (int) ((bound + pt.x)*getSize().width/(2*bound));
				int yPos = (int) ((bound + pt.y)*getSize().height/(2*bound));
				g2d.setColor(star.color);
				g2d.fillOval(xPos, yPos, pointSize, pointSize);
			} else {
				System.out.println("Point is null");
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// Do nothing
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}
}
