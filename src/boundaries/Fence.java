package boundaries;

public class Fence extends Boundary{
	public Fence(){
		//WS
	}
	
	public boolean canPass(){
		return passable;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDesc(){
		return desc;
	}
	
	private String name = "fence";
	private String desc = "a wooden fence preventing passage";
	private boolean passable = false;
}