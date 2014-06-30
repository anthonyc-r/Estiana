package map;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Animal;
import interfaces.Item;
import interfaces.Placeable;

/**
 * 0N 1E 2S 3W
 * @author meguca
 *
 */
public class Tile implements Placeable, Serializable{
	
	public Tile(ArrayList<Corner> aCornerList){
		//Grab the 4 corners of the tile
		corners.addAll(aCornerList);
		//Create borders based on corners
		for(int i=0; i<4; i++){
			borders.add(new Border(new ArrayList(corners.subList(i, i+1))));
		}
		
		
	}
	
	public Corner getCorner(int n){
		return corners.get(n);
	}
	
	public Border getBorder(int n){
		return borders.get(n);
	}
	
	public String toString(){
		return "TOBEIMPLEMENTED";
	}
	
	public int updateHeight(){
		return 0;
	}
	
	public int getHeight(){
		return avgHeight;
	}
	
	public char toChar(){
		return '?';
	}
	
	public GroundType getGroundType(){
		return corners.get(0).getGroundType();
	}
	
	//TODO: ADD TO ANIMAL LAYER
	public ArrayList<Animal> getAnimals(){
		return this.animals;
	}
	
	
	private ArrayList<Corner> corners = new ArrayList<Corner>(4);
	private ArrayList<Border> borders = new ArrayList<Border>(4);
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private int avgHeight;
}
