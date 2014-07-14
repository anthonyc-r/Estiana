package items;

import java.util.ArrayList;
import java.util.Arrays;

import interfaces.Item;

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
	
	public String getType(){
		return Note.type;
	}
	
	public String getName(){
		return adjective +" "+type;
	}
	
	public String getDesc(){
		return "The writing on the "+ adjective+" "+type+" says: \""+content+"\"";
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
	private static String type = "paper note";
	//TODO: Add recipe
	private static final String[] RECIPE = {"paper", "pen"};
}
