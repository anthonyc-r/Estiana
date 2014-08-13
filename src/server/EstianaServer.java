package server;

import map.Map;
import inout.*;
import animals.*;
import execution.CmdEval;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class EstianaServer{
    
    public static void main(String[] args){
        EstianaServer srv = new EstianaServer();
    }
    
    public EstianaServer(){
        cmdIn = new Scanner(System.in);
        gameMap = new Map(new EstianaData());
        connectedPlayers = new ArrayList<NetworkedPlayers>();
        
        initServer();
        acceptCons();
        pruneCons();
        runServer();
    }
    
    public void initServer(){
        
        try{
            System.out.println("Set server welcome message: ");
            welcomeMsg = cmdIn.nextLine();
            System.out.println("Set server port: ");
            port = cmdIn.nextInt();
            serverSocket = new ServerSocket(port);
        }catch(IOException e){
            logger.severe("Could not create socket on port "+port+".");
            logger.severe(e.getMessage());
            initServer();
        }catch(InputMismatchException e){
            logger.warning("Invalid port number.");
            initServer();
        }
    }
    
    private void runServer(){
        while(true){
            //Close disconnected cons
            //Check for new cons
            //For each con
            ////Check for new cmd
            ////If new cmd: Run cmds
            ////Send output of cmd to player
            //For each con
            ////Send new map data to con
        
            //Update animals
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
                logger.warning("Main thread interrupted.");
            }
        }
    }
    
    private void pruneCons(){
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Iterator mapIter = players.entrySet().iterator();
                            while(mapIter.hasNext()){
                                java.util.Map.Entry conPlayer = (java.util.Map.Entry)mapIter.next();
                                Socket exPlrSocket = (Socket) conPlayer.getKey();
                                InputStream exPlrStream = exPlrSocket.getInputStream();
                                if(exPlrStream.read() == -1){
                                    logger.info("Found disconnected player, removing...");
                                    exPlrSocket.close();
                                    players.remove(exPlrSocket);
                                    logger.info("Number of players connected: "+connectedPlayers.size()+".");
                                }
                            }
                            Thread.sleep(1000);
                        }catch(IOException e){
                            logger.warning("IOEX thrown");
                        }catch(InterruptedException e){
                            logger.warning("Connection pruning thread interrupted.");
                        }
                    }
                }
            }).start();
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
    private ArrayList<NetworkedPlayers> connectedPlayers = null;
    private String welcomeMsg = null;
    
    
    private static int playerCount = 0;
    
    private static final Logger logger = Logger.getLogger(EstianaServer.class.getName());
}