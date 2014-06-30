package execution;

import animals.Cow;
import player.Player;
import inout.*;
import interfaces.Placeable;
import map.*;

public class RunGame {
	
	public static void main(String[] args){
		Map estiana = new Map(new EstianaData());
		Player player = new Player("Me");
		TextOutput out = new TextOutput(estiana);
		Placeable starting = estiana.getTile(0, 0);
		Placeable start2 = estiana.getTile(0, 1);
		estiana.getAnimalPlane().placeAnimal(starting, player);
		estiana.getAnimalPlane().placeAnimal(starting, new Cow());
		out.updateText("Welcome!");
		out.updateView(0, 0);
		out.printFrame();
	}
}
