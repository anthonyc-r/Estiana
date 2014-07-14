package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;

public class Pen implements Item{

	public Pen(){
		//Set adjective
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getName() {
		return adjective+" "+type;
	}

	public String getDesc() {
		return "A "+adjective+" pen, used for writing. Full of ink.";
	}

	public ArrayList<String> getRecipe() {
		return new ArrayList(Arrays.asList(RECIPE));
	}
	
	public void use(Item useOn){
		
	}

	private String content;
	private String adjective;
	private String type = "pen";
	
	private static final String[] RECIPE = {};
}
