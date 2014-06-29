package map;

public class Corner {
	
	public Corner(int aHeight, GroundType aGroundType){
		groundType = aGroundType;
		height = aHeight;
		if(aHeight < WATER_LEVEL){
			groundType = GroundType.WATER;
		}
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
	
	private static int WATER_LEVEL = 30;
}
