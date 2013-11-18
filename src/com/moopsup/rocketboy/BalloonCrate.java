package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class BalloonCrate extends Upgrade 
{
	Rectangle crate;
	BufferedImage BALLOON_CRATE = null;
	int numberOfBalloonsInCrate;
	Random RND = new Random();
	int yCoord;
	ArrayList<Balloon> supportBalloons;
	
	public BalloonCrate(BufferedImage BALLOON_CRATE)
	{
		this.BALLOON_CRATE = BALLOON_CRATE;
		numberOfBalloonsInCrate = RND.nextInt(5) + 10;
		yCoord = -5;
		crate = new Rectangle(RND.nextInt((int) RocketBoy.GAME_WIDTH - 30), yCoord, 30, 30);
		supportBalloons = new ArrayList<Balloon>();
		for(int i = 0; i < 6; i++)
		{
			Balloon toAdd = new Balloon();
			toAdd.isCrateBalloon = true;
			toAdd.designatedCrate = this;
			supportBalloons.add(toAdd);
		}
	}
	
	@Override
	public boolean collides(Rectangle characterObject) 
	{
		if(crate.intersects(characterObject))
		{
			for(int p = 0; p < supportBalloons.size(); p++)
			{
				supportBalloons.get(p).flyAway = true;
				MainPanel.flyBalloons.add(supportBalloons.get(p));
			}
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		yCoord -= MainPanel.vertVelocity;
		
		crate.setLocation((int) crate.getX(), yCoord);
		
		if(crate.getY() - 200 > RocketBoy.GAME_HEIGHT)
		{  
			isPresent = false; 
		}
		
		for(Balloon balloon : supportBalloons)
		{
			//houseMain isn't used, just part of the signature ;/
			balloon.draw(g, MainPanel.gameChar.theCharacter); 
		}
		g.drawImage(BALLOON_CRATE, (int) crate.getX(), (int) crate.getY(), 30, 30, null);
	}
}
