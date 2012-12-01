package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class GeneralPlatform 
{
	Rectangle platform = null;
	Rectangle underSpike = null;
	
	Rectangle gasStation = null;
	Rectangle extraHelium = null;
	
	boolean removeMe = false;
	boolean hasPump;
	
	public abstract boolean landsOn(Rectangle theHouseObject);
	
	public abstract void draw(Graphics2D g);
}
