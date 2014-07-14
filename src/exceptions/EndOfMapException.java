package exceptions;

public class EndOfMapException extends Exception{
	
	public EndOfMapException() {
		super("You cannot move any farther in this direction");		
	}	
}
