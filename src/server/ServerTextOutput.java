package server;

import inout.TextOutput;
import map.Map;

import java.net.*;

public class ServerTextOutput extends TextOutput{
    
    public ServerTextOutput(Map gameMap, Socket aSocket){
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