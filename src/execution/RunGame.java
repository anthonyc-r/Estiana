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

import exceptions.EndOfMapException;
import animals.*;
import inout.*;
import interfaces.Placeable;
import map.*;

public class RunGame {
	
	public RunGame(String name){
		estiana = new Map(new EstianaData());
		out = new TextOutput(estiana);
		player = new Player(name, estiana, out);
		Cow cow = new Cow(estiana, out);
		startPos = estiana.getTile(0, 0);
		cmd = new String();
		in = new Scanner(System.in);
		estiana.getAnimalPlane().placeAnimal(startPos, player);
		estiana.getAnimalPlane().placeAnimal(startPos, cow);
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
	
	//m-m-muh elseifs
	private void evalCmd(String fullCmd){
		String[] splitCmd = fullCmd.split("\\s");
		
		if(splitCmd[0].equalsIgnoreCase("help")){
			printHelp();
		}else if(splitCmd[0].equalsIgnoreCase("move")){
			move(splitCmd[1]);
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else if(splitCmd[0].equalsIgnoreCase("")){
			
		}else{
			out.updateText(fullCmd);
		}
	}
	
	private void printHelp(){
		StringBuilder bldr = new StringBuilder();
		for(String cmd : cmds){
			bldr.append(cmd+", ");
		}
		out.updateText(bldr.toString());
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
	
	private void move(String directionStr){
		try{
			player.move(directionStr);
		}catch(IllegalArgumentException e){
			out.updateText("Invalid direction!");
		}catch(EndOfMapException e){
			e.getMessage();
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

	
	private Map estiana = null;
	private Player player = null;
	private TextOutput out = null;
	private Placeable startPos = null;
	private String cmd = null;
	private Scanner in = null;
	private static final String[] cmds = {"help", "move {east, north, south, west}"}; 
}
