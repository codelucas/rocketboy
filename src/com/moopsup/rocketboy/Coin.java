package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Coin extends Upgrade
{
	Random RND;
	Rectangle coin;
	int vert;
	boolean isImpact;
	
	public Coin()
	{
		RND = new Random(); 
		coin = new Rectangle(RND.nextInt(RocketBoy.GAME_WIDTH), 0, 15, 25);
		vert = 5;
	}
	
	public void draw(Graphics2D g)
	{
		coin.setLocation((int) coin.getX() + (RND.nextInt(4) - 2), (int) coin.getY() + vert);
		if(coin.getMaxY() > RocketBoy.GAME_HEIGHT || coin.getMaxX() > RocketBoy.GAME_WIDTH
				|| coin.getMinX() < 0)
		{
			isPresent = false;
		}
		g.setColor(new Color(255, 255, 0, RND.nextInt(120) + 120));
		g.fillOval((int) coin.getX(), (int) coin.getY(), (int) coin.getWidth(), (int) coin.getHeight());
	}

	@Override
	public boolean collides(Rectangle characterObject) 
	{
		if(coin.intersects(characterObject))
		{
			return true;
		}
		return false;
	}
}
