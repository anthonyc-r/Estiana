package animals;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.EndOfMapException;
import exceptions.ItemNotFoundException;
import items.ItemType;
import map.Direction;
import map.GameMap;
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
	public Player(String aName, GameMap aMap){
		super("yourself", aMap);
		super.setName(aName);
	}
	
	public String getDesc(){
		return "It's you!";
	}
	
    public Item findInInventory(ItemType type) throws ItemNotFoundException{
        for(Item item : inventory){
            ItemType typeCheck = item.getType();
            if(typeCheck == type){
                return item;
            }
        }
        throw new ItemNotFoundException();
    }
    
    public void deleteFromInventory(Item anItem){
        inventory.remove(anItem);
    }
    
    public void addToInventory(Item anItem){
        inventory.add(anItem);
    }
    
	public void pickUpItem(Item anItem){
        
	}
    
	public void dropItem(Item anItem){
		
	}
	
	private GameMap map;
	private ArrayList<Item> inventory = new ArrayList<Item>(10);
}
