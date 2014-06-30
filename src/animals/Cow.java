package animals;

import java.io.Serializable;

import interfaces.Animal;

public class Cow implements Animal, Serializable{

	public Cow(){
		//Gen random name
		name = "";
		health = 100.0;
		power = 100.0;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public double getHealth() {
		return health;
	}

	public double getPower() {
		return power;
	}
	
	private String type = "Cow";
	private String name;
	private double health;
	private double power;

}
