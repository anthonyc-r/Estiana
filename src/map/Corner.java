package map;

import java.io.Serializable;
import static map.GameMap.SEA_HEIGHT;

/**
 * Represents one of the four vertices that make up a tile.
 * @author meguca
 *
 */
public class Corner implements Serializable{
	
	/**
	 * Init a corner
	 * @param aHeight			The height of the corner above 0-level.
	 * @param aGroundType		The type of groud that the corner represents.
	 * 							Defaults to GRASS, or WATER if below SEA_HEIGHT.
	 */
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
	
	/**
	 * Sets a new height for a corner.
	 * @param newHeight			The desired height.
	 */
	public void setHeight(int newHeight){
		height = newHeight;
	}
	
	/**
	 * Gets the height of the corner.
	 * @return					The corner's height.
	 */
	public int getHeight(){
		return this.height;
	}
	
    /**
    * Adds x to the height of the corner 
    * @param change             The amount to add to the height
    */
    public void addHeight(int change){
        height += change;
    }
    
	/**
	 * Gets the type of ground of the corner
	 * @return					The corners ground type.
	 */
	public GroundType getGroundType(){
		return this.groundType;
	}
    
    public void setGroundType(GroundType grndType){
        this.groundType = grndType;
    }
	
	private GroundType groundType;
	private int height;
	
}
