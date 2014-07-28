package boundaries;


public class Boundary{
	public Boundary(){
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
	
	private String name;
	private String desc;
	private boolean passable;
}
