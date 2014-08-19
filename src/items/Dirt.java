package items;

import interfaces.Item;
import items.ItemType;


import java.util.ArrayList;
import java.util.Arrays;


public class Dirt implements Item{
    public Dirt(){
        
    }
    
	public void use(Item useOn){
	    
	}
	public ItemType getType(){
	    return type;
	}
	public String getName(){
	    return name;
	}
	public String getDesc(){
	    return "A pile of dirt";
	}
	public ArrayList<String> getRecipe(){
	    return new ArrayList(Arrays.asList(RECIPE));
	}
    
    private ItemType type = ItemType.DIRT;
    private String name = "dirt";
	private static final String[] RECIPE = {};
}