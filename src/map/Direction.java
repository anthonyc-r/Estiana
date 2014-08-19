package map;

/**
 * An enum for the 4 supported movement directions.
 * @author meguca
 *
 */
public enum Direction {
	NORTH, EAST, SOUTH, WEST, NORTH_WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST;
	
	/**
	 * More desirable that CAPSLOCK-MODO
	 */
	public String toString(){
		return super.toString().toLowerCase();
	}
	
	/**
	 * For use where numbers 0, 1, 2, 3 in arrays represent the north,
	 * east, south and west edges.
	 * @return
	 */
	public int toInt(){
		return super.ordinal();
	}
}
