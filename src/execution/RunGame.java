package execution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import animals.Cow;
import player.Player;
import inout.*;
import interfaces.Placeable;
import map.*;

import static map.Direction.*;

public class RunGame {
	
	public static void main(String[] args){
		Map estiana = new Map(new EstianaData());
		Player player = new Player("Me", estiana);
		TextOutput out = new TextOutput(estiana);
		Placeable starting = estiana.getTile(0, 0);
		Placeable start2 = estiana.getTile(0, 1);
		estiana.getAnimalPlane().placeAnimal(starting, player);
		estiana.getAnimalPlane().placeAnimal(starting, new Cow());
		out.updateText("Welcome!");
		out.updateView(player.getX(), player.getY());
		out.printFrame();
		//Concurrent mod err if 2 or more
		for(int i=0; i<2; i++){
			player.move(EAST);
		}
		out.updateView(player.getX(), player.getY());
		out.printFrame();
/*		
		try{
			FileOutputStream outStream = new FileOutputStream("test.ser");
			ObjectOutputStream objOut = new ObjectOutputStream(outStream);
			objOut.writeObject(estiana);
			objOut.close();
			outStream.close();
		} catch(IOException i){
			i.printStackTrace();
		}
		*/
		
/*		Map estiana = null;
		try{
			FileInputStream fStream = new FileInputStream("test.ser");
			ObjectInputStream in = new ObjectInputStream(fStream);
			estiana = (Map) in.readObject();
		} catch(ClassNotFoundException nf){
			nf.printStackTrace();
		} catch(IOException i){
			i.printStackTrace();
		}
		
		TextOutput out = new TextOutput(estiana);
		out.updateText("Welcome!");
		out.updateView(0, 0);
		out.printFrame();
		*/
	}
}
