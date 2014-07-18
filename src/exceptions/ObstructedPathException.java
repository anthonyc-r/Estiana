package exceptions;

public class ObstructedPathException extends Exception {
	
	public ObstructedPathException(){
		super("The "+ " is blocking your path");
	}
}
