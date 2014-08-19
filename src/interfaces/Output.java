package interfaces;

import map.GameMap;
import javax.swing.JTextArea;

public interface Output {
	
	public void updateText(String object);
	public void updateView(int viewX, int viewY);
    public void printFrame();
}
