package map;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Item;
import interfaces.Surface;

/**
 * Represents a tile in the game map. 
 * Where borders are stored in arrays, 0 is the North face, 1 is East, 3 is South, 4 is West.
 * Corners must be dealt with strangely as things assume corners go around the compass clockwise.
 * 	Whereas corners are read in from West to East, North to South.
 * @author meguca
 *
 */
public class Tile implements Surface, Serializable{
	
	/**
	 * Init a new tile with it's four corners.
	 * @param cLis			An array of corners. 0=NW, 1=NE, 2=SW, 3=SE
	 */
	public Tile(ArrayList<Corner> cLis){
		//Grab the 4 corners of the tile
		corners.addAll(cLis);
		
		for(int i=0; i<3; i++){
			borders.add(new Border(new ArrayList(corners.subList(i, i+2))));
		}
		
		//Create borders based on corners going clockwise around 
		//0 1
		//1 3
		//3 2
		//3 0
		/*borders.add(new Border(cLis.get(0), cLis.get(1)));
		borders.add(new Border(cLis.get(1), cLis.get(3)));
		borders.add(new Border(cLis.get(3), cLis.get(2)));
		borders.add(new Border(cLis.get(3), cLis.get(0)));*/
	}
	
	/**
	 * 0=NW, 1=NE, 2=SW, 3=SE
	 * @param n
	 * @return
	 */
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
