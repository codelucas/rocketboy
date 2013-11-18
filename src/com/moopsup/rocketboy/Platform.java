package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

//The most general basic platform, does nothing but support the character.
public class Platform extends GeneralPlatform
{
	static final int PLATFORM_WIDTH = 140;
	static final int PLATFORM_LENGTH = 10;
	
	BufferedImage PLATFORM_ONE = null;
	BufferedImage PLATFORM_SPIKE = null;
	BufferedImage GASPUMP_FILLED = null;
	
	boolean hasPump;
	boolean hasSpike;
	boolean startPlatform;
	Random RND = new Random();
	int yCoord;
	
	//Constructor for "static" platforms or non-game ones.
	public Platform(BufferedImage PLATFORM_ONE, BufferedImage PLATFORM_SPIKE)
	{
		this.PLATFORM_ONE = PLATFORM_ONE;
		this.PLATFORM_SPIKE = PLATFORM_SPIKE;
		
		startPlatform = true;
		hasSpike = true;
		removeMe = false;
		hasPump = false;
		yCoord = -5;
		platform = new Rectangle(RND.nextInt((int) RocketBoy.GAME_WIDTH - PLATFORM_WIDTH),
				yCoord, PLATFORM_WIDTH, PLATFORM_LENGTH);	
		underSpike = new Rectangle((int) platform.getX() + 6, (int) platform.getMaxY() - 1
				,(int) platform.getWidth() - 10, 30);
	}
	
	public Platform(BufferedImage PLATFORM_ONE, BufferedImage PLATFORM_SPIKE, BufferedImage GASPUMP_FILLED)
	{
		this.PLATFORM_ONE = PLATFORM_ONE;
		this.PLATFORM_SPIKE = PLATFORM_SPIKE;
		this.GASPUMP_FILLED = GASPUMP_FILLED;
		
		if(Math.random() < 0.3)
		{
			hasPump = true;
		}
		else
		{
			hasPump = false;
		}
		
		if(Math.random() < 0.2)
		{
			hasSpike = true;
		}
		else
		{
			hasSpike = false;
		}
		
		removeMe = false;
		startPlatform = false;
		yCoord = -5;
		platform = new Rectangle(RND.nextInt((int) RocketBoy.GAME_WIDTH - PLATFORM_WIDTH),
				yCoord, PLATFORM_WIDTH, PLATFORM_LENGTH);	
		if(hasSpike)
		{
			underSpike = new Rectangle((int) platform.getX() + 6, (int) platform.getMaxY() - 1,
					 (int) platform.getWidth() - 10, 30);
		}
		else
		{
			underSpike = new Rectangle(0, 0, 0, 0);
		}
		
		if(hasPump)
		{
			gasStation = new Rectangle((int) platform.getX() + 20, (int) platform.getMinY() - 33, 20, 30);
		}
		else
		{
			gasStation = new Rectangle(0, 0, 0, 0);
		}
	}
	
	@Override
	public boolean landsOn(Rectangle theCharacterObject) 
	{
		//Code to make sure we land "ON" the platform, instead of just intersecting it.
		if(theCharacterObject.getMinX() >= platform.getMinX() - 10 && theCharacterObject.getMaxX() 
				<= platform.getMaxX() + 15 && theCharacterObject.getMaxY()
				< platform.getMaxY() && theCharacterObject.getMaxY() >= platform.getMinY() - 5)
		{  
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		//if out of bounds, change new cloud
		yCoord -= MainPanel.vertVelocity;
		
		if(!startPlatform)
		{
			platform.setLocation((int) platform.getX(), yCoord);
			underSpike.setLocation((int) underSpike.getX(), (int) platform.getMaxY() - 1);
		}
		
		if(hasPump)
		{
			gasStation.setLocation((int) gasStation.getX(), (int) platform.getMinY() - 33);
		}
		
		if(platform.getY() > RocketBoy.GAME_HEIGHT)
		{
			removeMe = true; 
		}
		//15 and 30 are side equalizer variables.
		//g.fill(platform);
		g.drawImage(PLATFORM_ONE, (int) platform.getX() - 15, (int) platform.getY() - 10,
				(int) platform.getWidth() + 30, (int) platform.getHeight() + 15, null); 
		
		if(hasSpike)
		{
			g.drawImage(PLATFORM_SPIKE, (int) underSpike.getX(), (int) underSpike.getY(), (int) 
					underSpike.getWidth(), (int) underSpike.getHeight(), null);
		}
		
		//g.fill(gasStation);
		if(hasPump)
		{
			g.drawImage(GASPUMP_FILLED, (int) gasStation.getX(), (int) gasStation.getY(), 
					(int) gasStation.getWidth(), (int) gasStation.getHeight(), null);
		}
	}
}
