package interfaces;

import java.util.ArrayList;

import items.ItemType;

public interface Item {
	public void use(Item useOn);
	public ItemType getType();
	public String getName();
	public String getDesc();
	public ArrayList<String> getRecipe();
}
