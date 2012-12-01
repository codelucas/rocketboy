package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class HouseHealthBar 
{
	Rectangle healthBar;
	Rectangle fillBar;
	int fillCounter;
	
	public HouseHealthBar()
	{
		fillCounter = 100;
		healthBar = new Rectangle(190, 5, fillCounter, 20);
		fillBar = new Rectangle((int) healthBar.getX(), (int) healthBar.getY(), 
				(int) healthBar.getWidth(), (int) healthBar.getHeight());
	}
	
	public void draw(Graphics2D g)
	{
		if(fillCounter > 100)
		{
			fillCounter = 100;
		}
		
		//Draw the healthBar outline.
		g.setColor(new Color(255, 48, 48, 128));
		
		g.fillRoundRect((int) healthBar.getX(), (int) healthBar.getY(), 
				(int) healthBar.getWidth(), (int) healthBar.getHeight(), 20, 20);
		//Draw the fillBar.
		g.setColor(new Color(0, 255, 0, 230));
		g.fillRoundRect((int) fillBar.getX(), (int) fillBar.getY(), 
				(int) fillCounter, (int) fillBar.getHeight(), 20, 20);
	}
}
