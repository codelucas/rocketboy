package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Cloud 
{
	Random RND = new Random();
	Rectangle cloud = new Rectangle((int) (Math.random() * 950), (int) (Math.random() * 600),
			RND.nextInt(450) + 100, RND.nextInt(100) + 20);
	int speed = RND.nextInt(2) + 1;
	boolean withinParameters = false;
	int alpha = RND.nextInt(80) + 140;
	int randomCloudFormation = RND.nextInt(4);
	
	BufferedImage CLOUD_ONE = null;
	BufferedImage CLOUD_TWO = null;
	BufferedImage CLOUD_THREE = null;
	BufferedImage CLOUD_FOUR = null;

	public Cloud(BufferedImage CLOUD_ONE, BufferedImage CLOUD_TWO, BufferedImage
			CLOUD_THREE, BufferedImage CLOUD_FOUR)
	{
		this.CLOUD_ONE = CLOUD_ONE;
		this.CLOUD_TWO = CLOUD_TWO;
		this.CLOUD_THREE = CLOUD_THREE;
		this.CLOUD_FOUR = CLOUD_FOUR;
	}
	
	public void draw(Graphics2D g)
	{
		cloud.setLocation((int) cloud.getX() + speed, (int) cloud.getY());
		
		if(cloud.getCenterX() <= RocketBoy.GAME_WIDTH && cloud.getCenterX() > 0 && cloud.getCenterY() 
				<= RocketBoy.GAME_HEIGHT && cloud.getCenterY() > 0)
		{
			withinParameters = true;
		}
		else
		{
			withinParameters = false;
		}
		//if out of bounds, change new cloud
		if(cloud.getX() > RocketBoy.GAME_WIDTH)
		{	
			int x = (int) (0 - cloud.getWidth() - 100);
			int y = (int) (Math.random() * 625);
			cloud = new Rectangle(x, y, RND.nextInt(450) + 100, RND.nextInt(100) + 20);
			cloud.setLocation((int) cloud.getX(), (int) cloud.getY());
		}
		//if out of bounds, change new cloud
		if(cloud.getY() > RocketBoy.GAME_HEIGHT)
		{
			int x = (int) (Math.random() * 950);
			int y = (int) (0 - cloud.getHeight() / 2);
			
			cloud = new Rectangle(x, y, RND.nextInt(450) + 100, RND.nextInt(100) + 20);
			cloud.setLocation((int) cloud.getX(), ((int) cloud.getY()));
		}
		if(cloud.getY() < -100 && MainPanel.vertVelocity > 0)
		{   
			//if out of bounds, change new cloud
			int x = (int) (Math.random() * 950);
			int y = (int) (RocketBoy.GAME_HEIGHT - (cloud.getHeight() / 2));
			cloud = new Rectangle(x, y, RND.nextInt(450) + 100, RND.nextInt(100) + 20);
			cloud.setLocation((int) cloud.getX(), (int) cloud.getY());
		}
		
		//drawing
		if(randomCloudFormation == 0)
		{
			g.drawImage(CLOUD_ONE, (int) cloud.getX(), (int) cloud.getY(),
					(int) cloud.getWidth(), (int) cloud.getHeight(), null);
		}
		else if(randomCloudFormation == 1)
		{
			g.drawImage(CLOUD_TWO, (int) cloud.getX(), (int) cloud.getY(),
					(int) cloud.getWidth(), (int) cloud.getHeight(), null);
		}
		else if(randomCloudFormation == 2)
		{
			g.drawImage(CLOUD_THREE, (int) cloud.getX(), (int) cloud.getY(),
					(int) cloud.getWidth(), (int) cloud.getHeight(), null);
		}
		else if(randomCloudFormation == 3)
		{
			g.drawImage(CLOUD_FOUR, (int) cloud.getX(), (int) cloud.getY(),
					(int) cloud.getWidth(), (int) cloud.getHeight(), null);
		}
	}
}
