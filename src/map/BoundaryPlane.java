package map;

import interfaces.Surface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.BoundaryAlreadyPlacedException;
import exceptions.BoundaryNotFoundException;
import boundaries.Boundary;
import boundaries.Wall;

/**
 * To be implemented in a similar way as the animal and item planes.
 * Associates a building object with multiple surfaces, and so much be passed a reference to the map.
 * @author meguca
 *
 */
public class BoundaryPlane implements Serializable {
	
	/**
	 * Init a plane and associate it with a map.
	 * @param aMap
	 */
	public BoundaryPlane(GameMap aMap){
		this.map = aMap;
	}
	
	public void placeBoundary(Border aBorder, Boundary aBoundary) throws BoundaryAlreadyPlacedException{
		if(boundaryMap.get(aBorder) != null){
			throw new BoundaryAlreadyPlacedException();
		} else{
			boundaryMap.put(aBorder, aBoundary);
		}
	}
	
	public Boundary getBoundary(Border aBorder){
		return boundaryMap.get(aBorder);
	}
	
	public ArrayList<Boundary> getBoundaryList(Tile aTile){
		ArrayList<Boundary> boundList = new ArrayList<Boundary>(4);
		for(int i=0; i<4; i++){
			Boundary bound = boundaryMap.get(aTile.getBorder(i));
			if(bound != null){
				boundList.add(bound);
			}
		}
		return boundList;
	}
	
	private GameMap map;
	private HashMap<Border, Boundary> boundaryMap = new HashMap<Border, Boundary>();
}
