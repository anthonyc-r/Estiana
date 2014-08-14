package gui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Rectangle;

public class GraphicsPanel extends JPanel{
	
	public GraphicsPanel(){
		//Get graphics context
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0f,0f,Color.blue,0f,30f,Color.green);
		g2.setPaint(gp);
		g2.fill(new Rectangle(2, 2, 50, 50));
	}
}