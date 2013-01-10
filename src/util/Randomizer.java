package util;
import java.util.Random;

public class Randomizer {
	
	private static Random rand = new Random(0);
	
	/**
	 * Returns a random number [0, 1).
	 * @return
	 */
	public static double random() {
		return rand.nextDouble();
	}
}
