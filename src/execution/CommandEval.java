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

import boundaries.Boundary;
import boundaries.Fence;
import boundaries.Gate;
import boundaries.Wall;
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
import execution.gui.*;

public class CommandEval {
	
	public CommandEval(Map aGameMap, Player aPlayer){
		estiana = aGameMap;
		player = aPlayer;
	}
	
	public static void main(String[] args){
	}

	
	//m-m-muh elseifs
	public String evalCmd(String fullCmd){
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
			
		}
        
        return textResult;
	}
	
	private void printHelp(){
		StringBuilder bldr = new StringBuilder();
		bldr.append("Commands are: ");
		for(String cmd : cmds){
			bldr.append(cmd+", ");
		}
		textResult = bldr.toString();
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
			textResult = "You move "+directionStr;
		}catch(IllegalArgumentException e){
			textResult = "Invalid direction!";
		}catch(EndOfMapException e){
			textResult = e.getMessage();
		}catch(TerrainTooSteepException e){
			textResult = e.getMessage();
		}catch(ObstructedPathException e){
			textResult = e.getMessage();
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
				textResult = bound.getDesc();
			}
		}
		//Items
		for(Item item : items){
			if(item.getName().equalsIgnoreCase(someEntity)){
				textResult = item.getDesc();
			}
		}
		//Animals
		for(Animal animal : animals){
			if(animal.getName().equalsIgnoreCase(someEntity)){
				textResult = animal.getDesc();
			}
		}
		
	}

	
	private Map estiana = null;
	private Player player = null;
	private String textResult = null;
    
	private static final String[] cmds = {"help", "move {east, north, south, west}", "examine {name of thing}"}; 
}
