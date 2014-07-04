package player;

import java.io.Serializable;

import map.Direction;
import map.Map;
import interfaces.Animal;

/**
 * Class representing a player character.
 * @author meguca
 *
 */
public class Player implements Animal, Serializable {
	
	/**
	 * Init a player character
	 * @param aName			The players name.
	 * @param aMap			The map a player is associated with.
	 */
	public Player(String aName, Map aMap){
		this.xLoc = 0;
		this.yLoc = 0;
		this.name = aName;
		this.health = 100.0;
		this.power = 100.0;
		this.gameMap = aMap;
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
	
	/**
	 * Move a player in some direction
	 * TODO: MOVE VIEW UPDATING AND ANIMAL PLACEMENT TO HERE!
	 * @param dir			The direction in which to move.
	 */
	public void move(Direction dir){
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
		gameMap.getAnimalPlane().placeAnimal(gameMap.getTile(xLoc, yLoc), this);
	}
	
	private String type = "yourself";
	private String name;
	private double health;
	private double power;
	private int xLoc;
	private int yLoc;
	private Map gameMap;
}
