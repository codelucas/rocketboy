package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class BoostBar 
{
	Rectangle boostBar;
	Rectangle fillBar;
	int fillCounter;
	
	public BoostBar()
	{
		fillCounter = 100;
		boostBar = new Rectangle(340, 5, 100, 20);
		fillBar = new Rectangle((int) boostBar.getX(), (int) boostBar.getY(), 
				(int) fillCounter, (int) boostBar.getHeight());
	}
	
	public void draw(Graphics2D g)
	{
		//Draw the BoostBar outline.
		g.setColor(new Color(255, 48, 48, 128));
		
		g.fillRoundRect((int) boostBar.getX(), (int) boostBar.getY(), 
				(int) boostBar.getWidth(), (int) boostBar.getHeight(), 20, 20);
		
		//Draw the fillBar.
		g.setColor(new Color(0, 205, 255, 128));

		g.fillRoundRect((int) fillBar.getX(), (int) fillBar.getY(), 
				(int) fillCounter, (int) fillBar.getHeight(), 20, 20);
	}
}
