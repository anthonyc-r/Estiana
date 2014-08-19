package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;
import items.ItemType;

public class Pen implements Item{

	public Pen(){
		//Set adjective
	}
	
	public ItemType getType(){
		return this.type;
	}
	
	public String getName() {
		return adjective+" "+type.toString();
	}

	public String getDesc() {
		return "A "+type.toString()+", used for writing. Full of ink.";
	}

	public ArrayList<String> getRecipe() {
		return new ArrayList(Arrays.asList(RECIPE));
	}
	
	public void use(Item useOn){
		
	}

	private String content;
	private String adjective;
	private ItemType type = ItemType.PEN;
	
	private static final String[] RECIPE = {};
}
