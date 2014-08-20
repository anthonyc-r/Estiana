package gui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Rectangle;

import animals.Player;
import map.*;

public class GraphicsPanel extends JPanel{
	
	public GraphicsPanel(){
	}
    
    public void setValues(GameMap gMap, Player plr){
        gameMap = gMap;
        player = plr;
        iPlane = gMap.getItemPlane();
    }
	
	public void paint(Graphics g){
        super.paint(g);
        
        
		Graphics2D g2 = (Graphics2D) g;
        
        if(gameMap != null){
            iPlane = gameMap.getItemPlane();
            
            int width = super.getWidth();       //780   24x :: 11 - p - 12
            int height = super.getHeight();     //386   13x :: 6 - p - 6
        
            int playerX = player.getX();
            int playerY = player.getY();
            int mapHeight = gameMap.getMapHeight();
            int mapWidth = gameMap.getMapWidth();
            //Set maximum frame bounds
            int frameOriginX = playerX - 11;
            int frameOriginY = playerY - 6;
            int frameEndX = playerX + 26;//13;
            int frameEndY = playerY + 14;//7;
            //Verify maximum bounds are within map limits
            if(frameOriginX<0){
                frameOriginX = 0;
            }
            if(frameOriginY<0){
                frameOriginY = 0;
            }
            if(frameEndX>mapWidth){
                frameEndX = mapWidth;
            }
            if(frameEndY>mapHeight){
                frameEndY = mapHeight;
            }
            

            for(int i=0; i<24; i++){
                for(int j=0; j<13; j++){
                    //If within frame
                    if(frameOriginX+i<frameEndX && frameOriginY+j<frameEndY){
                        //Get the tile
                        Tile toDraw = gameMap.getTile(frameOriginX+i, frameOriginY+j);
                    
                        GroundType gType = toDraw.getGroundType();
                    
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
                        g2.fill(new Rectangle(i*32, j*32, (i*32)+32, (j*32)+32));
                        //Draw items on tile
                        if(iPlane.getItems(toDraw).size() != 0){
                            g2.setPaint(Color.MAGENTA);
                            g2.fill(new Rectangle(i*32, j*32, (i*32)+32, (j*32)+32));
                        }
                        //Draw animals on tile
                        if(playerX == frameOriginX+i && playerY == frameOriginY+j){
                            g2.setPaint(Color.RED);
                            g2.fill(new Rectangle(i*32, j*32, (i*32)+32, (j*32)+32));
                        }
                        //Draw boundaries on tile....
                        
                    }else{
                        g2.setPaint(Color.BLACK);
                        g2.fill(new Rectangle(i*32, j*32, (i*32)+32, (j*32)+32));
                    }
                }
            }
        }
        
	}
    
    private GameMap gameMap = null;
    private Player player = null;
    private ItemPlane iPlane = null;
}