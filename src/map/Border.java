package map;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Item;
import interfaces.Surface;

/**
 * A component of a 'tile', four surround a tile.
 * @author meguca
 *
 */
public class Border implements Serializable{
	
	/**
	 * Creates a new border out of the two corners at each end.
	 * @param twoCorners		The two corners.
	 */
	public Border(ArrayList<Corner> twoCorners){
		corners.addAll(twoCorners);
		passable = true;
	}
	
	public Border(Corner c1, Corner c2){
		corners.add(c1);
		corners.add(c2);
		passable = true;
	}
	
	/**
	 * Check if an entity may move though a border.
	 * @return			True if it may. False if not.
	 */
	public boolean isPassable(){
		return passable;
	}
	
	/**
	 * Gets the gradient at which the border lies
	 * @return			The gradient calculated as the differences between the corners.
	 */
	public int getSlope(){
		return (corners.get(0).getHeight() - corners.get(1).getHeight());
	}
	
	/**
	 * Gets the average height of the border
	 * @return			The average height.
	 */
	public int getHeight(){
		return (corners.get(0).getHeight() + corners.get(1).getHeight())/2;
	}
	
	
	private boolean passable;
	private ArrayList<Corner> corners = new ArrayList<Corner>(2);
}
