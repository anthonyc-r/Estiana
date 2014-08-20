package gui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Rectangle;

import map.GameMap;
import animals.Player;
import map.GroundType;

public class GraphicsPanel extends JPanel{
	
	public GraphicsPanel(){
		//Get graphics context
        width = super.getWidth();
        height = super.getHeight();
	}
    
    public void setStuff(GameMap aGameMap, Player aPlayer){
        gameMap = aGameMap;
        player = aPlayer;   
    }
	
	public void paint(Graphics g){
        width = super.getWidth();
        height = super.getHeight();
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0f,0f,Color.blue,0f,30f,Color.green);
		g2.setPaint(gp);
		//g2.fill(new Rectangle(2, 2, 50, 50));
        if(gameMap != null){

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                GroundType gType = gameMap.getTile(i, j).getGroundType();
                
                switch(gType){
                    case GRASS:
                        g2.setPaint(Color.GREEN);
                        break;
                    case DIRT:
                        g2.setPaint(Color.LIGHT_GRAY);
                        break;
                    default:
                        g2.setPaint(Color.WHITE);
                }
                if(player.getX() == i && player.getY() == j){
                    g2.setPaint(Color.RED);
                }
                g2.fill(new Rectangle(i*32, j*32, (i*32)+32, (j*32)+32));
            }
        }
        }
	}
    
    private int width;
    private int height;
    
    private GameMap gameMap = null;
    private Player player = null;
}