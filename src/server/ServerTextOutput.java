package server;

import inout.TextOutput;
import map.GameMap;

import java.net.*;

public class ServerTextOutput extends TextOutput{
    
    public ServerTextOutput(GameMap gameMap, Socket aSocket){
        super(gameMap);
        outputSocket = aSocket;
    }
    
    /**
     *Sends a string of text with new line seporated by \n over the socket
     */
    @Override
    public void printFrame(){
        
    }
    
    Socket outputSocket = null;
}