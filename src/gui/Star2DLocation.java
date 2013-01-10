package gui;

import geometry.Vector2D;
import geometry.Vector3D;

/**
 * Calculate the star location on the 2D plane view.
 * @author Judy
 */
public class Star2DLocation {

	/**
	 * Distance from ship to projection sphere.
	 */
	private static double SPHERE_DISTANCE = 1.0;

	/**
	 * Solves A*x = b for x using partial-pivoted Gaussian Elimination. Note:
	 * this function alters A and b.
	 * 
	 * @param A
	 *            an n by n matrix
	 * @param b
	 *            a vector of length n
	 * @return x
	 */
	private static double[] gaussianElimination(double[][] A, double[] b) {
		int n = b.length;
		// set up permutation
		int[] perm = new int[n];
		for (int i = 0; i < n; ++i)
			perm[i] = i;
		// convert to upper triangular form
		for (int i = 0; i < n; ++i) {
			// pivot
			for (int j = i + 1; j < n; ++j)
				if (Math.abs(A[perm[i]][i]) < Math.abs(A[perm[j]][i])) {
					int t = perm[j];
					perm[j] = perm[i];
					perm[i] = t;
				}
			// zero out column
			for (int j = i + 1; j < n; ++j) {
				double coeff = -A[perm[j]][i] / A[perm[i]][i];
				A[perm[j]][i] = 0;
				for (int k = i + 1; k < n; ++k)
					A[perm[j]][k] += coeff * A[perm[i]][k];
				b[perm[j]] += coeff * b[perm[i]];
			}
		}
		// run backwards substitution
		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j)
				b[perm[i]] -= b[perm[j]] * A[perm[i]][j];
			b[perm[i]] /= A[perm[i]][i];
		}
		// un-permute
		double[] x = new double[n];
		for (int i = 0; i < n; ++i)
			x[i] = b[perm[i]];
		return x;
	}

	/**
	 * Returns the x, y location of the provided star on the projection plane of
	 * the provided ship.
	 * 
	 * @param shipLocation
	 *            location of ship
	 * @param shipDirection
	 *            direction of ship
	 * @param shipRotation
	 *            rotation of ship
	 * @param starLocation
	 *            location of star
	 * @return projected location of star onto ship's projected plane
	 */
	public static Vector2D view(Vector3D shipLocation, Vector3D shipDirection,
			Vector3D shipRotation, Vector3D starLocation) {
		Vector3D intersection = starLocation.subtract(shipLocation).normalize()
				.multiply(SPHERE_DISTANCE);
		Vector3D cross = shipDirection.cross(shipRotation);
		double[][] A = { { shipDirection.x, shipRotation.x, cross.x },
				{ shipDirection.y, shipRotation.y, cross.y },
				{ shipDirection.z, shipRotation.z, cross.z } };
		double[] b = { intersection.x, intersection.y, intersection.z };
		double[] x = gaussianElimination(A, b);
		if (x[0] > 0) {
			double p = Math.PI / 2 - Math.acos(x[1] / SPHERE_DISTANCE);
			double q = Math.PI / 2 - Math.acos(x[2] / SPHERE_DISTANCE);
			return new Vector2D(q, p);
		}
		return null;
	}
}