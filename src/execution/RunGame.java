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

import boundarys.Boundary;
import boundarys.Wall;
import exceptions.BoundaryAlreadyPlacedException;
import exceptions.EndOfMapException;
import exceptions.ObstructedPathException;
import exceptions.TerrainTooSteepException;
import animals.*;
import inout.*;
import interfaces.Item;
import interfaces.Surface;
import items.Note;
import map.*;

public class RunGame {
	
	public RunGame(String name){
		estiana = new Map(new EstianaData());
		out = new TextOutput(estiana);
		player = new Player(name, estiana, out);
		Cow cow = new Cow(estiana, out);
		startPos = estiana.getTile(1, 1);
		//item test
		Note note = new Note("crumpled", "The exit lies to the south");
		//Bound test
		Boundary wall = new Wall();
		Boundary wall2 = new Wall();
		Boundary wall3 = new Wall();
		try{
			estiana.getBoundaryPlane().placeBoundary(startPos.getBorder(0), wall);
			estiana.getBoundaryPlane().placeBoundary(startPos.getBorder(3), wall2);
			estiana.getBoundaryPlane().placeBoundary(startPos.getBorder(2), wall3);
		}catch(BoundaryAlreadyPlacedException e){
			out.updateText(e.getMessage());
			//won't happen
		}
		
		cmd = new String();
		in = new Scanner(System.in);
		estiana.getAnimalPlane().placeAnimal(startPos, player);
		estiana.getAnimalPlane().placeAnimal(startPos, cow);
		estiana.getItemPlane().placeItem(startPos, note);
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
		//TODO:Validate cmd
		//Avoid array bounds exception for single word cmd
		fullCmd = fullCmd.concat(" ");
		//Break into 1'st part and rest
		int indx = fullCmd.indexOf(" ");
		String ins = fullCmd.substring(0, (indx)).trim();
		String target = fullCmd.substring(indx+1).trim();		
		
		if(ins.equalsIgnoreCase("help")){
			printHelp();
		}else if(ins.equalsIgnoreCase("move")){
			move(target);
		}else if(ins.equalsIgnoreCase("examine")){
			examine(estiana.getTile(player.getX(), player.getY()), target);
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else{
			out.updateText(fullCmd);
		}
	}
	
	private void printHelp(){
		StringBuilder bldr = new StringBuilder();
		bldr.append("Commands are: ");
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
			out.updateText("You move "+directionStr);
		}catch(IllegalArgumentException e){
			out.updateText("Invalid direction!");
		}catch(EndOfMapException e){
			out.updateText(e.getMessage());
		}catch(TerrainTooSteepException e){
			out.updateText(e.getMessage());
		}catch(ObstructedPathException e){
			out.updateText(e.getMessage());
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
	
	private void examine(Tile aTile, String someEntity){
		ArrayList<Item> items = estiana.getItemPlane().getItems(aTile);
		ArrayList<Animal> animals = estiana.getAnimalPlane().getAnimals(aTile);
		ArrayList<Boundary> bounds = estiana.getBoundaryPlane().getBoundaryList(aTile);
		//Buildings
		for(Boundary bound : bounds){
			if(bound.getName().equalsIgnoreCase(someEntity)){
				out.updateText(bound.getDesc());
			}
		}
		//Items
		for(Item item : items){
			if(item.getName().equalsIgnoreCase(someEntity)){
				out.updateText(item.getDesc());
			}
		}
		//Animals
		for(Animal animal : animals){
			if(animal.getName().equalsIgnoreCase(someEntity)){
				out.updateText(animal.getDesc());
			}
		}
		
	}

	
	private Map estiana = null;
	private Player player = null;
	private TextOutput out = null;
	private Tile startPos = null;
	private String cmd = null;
	private Scanner in = null;
	private static final String[] cmds = {"help", "move {east, north, south, west}", "examine {name of thing}"}; 
}
