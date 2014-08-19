package exceptions;

import boundaries.Boundary;

public class InvalidDirectionException extends Exception {
	
	public InvalidDirectionException(){
		super("Invalid direction.");
	}
}
