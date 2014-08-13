package server;

import animals.Player;
import map.Map;
import execution.CmdEval;

import java.net.*;

public class NetworkedPlayer extends Player{
    
    public NetworkedPlayer(String aName, Map aMap, Socket aSocket){
        super(aName, aMap);
        socket = aSocket;
        //TODO: find way of passing a 'networked textOut'
        cmdEval = new CmdEval(aMap, null, this);
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public CmdEval getCmdEval(){
        return cmdEval;
    }
    
    private Socket socket = null;
    private CmdEval cmdEval = null;
}