package map;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
	
	public String toString(){
		return this.toString().toLowerCase();
	}
	
	public int toInt(){
		return this.ordinal();
	}
}
