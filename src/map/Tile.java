package map;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Animal;
import interfaces.Item;
import interfaces.Placeable;

/**
 * Represents a tile in the game map. 
 * Where borders are stored in arrays, 0 is the North face, 1 is East, 3 is South, 4 is West.
 * @author meguca
 *
 */
public class Tile implements Placeable, Serializable{
	
	/**
	 * Init a new tile with it's four corners.
	 * @param aCornerList			An array of corners. 0=NW, 1=NE, etc.
	 */
	public Tile(ArrayList<Corner> aCornerList){
		//Grab the 4 corners of the tile
		corners.addAll(aCornerList);
		//Create borders based on corners
		for(int i=0; i<4; i++){
			borders.add(new Border(new ArrayList(corners.subList(i, i+1))));
		}
		
		
	}
	
	/**B-B-BUT ENCAPSULATION!**/
	public Corner getCorner(int n){
		return corners.get(n);
	}
	
	public Border getBorder(int n){
		return borders.get(n);
	}
	
	public String toString(){
		return "implemented in textoutput now.";
	}
	
	public int updateHeight(){
		return 0;
	}
	
	public int getHeight(){
		return avgHeight;
	}
	
	/**
	 * For use with a possible ASCII map
	 * @return					Char representation of a tile.
	 */
	public char toChar(){
		return '?';
	}
	
	public GroundType getGroundType(){
		return corners.get(0).getGroundType();
	}
	

	private ArrayList<Corner> corners = new ArrayList<Corner>(4);
	private ArrayList<Border> borders = new ArrayList<Border>(4);
	private int avgHeight;
}
