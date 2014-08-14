/*
Main Thread
(Get server port
Get server welcome message)-constructor()
(Initiate new map
Add animals to map
Add buildings to map)-initServer()
(Start thread1
Start thread2)
(Repeat forever:
    For each player in connected players:
        If player (read = -1);
            disconnect and remove from players
        If command sent
            Get sent command
            Process command with player's cmdEval
            Send text result of command to player
        Send gameMap to player
    Wait server tick)-Run server
    
    

*Thread1
*Setup connection listener


*/

package server;

import map.Map;
import inout.*;
import animals.*;
import execution.CommandEval;
import server.ServerTextOutput;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class EstianaServer{
    
    public static void main(String[] args){
        EstianaServer srv = new EstianaServer();
    }
    
    public EstianaServer(){
        //Init data structures
        connectedPlayers = new ArrayList<NetworkedPlayer>();
        cmdIn = new Scanner(System.in);
        //Get basic params
        try{
            System.out.println("Set server welcome message: ");
            welcomeMsg = cmdIn.nextLine();
            System.out.println("Set server port: ");
            port = cmdIn.nextInt();
            serverSocket = new ServerSocket(port);
        }catch(InputMismatchException e){
            logger.warning("Port number not an integer.");
            System.exit(-1);
        }catch(IOException e){
            logger.severe("Could not create socket on port "+port+".");
            logger.severe(e.getMessage());
            System.exit(-1);
        }
        //Setup server game 
        initServer();
        //Start thread1 
        acceptCons();
        //run server
        runServer();
    }
    
    public void initServer(){
        //Initiate new map
        gameMap = new Map(new EstianaData());
        //Add animals
        //Add buildings
    }
    
    private void runServer(){
        while(true){
            //For all players in conPlayers
            Iterator conPlayersIter = connectedPlayers.iterator();
            while(conPlayersIter.hasNext()){
                int cmdStatus = NO_COMMAND;
                NetworkedPlayer conPlayer = null;
                Socket conPlayerSocket = null;
                OutputStream conPlayerOutStream = null;
                InputStream conPlayerInStream = null;
                BufferedReader textReader = null;
                PrintWriter textWriter = null;
                
                conPlayer = (NetworkedPlayer)conPlayersIter.next();
                
                //always use protection
                try{
                    conPlayerSocket = (Socket) conPlayer.getSocket();
                    conPlayerInStream = conPlayerSocket.getInputStream();
                    conPlayerOutStream = conPlayerSocket.getOutputStream();
                
                    textReader = new BufferedReader(new InputStreamReader(conPlayerInStream));
                    textWriter = new PrintWriter(conPlayerOutStream, true);
                    
                    //Indicate server has got to client
                    conPlayerOutStream.write(1);
                
                    //Get cmd status byte
                    logger.info("Attempting to read cmdStatus from instream.");
                    cmdStatus = conPlayerInStream.read();
                    logger.info("Read from instream.");
                    //Check if player disconnected
                    if(cmdStatus == -1){
                        //read returned -1, client disconnected
                        logger.info("Found disconnected player, removing...");
                        conPlayerSocket.close();
                        connectedPlayers.remove(conPlayer);
                        logger.info("Number of players connected: "+connectedPlayers.size()+".");
                    }
                    //Check if cmdStatus indicates cmd waiting
                    else if(cmdStatus == COMMAND_WAITING){
                        String command = null;
                        String commandResult = null;
                        logger.info("Command from client waiting, attempting to read...");
                        //Get command
                        command = textReader.readLine();
                        logger.info("Read command: "+command+". Evaluating command...");
                        //process command
                        commandResult = conPlayer.getCmdEval().evalCmd(command);
                        logger.info("Evaluated command and got result, attempting to send result...");
                        //Send frame to client
                        //Thread.sleep(2000);
                        textWriter.println(commandResult);
                    }
                    //Check if cmdStatus indicates no cmd waiting
                    else if(cmdStatus == NO_COMMAND){
                        logger.info("client indicates no cmd waiting");
                    }else{
                        logger.info("cmd status undefined...");
                    }
                    
                }catch(IOException e){
                    logger.severe("IOException thrown communicating with client");
                    System.exit(-1);
                }/*catch(InterruptedException e){
                    logger.warning("Main thread interrupted.");
                    System.exit(-1);
                }*/
            }//END WHILE(HAS NEXT)
            
            //Wait until next server cycle
            try{
                Thread.sleep(SERVER_TICK);
            }catch(InterruptedException e){
                logger.warning("Main thread interrupted.");
                System.exit(-1);
            }
        }//END WHILE(TRUE)
    }
    
    private void acceptCons(){
        //Init accept cons thread
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            
                            logger.info("Waiting for new connection...");
                            Socket newPlrSock = serverSocket.accept();
                            logger.info("Got new connection");
                            //Setup io
                            InputStream plrInStream = newPlrSock.getInputStream();
                            OutputStream plrOutStream = newPlrSock.getOutputStream();
                            BufferedReader in = new BufferedReader(new InputStreamReader(plrInStream));
                            PrintWriter out = new PrintWriter(plrOutStream, true);
                            ObjectOutputStream objOut = new ObjectOutputStream(plrOutStream);
                            //Get player name
                            String plrName = in.readLine();
                            logger.info("Got name "+plrName+".");
                            //Send welcome message
                            out.println(welcomeMsg);
                            out.flush();
                            logger.info("Sent welcome message "+welcomeMsg+".");
                            
                            //Wait to let client catch up 
                            //TODO:Learn some basic networking :^)   
                            Thread.sleep(100);
                            objOut.writeObject(gameMap);
                            objOut.flush();
                            logger.info("Sent game map.");
                            
                            //Add to players
                            connectedPlayers.add(new NetworkedPlayer(plrName, gameMap, newPlrSock));
                            logger.info("Added new player/socket to server list.");
                            logger.info("Number of players connected: "+connectedPlayers.size()+".");
                        }catch(IOException e){
                            logger.severe("Error accepting new connection.");
                            logger.severe(e.getMessage());
                        }catch(InterruptedException e){
                            logger.warning("Interrupted exception thrown while waiting for new connection.");
                        }
                    }
                }
            }).start();
        
    }
    
    
    private Integer port = null;
    private Scanner cmdIn = null;
    private Map gameMap = null;
    private ServerSocket serverSocket = null;
    private ArrayList<NetworkedPlayer> connectedPlayers = null;
    private String welcomeMsg = null;
    
    
    private static int playerCount = 0;
    
    private static final int SERVER_TICK = 1000;
    private static final int COMMAND_WAITING = 10;
    private static final int NO_COMMAND = 11;
    private static final int READY_TO_READ = 12;
    private static final Logger logger = Logger.getLogger(EstianaServer.class.getName());
}