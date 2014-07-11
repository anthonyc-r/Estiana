package animals;

import java.io.Serializable;

import exceptions.EndOfMapException;
import map.Direction;
import map.Map;
import inout.TextOutput;

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
		super("Player", aMap, out);
		super.setName(aName);
	}
	
}
