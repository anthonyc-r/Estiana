

package server;

import animals.Player;
import map.Map;
import execution.CommandEval;
import server.ServerTextOutput;

import java.net.*;

public class NetworkedPlayer extends Player{
    
    public NetworkedPlayer(String aName, Map aMap, Socket aSocket){
        super(aName, aMap);
        socket = aSocket;
        //TODO: find way of passing a 'networked textOut'
        cmdEval = new CommandEval(aMap, this);
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public CommandEval getCmdEval(){
        return cmdEval;
    }
    
    private Socket socket = null;
    private CommandEval cmdEval = null;
    private ServerTextOutput netOut = null;
}