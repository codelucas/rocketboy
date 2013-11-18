package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Spike extends EnemyObject
{
	Random RND = new Random();
	Rectangle spike;
	
	//Trigonometric Values
	int destinationX = 0;
	int destinationY = 0;
	int startX = RND.nextInt(RocketBoy.GAME_WIDTH);
	int startY = 0;
	
	int deltaX = 0;
	int deltaY = 0;
	double speed = 0.03;
	
	BufferedImage SPIKE = null;
	
	public Spike(BufferedImage SPIKE)
	{
		this.SPIKE = SPIKE;
		RND = new Random(); 
		spike = new Rectangle(startX, 0, 20, 20);
		hitByFlyBalloon = false;
		isImpeded = false;
	}
	//Main draw method.
	public void draw(Graphics2D g, Graphics2D secondAffine)
	{
		if(isImpeded)
		{
			speed = 0;
			isImpeded = false;
		}
		else if(hitByFlyBalloon)
		{
			speed = 0.01;
			hitByFlyBalloon = false;
		}
		else
			speed = 0.03;
		
		destinationX = (int) MainPanel.gameChar.theCharacter.getX() + 20;
		destinationY = (int) MainPanel.gameChar.theCharacter.getY() + 10;
		
    	deltaX = destinationX - startX;
    	deltaY = destinationY - startY; 
	
		spike.setLocation((int) (startX += speed * deltaX), (int) (startY += speed * deltaY) + 5);
		
		if(spike.getY() > RocketBoy.GAME_HEIGHT || spike.getY() <= 0)
	    {
	    	removeMe = true;
	    }
	    
		g.drawImage(SPIKE, (int) spike.getX() - 30, (int) spike.getY() - 25, 75, 75, null);
	}
	@Override
	public boolean collides(Rectangle characterObject) 
	{
		if(spike.intersects(characterObject))
		{
			return true;
		}
		return false;
	}
}
