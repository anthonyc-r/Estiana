package map;

import interfaces.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The map class
 * @author meguca
 * Requires that a data file be first validated using the WurmMudToolkit class.
 */
public class Map implements Serializable {
	
	public Map(Input anInput){
		//Get basic info
		extractBasicData(anInput);
		//Set up map arrays appropriately
		tileGrid = new ArrayList<ArrayList<Tile>>(mapHeight);
		for(int i=0; i<mapHeight; i++){
			tileGrid.add(new ArrayList<Tile>(mapWidth));
		}
		//Get tile info and store in map arrays
		extractTileData(anInput);
	}
	
	/**
	 * gets map width, height and it's name and set's instance variables appropriately
	 */
	private void extractBasicData(Input<String> anInput){
		mapName = anInput.getInput();
		mapWidth = Integer.valueOf(anInput.getInput());
		mapHeight = Integer.valueOf(anInput.getInput());
	}
	
	/**
	 * Extract all map data from a data file
	 * @param anInput
	 */
	private void extractTileData(Input<String> anInput){
		
		//Vertical array, i.e. it's a vertical stick with other arrays(horz) stuck out(ycoord) |-
		ArrayList<ArrayList<Integer>> vertList = new ArrayList<ArrayList<Integer>>(mapHeight);
		
		for(int vert=0; vert<(mapHeight+1); vert++){
			//Add a new horizontal stick :^)
			vertList.add(new ArrayList<Integer>(mapWidth));
			for(int horz=0; horz<(mapWidth+1); horz++){
				//Add first line to it
				vertList.get(vert).add(Integer.valueOf(anInput.getInput()));
			}
		}
		tileGrid = toTileArray(vertList);
	}
	
	private ArrayList<ArrayList<Tile>> toTileArray(ArrayList<ArrayList<Integer>> intArray){
		ArrayList<ArrayList<Tile>> tileArray = new ArrayList<ArrayList<Tile>>(mapHeight);
		//Init all arrays
		for(int i=0; i<(mapHeight+1); i++){
			tileArray.add(new ArrayList<Tile>(mapWidth));
		}
		
		//
		for(int i=0; i<(mapHeight); i++){
			for(int j=0; j<(mapWidth); j++){
				//Make corners, as defined by
				//TopLeft(i, j), TopRight(i, j+1), BottomLeft(i+1, j), BottomRight(i+1, j+1)
				ArrayList<Corner> corners = new ArrayList<Corner>(4);
				corners.add(new Corner(intArray.get(i).get(j)));
				corners.add(new Corner(intArray.get(i).get(j+1)));
				corners.add(new Corner(intArray.get(i+1).get(j)));
				corners.add(new Corner(intArray.get(i+1).get(j+1)));
				//Set up tile
				Tile newTile = new Tile(corners);
				//push tile onto array
				tileArray.get(i).add(newTile);
			}
		}
		return tileArray;
	}
	
	/*ACCESSORS AND MUTATORS*/
	public int getMapWidth(){
		return mapWidth;
	}
	public int getMapHeight(){
		return mapHeight;
	}
	public String getMapName(){
		return mapName;
	}
	public Tile getTile(int x, int y){
		return tileGrid.get(y).get(x);
	}
	public ItemPlane getItemPlane(){
		return this.itemPlane;
	}
	public BuildingPlane getBuildingPlane(){
		return this.buildingPlane;
	}
	public AnimalPlane getAnimalPlane(){
		return this.animalPlane;
	}
	private ArrayList<ArrayList<Tile>> tileGrid = null;
	
	private String mapName;
	private int mapWidth;
	private int mapHeight;
	
	private ItemPlane itemPlane = new ItemPlane(this);
	private BuildingPlane buildingPlane = new BuildingPlane(this);
	private AnimalPlane animalPlane = new AnimalPlane(this);
	
	public static final int SEA_HEIGHT = 100;
}
