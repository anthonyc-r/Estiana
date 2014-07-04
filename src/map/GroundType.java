package map;

import java.io.Serializable;

/**
 * Enum for the differnt types of ground supported
 * @author meguca
 *
 */
public enum GroundType implements Serializable{
	SAND, GRASS, DIRT, SNOW, ROCK, WATER;
	
	/**
	 * PRINT IN LOWER CASE
	 */
	public String toString(){
		return this.toString().toLowerCase();
	}
}
