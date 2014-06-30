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
		int numCorners = (mapWidth+1)*(mapHeight+1);
		int numTiles = mapWidth*mapHeight;
		int height;
		Tile tile;
		
		ArrayList<Corner> tileCorners = new ArrayList<Corner>(4);
		ArrayList<Corner> cornerList = new ArrayList<Corner>(mapWidth+1);
		ArrayList<ArrayList<Corner>> cornerGrid = new ArrayList<ArrayList<Corner>>(mapHeight+1);
		tileGrid.ensureCapacity(numTiles);
		
		//Get a list of all tile corners put into a 2D grid
		for(int i=0; i<(mapHeight+1); i++){
			cornerList.clear();
			
			for(int j=0; j<(mapWidth+1); j++){
				height = Integer.valueOf(anInput.getInput());
				cornerList.add(new Corner(height, GroundType.GRASS));
			}
			
			cornerGrid.add(cornerList);
		}
		//Assign tile clusters to tiles
		for(int i=0; i<mapHeight; i++){
			for(int j=0; j<mapWidth; j++){
				tileCorners.clear();
				//Add the 4 corners required for a tile
				tileCorners.add(cornerGrid.get(i).get(j));
				tileCorners.add(cornerGrid.get(i).get(j+1));
				tileCorners.add(cornerGrid.get(i+1).get(j));
				tileCorners.add(cornerGrid.get(i+1).get(j+1));
				//Make a new tile out of them
				tile = new Tile(tileCorners);
				tileGrid.get(i).add(tile);
			}
		}
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
