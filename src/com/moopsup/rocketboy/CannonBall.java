package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class CannonBall 
{
	Rectangle cannonBall;
	boolean getRidOfMe = false;
	boolean fired = false;
	int destinationX = 0;
	int destinationY = 0;
	int finalX = 0;
	int finalY = 0;
	
	int deltaX = 0;
	int deltaY = 0;
	double speed;
	
	//Designed so I can free my balloons in a set y coordinate.
	boolean oneTime = true;
	int startX = 0;
	int startY = 0;

	BufferedImage CANNONBALL = null;
	
	public CannonBall(Rectangle houseMain, BufferedImage CANNONBALL)
	{
		this.CANNONBALL = CANNONBALL;
		cannonBall = new Rectangle((int) houseMain.getCenterX(), (int) houseMain.getY(), 15, 15);
		speed = 0.3;
	}
	
	public void updateDestination(int x, int y)
	{
		destinationX = x;
		destinationY = y;  
	}
	
	public void draw(Graphics2D g, Rectangle houseMain)
	{ 
	    if(fired)
	    { 
	    	if(oneTime)
	    	{
	    		finalX = destinationX;
		    	finalY = destinationY;
		    	startX = (int) houseMain.getCenterX() - 20;
		    	startY = (int) houseMain.getY();
		    	deltaX = finalX - startX;
		    	deltaY = finalY - startY; 
	    		oneTime = false;
	    	}
	    		
	    	cannonBall.setLocation((int) (startX += speed * deltaX), (int) (startY += speed * deltaY));  
	    } 
	    else
	    { 
	    	cannonBall.setLocation((int) houseMain.getCenterX(), (int) houseMain.getY() + MainPanel.vertVelocity); 
	    }
	    
	    if(cannonBall.getMinY() < 0 || cannonBall.getX() < 0 || cannonBall.getX() > RocketBoy.GAME_WIDTH
	    		|| cannonBall.getMaxY() > RocketBoy.GAME_HEIGHT)
	    { 
	    	getRidOfMe = true;
	    }
		g.drawImage(CANNONBALL, (int) cannonBall.getX(), (int) cannonBall.getY(), 12, 12, null);
	}
}
