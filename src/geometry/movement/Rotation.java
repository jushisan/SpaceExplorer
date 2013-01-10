package geometry.movement;

import geometry.Vector3D;

/**
 * Handles and stores the rotation movements of the spaceship.
 * 
 * @author Judy
 */
public class Rotation extends Vector3D {

	public static final int ROTATE_LEFT = 0;
	public static final int ROTATE_RIGHT = 1;
	private double m_speed = 0.1;

	Direction direction;

	public Rotation(double x, double y, double z, Direction dir) {
		super(x, y, z);
		direction = dir;
		direction.setRotation(this);
	}

	/**
	 * Rotate the ship
	 * 
	 * @param dir
	 *            - direction to rotate the ship (i.e., left or right)
	 * @throws Exception
	 */
	public void rotate(int dir) {
		Vector3D rotate;
		if (dir == ROTATE_LEFT)
			rotate = cross(direction);
		else
			rotate = direction.cross(this);
		rotate.normalize();
		rotate = this.add(rotate.multiply(m_speed));
		x = rotate.x;
		y = rotate.y;
		z = rotate.z;
		normalize();
		System.out.println("rotation: " + x + ", " + y + ", " + z);
		System.out.println("direction: " + direction.x + ", " + direction.y
				+ ", " + direction.z);
	}
}
