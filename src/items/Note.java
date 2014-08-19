package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;
import items.ItemType;

public class Note implements Item {
	
	/**
	 * TODO: Change String adj to an obj of type Attribute and call modifyItem(Attribute)
	 * Initiates a new 'note' item
	 * @param content		The message written on the note
	 * @param adj			The notes adjective(i.e. 'green', 'crumpled')
	 */
	public Note(String adj, String txtContent){
		adjective = adj;
		content = txtContent;
	}
	
	public ItemType getType(){
		return type;
	}
	
	public String getName(){
		return type.toString();
	}
	
	public String getDesc(){
		return "The writing on the "+type.toString()+" says: \""+content+"\"";
	}
	
	/**
	 * Get items required to make
	 * @return		A list of the types items needed to craft this item
	 */
	public ArrayList<String> getRecipe(){
		return new ArrayList(Arrays.asList(RECIPE));
	}
	
	public void use(Item useOn){
		
	}
	
	private String content;
	private String adjective;
	private ItemType type = ItemType.NOTE;
	//TODO: Add recipe
	private static final String[] RECIPE = {"paper", "pen"};
}
