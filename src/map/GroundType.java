package map;

import java.io.Serializable;

public enum GroundType implements Serializable{
	SAND, GRASS, DIRT, SNOW, ROCK, WATER;
	
	public String toString(){
		return this.toString().toLowerCase();
	}
}
