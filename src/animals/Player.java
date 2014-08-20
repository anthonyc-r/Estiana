package animals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.*;

import exceptions.EndOfMapException;
import exceptions.ItemNotFoundException;
import items.ItemType;
import map.Direction;
import map.GameMap;
import map.Tile;
import map.ItemPlane;
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
        map = aMap;
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
    
	public void pickUpItem(Item anItem) throws ItemNotFoundException{
        Tile standingOn = map.getTile(getX(), getY());
        ItemPlane iPlane = map.getItemPlane();
        iPlane.getItems(standingOn);
        iPlane.removeItem(standingOn, anItem);
        inventory.add(anItem);
	}
    
	public void dropItem(Item anItem){
        logger.info("Dropping item...");
        logger.info("Getting tile standing on...");
        Tile standingOn = map.getTile(getX(), getY());
        ItemPlane iPlane = map.getItemPlane();
        logger.info("Calling place item...");
        iPlane.placeItem(standingOn, anItem);
        logger.info("Removing item from inventory");
        inventory.remove(anItem);
	}
	
	private GameMap map;
	private ArrayList<Item> inventory = new ArrayList<Item>(10);
    
    private static final Logger logger = Logger.getLogger(Player.class.getName());
}
