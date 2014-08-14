package gui;

import inout.TextOutput;
import map.Map;
import javax.swing.JTextArea;
import java.util.ArrayList;

public class TextAreaTextOutput extends TextOutput{
    
    public TextAreaTextOutput(Map gameMap, JTextArea aTextArea){
        super(gameMap);
        textArea = aTextArea;
    }
    
    @Override
    public void printFrame(){
        //Get the descriptions
        ArrayList<String> mapDesc = super.getMapDesc();
        //Get the user messages
        ArrayList<String> textBuff = super.getTextBuff();
		//Reset area ready for rewriting
		textArea.setText("");
		//Print out the description formatted so it isn't too wide
		textArea.append("\n"+super.DIVIDE+"\n");
        
		for(String desc : mapDesc){
			textArea.append(super.wrapText(desc, super.TEXT_WIDTH)+"\n");
		}
		
		//Print out the last text message recieved
		textArea.append(super.DIVIDE+"\n");
		textArea.append(textBuff.get(textBuff.size()-1)+"\n");
		textArea.append(super.DIVIDE);
    }
    
    private JTextArea textArea = null;
}