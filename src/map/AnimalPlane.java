package map;

import animals.Animal;
import interfaces.Surface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The animal plane is used to associate animals with locations
 * on a map.
 * @author meguca
 *
 */
public class AnimalPlane implements Serializable{
	
	/**
	 * Creates a new animal plane
	 * @param aMap			The map with which the plane is associated with
	 */
	public AnimalPlane(GameMap aMap){
		map = aMap;
	}
	
	/**
	 * Adds an animal to the array associated with a surface on the map.
	 * If no array has been created, one is made and then the animal is added.
	 * @param surface		The surface to place the animal on
	 * @param animal		The animal to be placed.
	 */
	public void placeAnimal(Surface surface, Animal animal){
		//If duplicate, remove and replace
		for(ArrayList<Animal> animals : animalMap.values()){
			for(Animal existingAnimal : animals){
				if(existingAnimal == animal){
					animals.remove(existingAnimal);
					//Break out to stop concurr err
					break;
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
	
	/**
	 * Returns the array of animals associated with a particular surface
	 * @param surface		The surface to be checked
	 * @return				An array of animals on the surface
	 */
	public ArrayList<Animal> getAnimals(Surface surface){
		return animalMap.get(surface);
	}
	
	private GameMap map;
	private HashMap<Surface, ArrayList<Animal>> animalMap = new HashMap<Surface, ArrayList<Animal>>();
}
