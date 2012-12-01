package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class Upgrade 
{
	boolean isPresent = true;
	
	public abstract boolean collides(Rectangle characterObject);
	
	public abstract void draw(Graphics2D g);
}
