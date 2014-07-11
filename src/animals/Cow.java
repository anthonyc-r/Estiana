package animals;

import java.io.Serializable;

import map.Map;
import inout.TextOutput;

public class Cow extends Animal implements Serializable{

	public Cow(Map aMap, TextOutput out){
		super("Cow", aMap, out);
	}

}
