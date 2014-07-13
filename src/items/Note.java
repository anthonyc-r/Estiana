package items;

import interfaces.Item;

public class Note implements Item {
	
	/**
	 * Initiates a new 'note' item
	 * @param content		The message written on the note
	 * @param adj			The notes adjective(i.e. 'green', 'crumpled')
	 */
	public Note(String adj, String txtContent){
		adjective = adj;
		content = txtContent;
	}
	
	public String getName(){
		return adjective +" "+NAME;
	}
	
	public String getDesc(){
		return "The writing on the "+ adjective+" "+NAME+" says: \""+content+"\"";
	}
	
	private String content;
	private String adjective;
	private static final String NAME = "paper note";
}
