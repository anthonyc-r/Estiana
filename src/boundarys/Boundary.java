package boundarys;


public class Boundary{
	public Boundary(){
		//dat white space
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
	
	private String name = "boundary";
	private String desc = "a marking on the ground";
	private boolean passable = true;
}
