package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.Placeable;
import interfaces.Item;

/*
 * The item plane hovers over the map using a reference to check item compatibility
 */
public class ItemPlane implements Serializable{
	//Constructor
	public ItemPlane(Map aMap){
		itemMap = new HashMap<Placeable, ArrayList<Item>>();
	}
	
	public ArrayList<Item> getItems(Placeable aSurface){
		if(itemMap.get(aSurface) == null){
			ArrayList<Item> newItemList = new ArrayList<Item>();
			itemMap.put(aSurface, newItemList);
		}
		return itemMap.get(aSurface);
	}
	
	public void placeItem(Placeable surface, Item item){
		if(itemMap.containsKey(surface)){
			itemMap.get(surface).add(item);
		} else{
			ArrayList<Item> newItems = new ArrayList();
			newItems.add(item);
			itemMap.put(surface, newItems);
		}
	}
	

	private HashMap<Placeable, ArrayList<Item>> itemMap;
}
