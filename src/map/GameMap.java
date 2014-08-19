package map;

import interfaces.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The map class
 * @author meguca
 * Requires that a data file be first validated using the WurmMudToolkit class.
 */
public class GameMap implements Serializable {
	
	/**
	 * Init a map with an input file containing corner data
	 * @param anInput			The input associated with corner data.
	 */
	public GameMap(Input anInput){
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
	
	/*/**
	 * Converts the array of corners to an array of tiles.
	 * References to corners are shared between adjacent tiles.
	 * @param intArray			The array of array of corners.
	 * @return					An array of array of tiles.
	 */
	/*private ArrayList<ArrayList<Tile>> toTileArray(ArrayList<ArrayList<Integer>> intArray){
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
				//TODO: Fix this! Adjacent tiles should reference the same border.
				corners.add(new Corner(intArray.get(i).get(j)));
				corners.add(new Corner(intArray.get(i).get(j+1)));
				corners.add(new Corner(intArray.get(i+1).get(j+1)));
				corners.add(new Corner(intArray.get(i+1).get(j)));
				//Set up tile
				Tile newTile = new Tile(corners);
				//push tile onto array
				tileArray.get(i).add(newTile);
			}
		}
		return tileArray;
	}*/
	
	/**
	 * TURN BACK NOW BEFORE IT'S TOO LATE
	 * Converts the array of corners to an array of tiles.
	 * References to borders are shared between tiles, and references to corners are shared between borders.
	 * Due to this, the tiles are made in a rather verbose way as I couldn't think of another way to do it.
	 * @param intArray			The array of array of corners.
	 * @return					An array of array of tiles.
	 */
	private ArrayList<ArrayList<Tile>> toTileArray(ArrayList<ArrayList<Integer>> intArray){
		ArrayList<ArrayList<Tile>> tileArray = new ArrayList<ArrayList<Tile>>(mapHeight);
		//Init all arrays
		for(int i=0; i<(mapHeight+1); i++){
			tileArray.add(new ArrayList<Tile>(mapWidth));
		}
		
		//TOP ROW
		//First of top row
		Corner[] ftCorns = {new Corner(intArray.get(0).get(0)),
							new Corner(intArray.get(0).get(1)), 
							new Corner(intArray.get(1).get(1)), 
							new Corner(intArray.get(1).get(0))};
		Border[] ftBords = {new Border(ftCorns[0], ftCorns[1]),
							new Border(ftCorns[1], ftCorns[2]),
							new Border(ftCorns[2], ftCorns[3]),
							new Border(ftCorns[3], ftCorns[0])};
		tileArray.get(0).add(new Tile(new ArrayList<Border>(Arrays.asList(ftBords))));
		
		//Rest of top row
		for(int i=1; i<mapWidth; i++){
			Corner[] rtCorns = {tileArray.get(0).get(i-1).getCorner(1),
								new Corner(intArray.get(0).get(i+1)),
								new Corner(intArray.get(1).get(i+1)),
								tileArray.get(0).get(i-1).getCorner(2)};
			Border[] rtBords = {new Border(rtCorns[0], rtCorns[1]),
								new Border(rtCorns[1], rtCorns[2]),
								new Border(rtCorns[2], rtCorns[3]),
								tileArray.get(0).get(i-1).getBorder(1)};
			tileArray.get(0).add(new Tile(new ArrayList<Border>(Arrays.asList(rtBords))));
			
		}
		
		//REST OF TILES
		for(int i=1; i<mapHeight; i++){
			//First of row
			Corner[] frCorns = {tileArray.get(i-1).get(0).getCorner(3),
								tileArray.get(i-1).get(0).getCorner(2), 
								new Corner(intArray.get(i+1).get(1)), 
								new Corner(intArray.get(1+1).get(0))};
			Border[] frBords = {tileArray.get(i-1).get(0).getBorder(2),
								new Border(frCorns[1], frCorns[2]),
								new Border(frCorns[2], frCorns[3]),
								new Border(frCorns[3], frCorns[0])};
			tileArray.get(i).add(new Tile(new ArrayList<Border>(Arrays.asList(frBords))));
			
			//Rest of row
			for(int j=1; j<mapWidth; j++){
				Corner[] rrCorns = {tileArray.get(i-1).get(j-1).getCorner(3),
									tileArray.get(i-1).get(j).getCorner(2),
									new Corner(intArray.get(i+1).get(j+1)),
									tileArray.get(i).get(j-1).getCorner(2)};
				Border[] rrBords = {tileArray.get(i-1).get(j).getBorder(2),
									new Border(rrCorns[1], rrCorns[2]),
									new Border(rrCorns[2], rrCorns[3]),
									tileArray.get(i).get(j-1).getBorder(1)};
				tileArray.get(i).add(new Tile(new ArrayList<Border>(Arrays.asList(rrBords))));
			}
		}
		
		
		return tileArray;
	}
	
	/*DEM GETSETS*/
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
	public BoundaryPlane getBoundaryPlane(){
		return this.boundaryPlane;
	}
	public AnimalPlane getAnimalPlane(){
		return this.animalPlane;
	}
	private ArrayList<ArrayList<Tile>> tileGrid = null;
	
	private String mapName;
	private int mapWidth;
	private int mapHeight;
	
	private ItemPlane itemPlane = new ItemPlane(this);
	private BoundaryPlane boundaryPlane = new BoundaryPlane(this);
	private AnimalPlane animalPlane = new AnimalPlane(this);
	
	public static final int SEA_HEIGHT = 20;
}
