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
import java.util.logging.*;

import boundaries.Boundary;
import boundaries.Fence;
import boundaries.Gate;
import boundaries.Wall;
import exceptions.BoundaryAlreadyPlacedException;
import exceptions.EndOfMapException;
import exceptions.ObstructedPathException;
import exceptions.TerrainTooSteepException;
import exceptions.InvalidDirectionException;
import exceptions.ItemNotFoundException;
import animals.*;
import inout.*;
import interfaces.Item;
import interfaces.Surface;
import items.Note;
import items.Dirt;
import items.ItemType;
import map.*;
import execution.gui.*;

public class CommandEval {
	
	public CommandEval(GameMap aGameMap, Player aPlayer){
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
		Tile playerTile = estiana.getTile(player.getX(), player.getY());
		if(ins.equalsIgnoreCase("help")){
			return printHelp();
		}else if(ins.equalsIgnoreCase("move")){
			return move(target);
		}else if(ins.equalsIgnoreCase("examine")){
			return examine(playerTile, target);
		}else if(ins.equalsIgnoreCase("dig")){
			return digCorner(playerTile, target);
		}else if(ins.equalsIgnoreCase("raise")){
			return raiseCorner(playerTile, target);
		}else if(ins.equalsIgnoreCase("drop")){
			return dropItem(target);
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}else if(ins.equalsIgnoreCase("")){
			
		}
        
        return "Invalid command.";
	}
	
	private String printHelp(){
		StringBuilder bldr = new StringBuilder();
		bldr.append("Commands are: ");
		for(String cmd : cmds){
			bldr.append(cmd+", ");
		}
		return bldr.toString();
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
	
	private String move(String directionStr){
		try{
			player.move(directionStr);
			return "You move "+directionStr;
		}catch(IllegalArgumentException e){
			return "Invalid direction!";
		}catch(EndOfMapException e){
			return e.getMessage();
		}catch(TerrainTooSteepException e){
			return e.getMessage();
		}catch(ObstructedPathException e){
			return e.getMessage();
		}
	}
	
	private GameMap loadGame(){
		GameMap savedMap = null;
		try{
			FileInputStream fStream = new FileInputStream("test.ser");
			ObjectInputStream in = new ObjectInputStream(fStream);
			savedMap = (GameMap) in.readObject();
			in.close();
		} catch(ClassNotFoundException nf){
			nf.printStackTrace();
		} catch(IOException i){
			i.printStackTrace();
		}
		return savedMap;
	}
	
	private String examine(Tile aTile, String someEntity){
		ArrayList<Item> items = estiana.getItemPlane().getItems(aTile);
		ArrayList<Animal> animals = estiana.getAnimalPlane().getAnimals(aTile);
		ArrayList<Boundary> bounds = estiana.getBoundaryPlane().getBoundaryList(aTile);
		//Buildings
		for(Boundary bound : bounds){
			if(bound.getName().equalsIgnoreCase(someEntity)){
				return bound.getDesc();
			}
		}
		//Items
		for(Item item : items){
			if(item.getName().equalsIgnoreCase(someEntity)){
				return item.getDesc();
			}
		}
		//Animals
		for(Animal animal : animals){
			if(animal.getName().equalsIgnoreCase(someEntity)){
				return animal.getDesc();
			}
		}
		
        return "Cannot see "+someEntity+".";
	}
    
    private String digCorner(Tile aTile, String directionStr){
        try{
            Direction dir = Direction.valueOf(directionStr.toUpperCase());
            Corner toDig = aTile.getCorner(dir);
            GroundType type = toDig.getGroundType();
            if(type == GroundType.ROCK || type == GroundType.WATER){
                return "You cannot dig here.";
            }else{
                //Get an item of ground type and place in player inventory
                toDig.addHeight(-1);
                player.addToInventory(new Dirt());
                if(type == GroundType.GRASS){
                    toDig.setGroundType(GroundType.DIRT);
                }
                return "You dig up some "+type.toString()+".";
            }
        }catch(InvalidDirectionException e){
            return e.getMessage();
        }catch(IllegalArgumentException e){
            return "Invalid direction, "+directionStr+"!";
        }
    }
    
    private String raiseCorner(Tile aTile, String directionStr){
        try{
            Direction dir = Direction.valueOf(directionStr.toUpperCase());
            Corner toRaise = aTile.getCorner(dir);
            Item toDrop = player.findInInventory(ItemType.DIRT);
            player.deleteFromInventory(toDrop);
            toRaise.addHeight(1);
            toRaise.setGroundType(GroundType.DIRT);
            return "You drop some dirt on the "+dir.toString()+" corner.";
            
        }catch(InvalidDirectionException e){
            return e.getMessage();
        }catch(IllegalArgumentException e){
            return "Invalid direction, "+directionStr+"!";
        }catch(ItemNotFoundException e){
            return "Cannot find item in inventory!";
        }
    }
    
    private String dropItem(String nameOfItem){
        try{
            ItemType typeToDrop = ItemType.valueOf(nameOfItem.toUpperCase());
            Item toDrop = player.findInInventory(typeToDrop);
            player.dropItem(toDrop);
            return "You drop the "+nameOfItem+".";
        }catch(IllegalArgumentException e){
            return "Item, "+nameOfItem+", not found.";
        }catch(ItemNotFoundException e){
            return "Item, "+nameOfItem+", not found.";
        }
    }
    
    private String pickupItem(String nameOfItem){
        /*try{
             ItemType typeToDrop = ItemType.valueOf(nameOfItem.toUpperCase());
        }catch(IllegalArgumentException e){
            return "Item, "+nameOfItem+", not found.";            
        }catch(ItemNotFoundException e){
            return "Item, "+nameOfItem+", not found.";  
        }*/
            return "not implemented";
    }

	
	private GameMap estiana = null;
	private Player player = null;
    
	private static final String[] cmds = {"help", "move {east, north, south, west}", "examine {name of thing}"}; 
}
