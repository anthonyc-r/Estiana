package boundaries;

public class Gate extends Boundary{
	public Gate(){
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
	
	private String name = "gate";
	private String desc = "a gate that appears to be open.";
	private boolean passable = true;
}