package exceptions;

public class BoundaryNotFoundException extends Exception{
	
	public BoundaryNotFoundException(){
		super("Boundary not found.");
	}
}
