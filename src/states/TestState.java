package states;

import interfaces.State;
import map.*;


public class TestState implements State{
	
	public TestState(GameMap gameMap){
		aPlane = gameMap.getAnimalPlane();
		bPlane = gameMap.getBoundaryPlane();
		iPlane = gameMap.getItemPlane();
	}
	
	public void setState(){
		
	}
	
	public void setState(State prevState){
		
	}
	
	AnimalPlane aPlane = null;
	BoundaryPlane bPlane = null;
	ItemPlane iPlane = null;
	
}