package geometry;

public class Vector3D {

	public double x;
	public double y;
	public double z;

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double size() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector3D normalize() {
		double denum = size();
		x /= denum;
		y /= denum;
		z /= denum;
		return this;
	}
	 
	public Vector3D add(Vector3D o) {
		return new Vector3D(x + o.x, y + o.y, z + o.z);
	}

	public Vector3D subtract(Vector3D o) {
		return new Vector3D(x - o.x, y - o.y, z - o.z);
	}

	public Vector3D multiply(double scalar) {
		return new Vector3D(scalar * x, scalar * y, scalar * z);
	}

	public double dot(Vector3D o) {
		return x*o.x + y*o.y + z*o.z;
	}
	
	public Vector3D cross(Vector3D o) {
		return new Vector3D(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y
				* o.x);
	}

	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

}