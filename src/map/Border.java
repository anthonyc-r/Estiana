package map;

import java.util.ArrayList;

import interfaces.Item;
import interfaces.Placeable;

public class Border implements Placeable{
	
	public Border(ArrayList<Corner> twoCorners){
		corners.addAll(twoCorners);
		passable = true;
	}
	
	public boolean isPassable(){
		return passable;
	}
	
	public int getSlope(){
		return (corners.get(0).getHeight() - corners.get(1).getHeight());
	}
	
	public int getHeight(){
		return (corners.get(0).getHeight() + corners.get(1).getHeight())/2;
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}
	
	private boolean passable;
	private ArrayList<Corner> corners = new ArrayList<Corner>(2);
	private ArrayList<Item> items = new ArrayList<Item>();
}
