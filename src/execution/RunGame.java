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
	
	//m-m-muh elseifs
	private void evalCmd(String fullCmd){
		String halfCmd = fullCmd.split("\\s")[0];
		
		if(halfCmd.equalsIgnoreCase("help")){
			out.updateText(cmds.toString());
		}else if(halfCmd.equalsIgnoreCase("move")){
			move(fullCmd);
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else if(halfCmd.equalsIgnoreCase("")){
			
		}else{
			out.updateText(fullCmd);
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
	
	private void move(String fullCmd){
		
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
	private static final String[] cmds = {"help", ""}; 
}
