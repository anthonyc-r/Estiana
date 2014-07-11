package exceptions;

public class EndOfMapException extends Exception{
	
	public EndOfMapException(int col) {
		super("You cannot move any farther in this direction");		
	}	
}
