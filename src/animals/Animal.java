package animals;

import java.io.Serializable;

import exceptions.EndOfMapException;
import exceptions.TerrainTooSteepException;
import map.Direction;
import map.Map;
import map.Tile;
import inout.TextOutput;

public class Animal implements Serializable {
	
	/**
	 * Init a player character
	 * @param aType			The type of creature.
	 * @param aName			The players name.
	 * @param aMap			The map a player is associated with.
	 */
	
	public Animal(String aType, Map aMap, TextOutput out){
		this.xLoc = 1;
		this.yLoc = 1;
		this.type = aType;
		//Randgen name
		this.name = "";
		this.health = 100.0;
		this.power = 100.0;
		this.gameMap = aMap;
		this.out = out;
	}
	
	/**Dem gets dem sets**/
	public String getName() {
		return name;
	}

	public double getHealth() {
		return health;
	}

	public double getPower() {
		return power;
	}
	public String getType(){
		return type;
	}
	public int getX(){
		return xLoc;
	}
	public int getY(){
		return yLoc;
	}
	public void setName(String aName){
		this.name = aName;
	}
	
	/**
	 * Move a player in some direction
	 * TODO: MOVE VIEW UPDATING AND ANIMAL PLACEMENT TO HERE!
	 * @param dir			The direction in which to move.
	 */
	public void move(String aDir) throws IllegalArgumentException, EndOfMapException, TerrainTooSteepException{
		Direction dir = Direction.valueOf(aDir.toUpperCase());
		//Check borders for obstruction
		
		//In case move must be reverted
		int oldX = xLoc;
		int oldY = yLoc;
		
		switch(dir){
		case NORTH:
			yLoc -= 1;
			break;
		case EAST:
			xLoc += 1;
			break;
		case SOUTH:
			yLoc += 1;
			break;
		case WEST:
			xLoc -= 1;
			break;
		}
		//Check bounds
		if(xLoc > gameMap.getMapWidth() || xLoc < 0 || yLoc > gameMap.getMapHeight() || yLoc < 0){
			xLoc = oldX;
			yLoc = oldY;
			throw new EndOfMapException();
		}

		//Check steepness
		if(tooSteep(gameMap.getTile(xLoc, yLoc))){
			xLoc = oldX;
			yLoc = oldY;
			throw new TerrainTooSteepException();
		}
			
		gameMap.getAnimalPlane().placeAnimal(gameMap.getTile(xLoc, yLoc), this);
		out.updateView(this.xLoc, this.yLoc);
		
	}
	
	public String getDesc(){
		return "An animal!";
	}
	
	private boolean tooSteep(Tile aTile){
		System.out.println(aTile.getCorner(0).getHeight());
		System.out.println(aTile.getCorner(1).getHeight());
		System.out.println(aTile.getCorner(2).getHeight());
		System.out.println(aTile.getCorner(3).getHeight());
		
		int diff1 = Math.abs(aTile.getCorner(0).getHeight() - aTile.getCorner(2).getHeight());
		int diff2 = Math.abs(aTile.getCorner(1).getHeight() - aTile.getCorner(3).getHeight());
		if(diff1 > 30 || diff2 > 30){
			return true;
		}else{
			return false;
		}
	}
	
	private String type;
	private String name;
	private double health;
	private double power;
	private int xLoc;
	private int yLoc;
	private Map gameMap;
	private TextOutput out;
}