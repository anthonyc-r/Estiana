package boundaries;

public class Wall extends Boundary{
	public Wall(){
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
	
	private String name = "wall";
	private String desc = "a sturdy wall preventing passage";
	private boolean passable = false;
}
