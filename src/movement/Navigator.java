package movement;

import java.util.ArrayList;


import geometry.Vector2D;
import geometry.Vector3D;
import gui.Star2DLocation;

/**
 * Handles all the movements of the spaceship, including the direction and the
 * rotation.
 * 
 * @author Judy
 * 
 */
public class Navigator {

	private Vector3D m_location;
	private Direction m_direction;
	private Rotation m_rotation;
	
	public Navigator(int x, int y, int z) {
		m_location = new Vector3D(x, y, z);
		init();
	}
	
	public Navigator() {
		m_location = new Vector3D(0, 0, 0);
		init();
	}
	
	public Vector3D getLocation() {
		return m_location;
	}
	
	/**
	 * Initialise direction and rotation angle of the spaceship.
	 */
	private void init() {
		m_direction = new Direction(1, 0, 0);
		m_direction.normalize();
		m_rotation = new Rotation(0, 0, 1, m_direction);
		m_rotation.normalize();
	}
	
	/**
	 * Move the ship along current direction.
	 * @return current location
	 */
	public Vector3D move() {
		m_location = m_location.add(m_direction);
		System.out.println("location: " + m_location.x + ", " + m_location.y + ", " + m_location.z);
		return m_location;
	}
	
	/**
	 * Rotate the ship.
	 * @param dir - Rotation.ROTATE_LEFT or Rotation.ROTATE_LEFT
	 * @return rotation vector after the rotation.
	 */
	public Vector3D rotate(int dir) {
		m_rotation.rotate(dir);
		return m_rotation;
	}
	
	/**
	 * Change ship direction.
	 * @param screenX
	 * @param screentY
	 * @return direction of the ship after the change.
	 */
	public Vector3D changeDirection(int screenX, int screentY) {
		m_direction.changeDirection(screenX, screentY);
		return m_direction;
	}
	
	public Vector2D getPlaneViews(Vector3D starLocation) {
		return Star2DLocation.view(m_location, m_direction, m_rotation, starLocation);
	}
}
