package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;
import items.ItemType;

public class Paper implements Item {

	public Paper(){
		//Set adjective
	}
	
	public ItemType getType(){
		return this.type;
	}
	
	public String getName() {
		return adjective+" "+type.toString();
	}

	public String getDesc() {
		return "A piece of "+type.toString()+". You can write notes on this.";
	}

	public ArrayList<String> getRecipe() {
		return new ArrayList(Arrays.asList(RECIPE));
	}
	
	public void use(Item useOn){
		
	}
	
	private String adjective;
	private ItemType type = ItemType.PAPER;
	//TODO: Add recipe
	private static final String[] RECIPE = {};
}
