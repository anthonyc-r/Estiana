package map;

import java.io.Serializable;
import static map.Map.SEA_HEIGHT;

public class Corner implements Serializable{
	
	public Corner(int aHeight, GroundType aGroundType){
		groundType = aGroundType;
		height = aHeight;
		if(aHeight < SEA_HEIGHT){
			groundType = GroundType.WATER;
		}
	}
	public Corner(int aHeight){
		this(aHeight, GroundType.GRASS);
	}
	
	public void setHeight(int newHeight){
		height = newHeight;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public GroundType getGroundType(){
		return this.groundType;
	}
	
	private GroundType groundType;
	private int height;
	
}
