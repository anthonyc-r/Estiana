package inout;

import interfaces.*;
import map.*;
import animals.Animal;

import java.util.ArrayList;

public class TextOutput implements Output<String> {
	
	/**
	 * Outputs two parts, a description of the area and the most recent info message
	 */
	public TextOutput(Map aMap){
		this.map = aMap;
		mapDesc = new ArrayList<String>(5);
		textBuff = new ArrayList<String>(10);
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
		//Describe objects on tile
		mapDesc.add(genObjectDesc(viewTile));
		//Describe animals on tile
		mapDesc.add(genAnimalDesc(viewTile));
		
	}
	
	/**
	 * Prints out the descriptor and latest information message.
	 */
	public void printFrame(){
		System.out.println(DIVIDE);
		for(String desc : mapDesc){
			System.out.println(desc);
		}
		System.out.println(DIVIDE);
		System.out.println(textBuff.get(textBuff.size()-1));
		System.out.println(DIVIDE);
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
			return "The ground is fairly level";
		} else if(diff1<10 && diff2<10){
			return "The ground is slightly inclined.";
		} else if(diff1<20 && diff2<20){
			return "The ground is very sloped!";
		} else if(diff1<30 && diff2<30){
			return "The ground here is a practically a cliff!";
		} else {
			return "Nahhhh, ya'know";
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
			buff.append("The ground has on it; ");
			for(Item item : items){
				buff.append("a ");
				buff.append(item.getName()+",");
				buff.append("\n");
			}
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
			buff.append("Around you, you see ");
			for(Animal animal : animals){
					buff.append(animal.getName()+", ");
			}
			return buff.toString();
		}
	}

	private ArrayList<String> mapDesc;
	private ArrayList<String> textBuff;
	
	private Map map;
	
	private static String DIVIDE = "------------------------------------------------------------------";
}
