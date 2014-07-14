package exceptions;

public class TerrainTooSteepException extends Exception{
	
	public TerrainTooSteepException() {
		super("The terrain is too steep to move any further in this direction");		
	}	
}
