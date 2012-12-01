package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.Random;


public class RandomUpGrade extends Upgrade
{
	Random RND = new Random();
	Rectangle upgradeBox;
	int hor;
	int vert;
	//0 for forcefield, 1 for health, 2 for more helium.
	int whatIsThis;
	BufferedImage QUESTION_UPGRADE = null;
	
	public RandomUpGrade(BufferedImage QUESTION_UPGRADE)
	{
		this.QUESTION_UPGRADE = QUESTION_UPGRADE;
		hor = RND.nextInt(RocketBoy.GAME_WIDTH);
		vert = 7;
		upgradeBox = new Rectangle(hor, 0, 25, 25);
		whatIsThis = RND.nextInt(2);
	}
	
	@Override
	public boolean collides(Rectangle characterObject) 
	{
		if(upgradeBox.intersects(characterObject))
		{
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g)
	{
		//If the forcefield falls out, make sure it gets removed.
		if(upgradeBox.getMinY() >= RocketBoy.GAME_HEIGHT)
		{
			isPresent = false;
		}
		upgradeBox.setLocation(hor, (int) (upgradeBox.getY() + vert));
		g.drawImage(QUESTION_UPGRADE, hor, (int) (upgradeBox.getY() + vert), 40, 40, null);
	}
}
