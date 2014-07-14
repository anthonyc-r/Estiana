package interfaces;

import java.util.ArrayList;

public interface Item {
	public void use(Item useOn);
	public String getType();
	public String getName();
	public String getDesc();
	public ArrayList<String> getRecipe();
}
