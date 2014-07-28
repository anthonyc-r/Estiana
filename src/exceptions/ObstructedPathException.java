package exceptions;

import boundaries.Boundary;

public class ObstructedPathException extends Exception {
	
	public ObstructedPathException(Boundary bound){
		super("The "+ bound.getName()+" is blocking your path");
	}
}
