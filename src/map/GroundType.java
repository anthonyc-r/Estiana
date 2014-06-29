package map;

public enum GroundType {
	SAND, GRASS, DIRT, SNOW, ROCK, WATER;
	
	public String toString(){
		return this.toString().toLowerCase();
	}
}
