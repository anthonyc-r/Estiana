package player;

import java.io.Serializable;

import interfaces.Animal;

public class Player implements Animal, Serializable {
	
	public Player(String aName){
		this.name = aName;
		this.health = 100.0;
		this.power = 100.0;
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
	public String getType(){
		return type;
	}
	
	private String type = "yourself";
	private String name;
	private double health;
	private double power;
}
