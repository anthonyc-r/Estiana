package interfaces;

import map.Map;

public interface Output<E> {
	
	public void updateText(E object);
	public void updateView(int viewX, int viewY);
	public void printFrame();
}
