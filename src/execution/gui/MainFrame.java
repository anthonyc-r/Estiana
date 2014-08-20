package execution.gui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.*;
import java.io.*;

import gui.GraphicsPanel;

import map.GameMap;
import animals.Player;
import execution.CommandEval;
import inout.TextOutput;
import gui.TextAreaTextOutput;
import inout.EstianaData;
import interfaces.Surface;
import map.Tile;

public class MainFrame extends JFrame{
	
	public MainFrame(){
		setTitle(MAIN_TITLE);
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		setVisible(true);
		setResizable(false);
		initFrame();
		initSPGame();
	}
	
	
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		
	}
	
	public JTextArea getTextArea(){
		return this.textOutFrame;
	}
	
	
	private void initFrame(){
		textInFrame = new JTextField();
		textOutFrame = new JTextArea();
		tOutScrollFrame = new JScrollPane();
		gfxPanel = new GraphicsPanel();
		
		textOutFrame.setEditable(false);
		textInFrame.setEditable(true);
		//Sets the scroll pane to display the contents of the textArea
		tOutScrollFrame.setViewportView(textOutFrame);
		//Ensure it auto scrolls to the bottom
		DefaultCaret caret = (DefaultCaret) textOutFrame.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		contentPane.setLayout(null);
		
		contentPane.add(gfxPanel);
		contentPane.add(tOutScrollFrame);
		//contentPane.add(textOutFrame);
		contentPane.add(textInFrame);
		
		//TODO: Fix hardcoding in dimensions
		////The original problem may have been caused by not displaying the window before setBounds is called
		////orig&dims may be unneeded.
		orig = new Point(getInsets().left, getInsets().top);
		dims = new Dimension(MAIN_WIDTH-(getInsets().left+getInsets().right),
									MAIN_HEIGHT-(getInsets().top+getInsets().bottom));
		
		//use orig and dims to account for MAIN_WIDTH&HEIGHT being the w/h of the entire frame
		//Including OS drawn borders
		tOutScrollFrame.setBounds(orig.x+10, dims.height-180, 
								dims.width-20, 148);
		//textOutFrame.setBorder(BorderFactory.createLineBorder(Color.black));
		//slightly wider than outFrame due to it appearing narrower when set similarly
		textInFrame.setBounds(orig.x+7, dims.height-31,
				 				dims.width-14, 23);
		gfxPanel.setBounds(orig.x+10, 10,
							dims.width-20, dims.height-192);
		gfxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Set up action event listener for input
		textInFrame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String stringIn = textInFrame.getText();
				String cmdResult = cmdEval.evalCmd(stringIn);
                output.updateText(cmdResult);
				playerUpdate();
				textInFrame.setText("");
				
			}
		});
		
	}
	
	private void initSPGame(){
		gameMap = new GameMap(new EstianaData());
		output = new TextAreaTextOutput(gameMap, textOutFrame);
		player = new Player("player", gameMap);
		cmdEval = new CommandEval(gameMap, player);
        
        gfxPanel.setStuff(gameMap, player);
		//place player
		Surface startTile = gameMap.getTile(1, 1);
		gameMap.getAnimalPlane().placeAnimal(startTile, player);
		//Print intial frame
		output.updateView(1, 1);
		output.updateText(WELCOME_MSG);
		output.printFrame();
	}
	
	private void playerUpdate(){
		output.updateView(player.getX(), player.getY());
        gfxPanel.repaint();
		output.printFrame();
	}
	
	//GUI STUFF
	private Dimension dims = null;
	private Point orig = null;
	private Container contentPane = null;
	private JTextField textInFrame = null;
	private JTextArea textOutFrame = null;
	private JScrollPane tOutScrollFrame = null;
	private GraphicsPanel gfxPanel =  null;
	
	public static final String MAIN_TITLE = "Estiana";
	public static final int MAIN_WIDTH = 800;
	public static final int MAIN_HEIGHT = 600;
	
	//GAME LOGIC STUFF
	private GameMap gameMap = null;
	private Player player = null;
	private CommandEval cmdEval = null;
	private TextOutput output = null;
	
	public static final String WELCOME_MSG = "Welcome to Estiana, type 'help' to view commands.";
}