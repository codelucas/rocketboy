package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class EnemyObject 
{
	boolean isImpeded = false;
	boolean isImpact = false;
	boolean hitByFlyBalloon = false;
	boolean removeMe = false;
	
	public abstract boolean collides(Rectangle characterObject);
	
	public abstract void draw(Graphics2D g, Graphics2D secondAffine);
}
