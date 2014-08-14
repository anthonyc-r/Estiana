/*
init gui

*/

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
import java.util.*;
import java.util.logging.*;

import gui.GraphicsPanel;

import map.Map;
import animals.Player;
import server.NetworkedPlayer;
import execution.CommandEval;
import inout.TextOutput;
import gui.TextAreaTextOutput;
import inout.EstianaData;
import interfaces.Surface;
import map.Tile;

public class MainFrameMP extends JFrame{
	
	public MainFrameMP(){
		setTitle(MAIN_TITLE);
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		setVisible(true);
		setResizable(false);
        logger.info("Init frame running...");
		initFrame();
        logger.info("Init game running...");
		initGame();
        logger.info("Running main game loop...");
        runGame();
	}
	
	
	public static void main(String[] args){
		MainFrameMP frame = new MainFrameMP();
		
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
				lastCommand = textInFrame.getText();
                //Set newCommand ready for processing
                newCommand = true;	
                //Reset area
                textInFrame.setText("");
			}
		});
		
	}
    
    private void initGame(){
        //Set server info
        Scanner inpt = new Scanner(System.in);
        String host = "127.0.0.1";
        Integer port = 6666;
        String plrName = inpt.nextLine();
        
        try {
            gameSocket = new Socket(host, port);
            socketInStream = gameSocket.getInputStream();
            socketOutStream = gameSocket.getOutputStream();
            
            netIn = new BufferedReader(new InputStreamReader(socketInStream));
            netOut = new PrintWriter(socketOutStream, true);
            
            objectIn = new ObjectInputStream(socketInStream);
            //Send player name
            netOut.println(plrName);
            logger.info("Sent player name: "+plrName+".");
            
            //Get welcome message
            srvWelcome = netIn.readLine();
            logger.info("Got welcome message.");
            
            //Get game map
            gameMap = (map.Map) objectIn.readObject();
            logger.info("Recieved map data");
            
        }catch(IOException e){
            logger.warning("IOEX thrown");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void runGame(){
        while(true){    
            try{
                //IF COMMAND INPUT
                //Wait until server has got to this player
                logger.info("Waiting until server sends ready to recieve message...");
                socketInStream.read();
                logger.info("Server ready.");
                if(newCommand){
                    logger.info("New command entered.");
                    //Send cmd indicator byte
                    socketOutStream.write(COMMAND_WAITING);
                    logger.info("Sent command waiting signal.");
                    //Send command
                    netOut.println(lastCommand);
                    logger.info("Sent command entered.");
                    //Reset command flag
                    newCommand = false;
                    //Get frame from server
                    logger.info("Attempting to read new description from server...");
                    String frame = netIn.readLine();
                    logger.info("Read new description from server.");
                    //print local frame
                    textOutFrame.setText("");
                    textOutFrame.append(frame);
                }else{
                    //send nocmd indicator byte            
                    socketOutStream.write(NO_COMMAND);
                }
            }catch(IOException e){
                logger.severe("IOException while sending command to server.");
                System.exit(-1);
            }
        
            //Sleep until next cycle
            try{
                Thread.sleep(CLIENT_TICK);
            }catch(InterruptedException e){
                logger.warning("Main thread wait interrupted");
            }
        }
    }
	
	private void playerUpdate(){
		output.updateView(player.getX(), player.getY());
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
	private Map gameMap = null;
	private Player player = null;
	private CommandEval cmdEval = null;
	private TextOutput output = null;
    private String srvWelcome = null;
    private String lastCommand = null;
    
    private boolean newCommand = false;
    
    //NETWORKING STUFF
    Socket gameSocket = null;
    InputStream socketInStream = null;
    OutputStream socketOutStream = null;
    PrintWriter netOut = null;
    BufferedReader netIn = null;
    ObjectInputStream objectIn = null;
    
    private static final int CLIENT_TICK = 1500;
    private static final int COMMAND_WAITING = 10;
    private static final int NO_COMMAND = 11;
    private static final int READY_TO_READ = 12;
    
    private static final Logger logger = Logger.getLogger(MainFrameMP.class.getName());
}