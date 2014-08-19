package exceptions;

import boundaries.Boundary;

public class ItemNotFoundException extends Exception {
	
	public ItemNotFoundException(){
		super("Could not find item.");
	}
}
