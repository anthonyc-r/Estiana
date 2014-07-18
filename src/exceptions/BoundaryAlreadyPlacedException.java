package exceptions;

public class BoundaryAlreadyPlacedException extends Exception {
	
	public BoundaryAlreadyPlacedException(){
		super("There is a boundary already placed here!");
	}
}
