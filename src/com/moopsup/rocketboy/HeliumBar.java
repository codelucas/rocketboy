package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class HeliumBar 
{ 
	Rectangle heliumBar;
	Rectangle fillBar;
	int fillCounter;
	
	public HeliumBar()
	{
		fillCounter = 250;
		heliumBar = new Rectangle(600, 5, fillCounter, 20);
		fillBar = new Rectangle((int) heliumBar.getX(), (int) heliumBar.getY(), 
				(int) fillCounter, (int) heliumBar.getHeight());
	}
	
	public void draw(Graphics2D g)
	{
		if(fillCounter > 250)
		{
			fillCounter = 250;
		}
		
		//Draw the HeliumBar outline.
		g.setColor(new Color(255, 48, 48, 128));
		
		g.fillRoundRect((int) heliumBar.getX(), (int) heliumBar.getY(), 
				(int) heliumBar.getWidth(), (int) heliumBar.getHeight(), 20, 20);
		//Draw the fillBar.

		g.setColor(new Color(0, 250, 154, 220));

		g.fillRoundRect((int) fillBar.getX(), (int) fillBar.getY(), 
				(int) fillCounter, (int) fillBar.getHeight(), 20, 20);
	}
}