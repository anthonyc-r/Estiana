package animals;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.EndOfMapException;
import map.Direction;
import map.Map;
import map.Tile;
import inout.TextOutput;
import interfaces.Item;

/**
 * Class representing a player character.
 * @author meguca
 *
 */
public class Player extends Animal implements Serializable {
	
	/**
	 * Init a player character
	 * @param aName			The players name.
	 * @param aMap			The map a player is associated with.
	 * @param out 			A reference to the text output
	 */
	public Player(String aName, Map aMap, TextOutput out){
		super("yourself", aMap, out);
		super.setName(aName);
	}
	
	public String getDesc(){
		return "It's you!";
	}
	
	public void pickUpItem(Item anItem){
		Tile tile = map.getTile(super.getX(), super.getY());
		ArrayList<Item> items = map.getItemPlane().getItems(tile);
	}
	public void dropItem(){
		
	}
	
	private Map map;
	private ArrayList<Item> inventory = new ArrayList<Item>(10);
}
