package map;

import interfaces.Animal;
import interfaces.Placeable;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalPlane {
	
	public AnimalPlane(Map aMap){
		map = aMap;
	}
	
	public void placeAnimal(Placeable surface, Animal animal){
		//If duplicate, remove and replace
		for(ArrayList<Animal> animals : animalMap.values()){
			for(Animal existingAnimal : animals){
				if(existingAnimal == animal){
					animals.remove(existingAnimal);
				}
			}
		}
		//If surface exists, put in array
		if(animalMap.containsKey(surface)){
			ArrayList<Animal> existingArray = animalMap.get(surface);
			existingArray.add(animal);
		} else{
			ArrayList<Animal> newArray = new ArrayList<Animal>();
			newArray.add(animal);
			animalMap.put(surface, newArray);
		}
	}
	
	public ArrayList<Animal> getAnimals(Placeable surface){
		return animalMap.get(surface);
	}
	
	private Map map;
	private HashMap<Placeable, ArrayList<Animal>> animalMap = new HashMap<Placeable, ArrayList<Animal>>();
}
