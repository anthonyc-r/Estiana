package animals;

import java.io.Serializable;

import map.GameMap;
import inout.TextOutput;

public class Cow extends Animal implements Serializable{

	public Cow(GameMap aMap){
		super("Cow", aMap);
		super.setName("Molly");
	}
	
	public String getDesc(){
		return "A white and brown cow.";
	}
}
