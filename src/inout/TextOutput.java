package inout;

import interfaces.*;
import map.*;
import animals.Animal;
import boundaries.Boundary;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class TextOutput implements Output {
	
	/**
	 * Outputs two parts, a description of the area and the most recent info message
	 */
	public TextOutput(Map aMap){
		this.map = aMap;
		mapDesc = new ArrayList<String>(5);
		textBuff = new ArrayList<String>(10);
		
		dirVals = Direction.values();
	}
	
	/**
	 * Update the text of the buffer
	 * @param text
	 * TODO: Ensure that the text starts a new line when max char width is exceeded.
	 */
	public void updateText(String someText){
		if(textBuff.size() <= 10){
			textBuff.add(someText);
		}
		else{
			textBuff.remove(0);
			textBuff.add(someText);
		}
	}
	
	/**
	 * Updates the view description of the buffer
	 * @param aMap		The relevant map.
	 * @param viewX		The x coordinate of the top left of the view port
	 * @param viewY		The y coordinate of the top left of the view port
	 * TODO: Ensure the descriptions don't exceed the maximum char width by inserting \n 
	 * 			when String.length >= maxWidth
	 */
	public void updateView(int viewX, int viewY){
		Tile viewTile = map.getTile(viewX, viewY);
		mapDesc.clear();
		//Describe ground type
		mapDesc.add(genGroundDesc(viewTile));
		//Describe ground slope
		mapDesc.add(genSlopeDesc(viewTile));
		//Describe boundaries 
		mapDesc.add(genBoundDesc(viewTile));
		//Describe objects on tile
		mapDesc.add(genObjectDesc(viewTile));
		//Describe animals on tile
		mapDesc.add(genAnimalDesc(viewTile));
		
	}
	
	/**
	 * Prints out the descriptor and latest information message.
	 */
	public void printFrameToConsole(){
		//Print out the description formatted so it isn't too wide
		System.out.println(DIVIDE);
		for(String desc : mapDesc){
			System.out.println(wrapText(desc, TEXT_WIDTH));
		}
		
		//Print out the last text message recieved
		System.out.println(DIVIDE);
		System.out.println(textBuff.get(textBuff.size()-1));
		System.out.println(DIVIDE);
	}
	
	public void printFrameToTextArea(JTextArea textArea){
		//Reset area ready for rewriting
		textArea.setText("");
		//Print out the description formatted so it isn't too wide
		textArea.append("\n"+DIVIDE+"\n");
		for(String desc : mapDesc){
			textArea.append(wrapText(desc, TEXT_WIDTH)+"\n");
		}
		
		//Print out the last text message recieved
		textArea.append(DIVIDE+"\n");
		textArea.append(textBuff.get(textBuff.size()-1)+"\n");
		textArea.append(DIVIDE);
	}
	
	/**
	 * Generates a description of the boundaries of a tile
	 * @param aTile
	 * @return
	 */
	private String genBoundDesc(Tile aTile){
		StringBuilder buff = new StringBuilder();

		
		for(int i=0; i<4; i++){
			Boundary bound = map.getBoundaryPlane().getBoundary(aTile.getBorder(i));
			//If there is one, describe it
			if(bound != null){
				buff.append("You see a "+bound.getName()+" to your "+dirVals[i]+",");
				if(bound.canPass()){
					buff.append(" it looks like you can pass through it. ");
				} else{
					buff.append(" it looks impossible to pass through. ");
				}
			}
		}
		return buff.toString();
	}
	
	/**
	 * Generates a description of the ground
	 * @param aTile
	 * @return	Description of the ground
	 */
	private String genGroundDesc(Tile aTile){
		StringBuilder buff = new StringBuilder();
		buff.append("The ground here is ");
		switch(aTile.getGroundType()){
		case SNOW:
			buff.append("white with snow.");
			break;
		case DIRT:
			buff.append("bare dirt.");
			break;
		case GRASS:
			buff.append("green with lush grass.");
			break;
		case SAND:
			buff.append("very sandy.");
			break;
		case ROCK:
			buff.append("solid rock.");
			break;
		case WATER:
			buff.append("not ground at all! Water flows around you.");
			break;
		}
		return buff.toString();
	}
	
	/**
	 * Generates a text description of a tile's angle based on tile properties.
	 * @param aTile			The tile to query
	 * @return				A string description.
	 */
	private String genSlopeDesc(Tile aTile){
		int diff1 = Math.abs(aTile.getCorner(0).getHeight() - aTile.getCorner(2).getHeight());
		int diff2 = Math.abs(aTile.getCorner(1).getHeight() - aTile.getCorner(3).getHeight());
		if(diff1<5 && diff2<5){
			return "The ground is fairly level.";
		} else if(diff1<10 && diff2<10){
			return "The ground is slightly inclined.";
		} else if(diff1<20 && diff2<20){
			return "The ground is very sloped!";
		} else if(diff1<30 && diff2<30){
			return "The ground here is a almost too steep to walk up.";
		} else {
			return "The ground here is unpassable.";
		}
		
	}
	
	/**
	 * Generates a description of the items on a tile.
	 * @param aTile			The tile to query
	 * @return				The description.
	 */
	private String genObjectDesc(Tile aTile){
		ArrayList<Item> items = map.getItemPlane().getItems(aTile);
		
		if(items.size() == 0){
			return "";
		}
		else{
			StringBuilder buff = new StringBuilder();
			buff.append("The ground has on it;");
			for(Item item : items){
				buff.append(" a ");
				buff.append(item.getName()+",");
			}
			//See genAnimalDesc
			buff.setCharAt(buff.length()-1, '.');
			return buff.toString();
		}
	}
	
	
	/**
	 * Generates a description of the animals on a tile.
	 * @param aTile				The tile to query
	 * @return					The desc.
	 */
	private String genAnimalDesc(Tile aTile){
		ArrayList<Animal> animals = map.getAnimalPlane().getAnimals(aTile);
		if(animals == null){
			animals = new ArrayList<Animal>();
		}
		if(animals.size() == 0){
			return "";
		} else{
			StringBuilder buff = new StringBuilder();
			buff.append("Around you, you see;");
			for(Animal animal : animals){
					buff.append(" "+animal.getName()+",");
			}
			//Don't want a trailing ','
			//There WILL be at least one description here, no worries about a lone '.'
			buff.setCharAt(buff.length()-1, '.');
			return buff.toString();
		}
	}
	
	/**
	 * Wraps text to a maximum length ready for printing out 
	 * by inserting \n.
	 * @param str			Text to wrap
	 * @param width			Maximum length of the line
	 * @return				Wrapped text
	 */
	private String wrapText(String str, int width){
		//Grab a string builder for it's nice methods
		StringBuilder editor = new StringBuilder(str);
		//Find out how many times we need to insert \n
		int numLines = editor.length()/width;
		//Insert newlines
		for(int i=1; i<=numLines; i++){
			int insertPoint = (width*i)+1;
			//Don't want space at the start of a line
			if(editor.charAt(insertPoint) == ' '){
				editor.deleteCharAt(insertPoint);
			}
			editor.insert(insertPoint, "\n");
		}
		return editor.toString();
	}

	private ArrayList<String> mapDesc;
	private ArrayList<String> textBuff;
	
	//Cached values 
	private Direction[] dirVals;
	
	private Map map;
	
	private static final String DIVIDE = "------------------------------------------------------------------";
	private static final int TEXT_WIDTH = 121;
}
