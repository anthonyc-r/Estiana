package player;

import java.io.Serializable;

import map.Direction;
import map.Map;
import interfaces.Animal;

public class Player implements Animal, Serializable {
	
	public Player(String aName, Map aMap){
		this.xLoc = 0;
		this.yLoc = 0;
		this.name = aName;
		this.health = 100.0;
		this.power = 100.0;
		this.gameMap = aMap;
	}
		
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
