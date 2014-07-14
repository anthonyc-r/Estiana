package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;

public class Paper implements Item {

	public Paper(){
		//Set adjective
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getName() {
		return adjective+" "+type;
	}

	public String getDesc() {
		return "A piece of "+type+", "+adjective+". You can write notes on this.";
	}

	public ArrayList<String> getRecipe() {
		return new ArrayList(Arrays.asList(RECIPE));
	}
	
	public void use(Item useOn){
		
	}
	
	private String adjective;
	private String type = "paper";
	//TODO: Add recipe
	private static final String[] RECIPE = {};
}
