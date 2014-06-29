package player;

import interfaces.Animal;

public class Player implements Animal {
	
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
	
	
	private String name;
	private double health;
	private double power;
}
