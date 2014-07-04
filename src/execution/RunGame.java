package execution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import animals.Cow;
import player.Player;
import inout.*;
import interfaces.Placeable;
import map.*;
import static map.Direction.*;

public class RunGame {
	Map estiana = null;
	Player player = null;
	TextOutput out = null;
	Placeable startPos = null;
	String cmd = null;
	Scanner in = null;
	
	public RunGame(String name){
		estiana = new Map(new EstianaData());
		player = new Player(name, estiana);
		out = new TextOutput(estiana);
		startPos = estiana.getTile(0, 0);
		cmd = new String();
		in = new Scanner(System.in);
		estiana.getAnimalPlane().placeAnimal(startPos, player);
		out.updateText("Welcome to estiana, type 'help' to view commands.");
		out.updateView(player.getX(), player.getY());
	}
	
	public static void main(String[] args){
		RunGame runGame = new RunGame("Player");
		runGame.runGameLoop();
	}
	
	private void runGameLoop(){
		while(true){
			out.printFrame();
			cmd = in.nextLine();
			evalCmd(cmd);
		}
	}
	
	private void evalCmd(String aCmd){
		if(aCmd.equalsIgnoreCase("help")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			//Some action
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}else if(aCmd.equalsIgnoreCase("")){
			
		}
	}
	
	private void saveGame(){
		try{
			FileOutputStream outStream = new FileOutputStream("test.ser");
			ObjectOutputStream objOut = new ObjectOutputStream(outStream);
			objOut.writeObject(estiana);
			objOut.close();
			outStream.close();
		} catch(IOException i){
			i.printStackTrace();
		}
	}
	
	private Map loadGame(){
		Map savedMap = null;
		try{
			FileInputStream fStream = new FileInputStream("test.ser");
			ObjectInputStream in = new ObjectInputStream(fStream);
			savedMap = (Map) in.readObject();
			in.close();
		} catch(ClassNotFoundException nf){
			nf.printStackTrace();
		} catch(IOException i){
			i.printStackTrace();
		}
		return savedMap;
	}

}
