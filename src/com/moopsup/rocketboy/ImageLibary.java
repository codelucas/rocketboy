package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//A class which holds static methods which draw static images.
public class ImageLibary
{
	BufferedImage TREE = null;
	BufferedImage BARN = null;
	BufferedImage GRASS = null;
	
	public ImageLibary(BufferedImage TREE, BufferedImage BARN, BufferedImage GRASS)
	{
		this.TREE = TREE;
		this.BARN = BARN;
		this.GRASS = GRASS;
	}
	
	public static void drawThePlatforms(Graphics2D g, ArrayList<GeneralPlatform> platforms)
	{
		for(GeneralPlatform platform : platforms)
		{
			platform.draw(g);
		}
	}
	
	public static void drawUpgrades(Graphics2D g, ArrayList<Upgrade> upgrades)
	{
		for(Upgrade upgrade : upgrades)
		{
			upgrade.draw(g);
		}
	}
	
	public static void drawBoostBar(Graphics2D g, BoostBar bar)
	{
		bar.draw(g);
	}

	public static void drawHealthBar(Graphics2D g, HouseHealthBar bar)
	{
		bar.draw(g);
	}
	
	public static void drawEnemyObjects(Graphics2D g, Graphics2D secondAffine, ArrayList<EnemyObject> enemies)
	{
		for(EnemyObject enemy : enemies)
		{
			enemy.draw(g, secondAffine);
		}
	}
	
	public void drawGroundAndSkyscrapers(Graphics2D g, Rectangle ground)
	{
		//As the y value goes down the number goes up
		if(ground.getY() <= RocketBoy.GAME_HEIGHT) 
		{
			//g.fill(ground);
			g.drawImage(GRASS, MainPanel.ground.x, MainPanel.ground.y,
					MainPanel.ground.width, MainPanel.ground.height, null);
			//g.fill(UpUpAndAway.ground);
		}
		//Main buildings and trees.
		if(ground.getY() - 900 <= RocketBoy.GAME_HEIGHT)
		{
			//g.drawImage(BARN, (int) ground.getX() + 50, (int) ground.getY() - 207, 330, 330, null);
			g.drawImage(TREE, (int) ground.getX() + 875, (int) ground.getY() - 100, 50, 145, null);
			g.drawImage(TREE, (int) ground.getX() + 800, (int) ground.getY() - 130, 50, 160, null);
			g.drawImage(TREE, (int) ground.getX() + 950, (int) ground.getY() - 135, 55, 169, null);
			g.drawImage(TREE, (int) ground.getX() + 150, (int) ground.getY() - 80, 45, 100, null);
			g.drawImage(TREE, (int) ground.getX() + 10, (int) ground.getY() - 80, 45, 110, null);
		}
	}
	
	public static void drawBalloons(Graphics2D g, ArrayList<Balloon> balloons, ArrayList<Balloon> flyBalloons,
			Rectangle theHouse)
	{
		//drawing balloons!
		for(Balloon balloon : balloons)
		{
			balloon.draw(g, theHouse);
		}
		
		for(int c = 0; c < flyBalloons.size(); c++)
		{									//draw and remove flyBalloons
			flyBalloons.get(c).draw(g, theHouse);
			if(flyBalloons.get(c).balloon.getY() < -10)
			{
				flyBalloons.remove(c);
				c--;
			}
		}
	}
	
	public static void drawClouds(Graphics2D g, ArrayList<Cloud> clouds, int vertVelocity)
	{
		for(int c = 0; c < clouds.size(); c++)
		{
			//The clouds go down (gravity); to look like balloons and house are going up
			clouds.get(c).cloud.setLocation((int)clouds.get(c).cloud.getX(),(int)clouds.get(c).cloud.getY() - vertVelocity);
			clouds.get(c).draw(g);
		}
	}
	
	public static void drawChar(MainChar theHouse, Graphics2D g, Graphics2D jetpackAffine)
	{
		theHouse.draw(g, jetpackAffine);
	}
}
