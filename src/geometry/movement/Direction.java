package geometry.movement;

import geometry.Vector3D;

/**
 * Handles and stores the moving direction of the spaceship.
 * @author Judy
 */
public class Direction extends Vector3D {

	public static final int DIRECT_UP = 2;
	public static final int DIRECT_DOWN = 3;
	public static final int DIRECT_LEFT = 4;
	public static final int DIRECT_RIGHT = 5;
	private double m_speed = 0.01;
	
	private Rotation m_rotation;
	
	public Direction(double x, double y, double z) {
		super(x, y, z);
	}
	
	public void setRotation(Rotation rotate) {
		m_rotation = rotate;
	}

	/**
	 * Store mouse drag point.
	 */
	private int m_screenX = 0;
	private int m_screenY = 0;
	
	/**
	 * Change the direction the ship is moving
	 * @param screenX - mouse drag x coordinate
	 * @param screenY - mouse drag y coordinate
	 */
	public void changeDirection(int screenX, int screenY) {
		// calculate the direction of the mouse drag
		int dx = screenX - m_screenX;
		int dy = screenY - m_screenY;
		double denum = Math.sqrt(dx*dx + dy*dy);
		m_screenX = screenX;
		m_screenY = screenY;
		System.out.println("dx = " + dx + ", dy = " + dy);
		if (dx > 0) {
			changeLRDirection(DIRECT_RIGHT, Math.sqrt(dx/denum));
		} else if (dx < 0) {
			changeLRDirection(DIRECT_LEFT, Math.sqrt(-dx/denum));
		}
		if (dy > 0) {
			changeUpDownDirection(DIRECT_UP, Math.sqrt(dy/denum));
		} else if (dy < 0) {
			changeUpDownDirection(DIRECT_DOWN, Math.sqrt(-dy/denum));
		}
	}
	
	/**
	 * Change direction left or right.
	 * @param dir
	 * @param strength
	 */
	private void changeLRDirection(int dir, double strength) {
		Vector3D leftOrRightVector;
		System.out.println("Direction: " + dir);
		if (dir == DIRECT_LEFT)
			leftOrRightVector = m_rotation.cross(this);
		else
			leftOrRightVector = this.cross(m_rotation);
		leftOrRightVector.normalize();
		System.out.println("Change Direction:  " + leftOrRightVector.x + ", " + leftOrRightVector.y + ", " + leftOrRightVector.z);
		leftOrRightVector = this.add(leftOrRightVector.multiply(m_speed*strength));
		x = leftOrRightVector.x;
		y = leftOrRightVector.y;
		z = leftOrRightVector.z;
		this.normalize();
	}
	
	/**
	 * Change direction up or down.
	 * @param dir
	 * @param strength
	 */
	private void changeUpDownDirection(int dir, double strength) {
		Vector3D change;
		Vector3D rotation;
		if (dir == DIRECT_UP) {
			change = m_rotation.multiply(m_speed*strength);
			rotation = m_rotation.add(multiply(m_speed*strength*-1));
		} else {
			change = m_rotation.multiply(m_speed*strength*-1);
			rotation = m_rotation.add(multiply(m_speed*strength));
		}
		change = this.add(change);
		m_rotation.x = rotation.x;
		m_rotation.y = rotation.y;
		m_rotation.z = rotation.z;
		m_rotation.normalize();
		x = change.x;
		y = change.y;
		z = change.z;
		normalize();
	}
}
