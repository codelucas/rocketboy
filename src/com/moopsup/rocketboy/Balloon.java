package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

//If a balloon gets hit by an obstacle, its guaranteed to be "flying away" and also
//"got hit", when we release a balloon its only "flying away", if a balloon is
//"got hit", it must "flying away" also. "Burnt" is just a trait which controls color.
public class Balloon
{
	boolean isStaticBalloon;
	boolean isCrateBalloon;
	BalloonCrate designatedCrate;
	
	Rectangle balloon;
	int vert;
	int hor ;
	
	//Balloon timers.
	int sleepTime;
	
	Color color;
	String direction;
	
	//boolean doneExpanding;
	boolean flyAway;
	boolean gotHit;
	boolean burnt;
	int boostTime;
	int rememberLostAltitude = 0;
	Color originalColor = null;
	
	//Designed so I can free my balloons in a set y coordinate.
	boolean oneTime = true;
	int finalXCoord = 0;
	static final int CIRCLE_RADIUS = 80;
	double positionSpeed = 0.04;
	int deltaX;
	int deltaY;
	int rawXDifference;
	int rawYDifference;
	
	Random RND = new Random();
	
	//After balloon generation, all balloons will float toward randomized x/y values within a circle.
	int finalXValue;
	int finalYValue;
	boolean finalCoordHasBeenHit;
	boolean stepOne;
	boolean stepTwo;

	//Constructor for the main game, balloons being set my mouse.
	public Balloon(int x, int y)                                      
	{
		hor = x;
		vert = y;
		
		isStaticBalloon = false;
		isCrateBalloon = false;
		designatedCrate = null;
		
		balloon = new Rectangle(hor, vert, 3, 6); 
		
		rawXDifference = (int) (CIRCLE_RADIUS * Math.sqrt(Math.random()) * Math.cos(Math.PI * 2 * Math.random()));
		rawYDifference = (int) (CIRCLE_RADIUS *  Math.sqrt(Math.random()) * Math.sin(Math.PI * 2 * Math.random()));
		
		finalXValue = (int) (MainPanel.gameChar.theCharacter.getX() + rawXDifference);
		finalYValue = (int) (250 + rawYDifference);
		
		finalCoordHasBeenHit = false;
		stepOne = false;
		stepTwo = false;
		
		deltaX = finalXValue - hor;
		deltaY = finalYValue - vert;
		
		sleepTime = 0;
		//Create a random "bright color" using RGB.
		final float hue = RND.nextFloat();
		final float saturation = 0.9f;
		final float luminance = 1.0f;
		color = Color.getHSBColor(hue, saturation, luminance); 
		
		//color = new Color(RND.nextInt(135) + 120, RND.nextInt(135) + 120, RND.nextInt(135) + 120, 200);
		
		originalColor = color;
		int randomDirection = RND.nextInt(2);
		if(randomDirection == 0)
			direction = "left";
		else if(randomDirection == 1)
			direction = "right";
		flyAway = false; 
		gotHit = false;
		burnt = false;
		boostTime = -1;
	}
	
	//Constructor for any static uses, balloons generated randomly.
	public Balloon()
	{
		balloon = new Rectangle(0, 0, 3, 6); 
		isStaticBalloon = true;
		isCrateBalloon = false;
		designatedCrate = null;
		//An interesting algorithm for evenly distributing points in a circle
		//Note the square root used on the circle radius, it is the key factor.
		hor = (int) (10 + CIRCLE_RADIUS * Math.sqrt(Math.random()) * Math.cos(Math.PI * 2 * Math.random()));
		vert = (int) (200 + CIRCLE_RADIUS *  Math.sqrt(Math.random()) * Math.sin(Math.PI * 2 * Math.random()));
	
		sleepTime = 0;
		//Create a random "bright color" using RGB.
		final float hue = RND.nextFloat();
		final float saturation = 0.9f;
		final float luminance = 1.0f;
		color = Color.getHSBColor(hue, saturation, luminance); 
		
		//color = new Color(RND.nextInt(135) + 120, RND.nextInt(135) + 120, RND.nextInt(135) + 120, 200);
		
		originalColor = color;
		int randomDirection = RND.nextInt(2);
		if(randomDirection == 0)
			direction = "left";
		else if(randomDirection == 1)
			direction = "right";
		flyAway = false; 
		gotHit = false;
		burnt = false;
		boostTime = -1;
	}
	
	public Rectangle getBalloon()
	{
		return balloon;
	}
	
	int tempTimer = 0;
	public void draw(Graphics2D g, Rectangle houseMain)
	{  
		//If we release balloons, the white line will not be drawn, giving impression of a balloon flying upwards
		if(flyAway || gotHit)    
		{
			vert += 4;
			vert += MainPanel.vertVelocity;
		}
		//If the balloon is burnt, have it fall out quicker.
		if(burnt)
		{
			vert += -7;
		}
		
		if(boostTime > 0)
		{
			vert += -7;
			rememberLostAltitude += -7;
			if(boostTime % 6 == 0)
			{
				color = color.brighter();
			}
			boostTime--;
		}
		else if(boostTime == 0)
		{
			//After the boost, the balloons are "down there", we wanna bring em back.
			vert -= rememberLostAltitude;
			rememberLostAltitude = 0;
			//Reset the original color.
			color = originalColor;
			//Set it back to the -1 sentinel value.
			boostTime--;
		}
		
		//If the balloon gets hit, make it appear like so. (AKA have it shrink t)
		if(gotHit)
		{
			if(balloon.getWidth() > 0 || balloon.getHeight() > 0)
			{
				balloon = new Rectangle((int) balloon.getX(), (int) balloon.getY(), (int)
						balloon.getWidth() - 2, (int) balloon.getHeight() - 2);
			}
		}
		
		//When its flying away it shrinks also but not to that huge degree.
		else if(flyAway)
		{
			if(balloon.getWidth() > 5 || balloon.getHeight() >= 10)
			{
				balloon = new Rectangle((int) balloon.getX(), (int) balloon.getY(), (int)
						balloon.getWidth() - 1, (int) balloon.getHeight() - 1);
			}
		}
		
		//Controls the balloons shrink/growth rates, if its intact it remains stable.
		if(balloon.getWidth() <= 8 && balloon.getHeight() <= 16 && !flyAway && !burnt)
		{
			balloon = new Rectangle((int) balloon.getX(), (int) balloon.getY(), (int)
					balloon.getWidth() + 2, (int) balloon.getHeight() + 2);
		}
		
		if(flyAway || gotHit)
		{
			if(oneTime == true)
			{
				finalXCoord = (int) balloon.getCenterX();
				oneTime = false;
			}
			//The balloons will float away at the rate of which they left the house.
			balloon.setLocation(finalXCoord, (int) (houseMain.getY() - vert)); 
		}
		else //Still intact with house.
		{
			//Used for statically generated balloons, typically start menu and etc.
			if(isStaticBalloon && !isCrateBalloon)
			{
				//Set the balloons location.
				balloon.setLocation((int) houseMain.getX() + hor, (int) houseMain.getY() - vert); 
			
				//move the balloon left and right
				if(direction.equals("left") && sleepTime % 2 == 0)
				{
					balloon.setLocation((int) houseMain.getX() + hor + 1,(int) houseMain.getY() - vert);
					direction = "right";	
				}
				else if (direction.equals("right") && sleepTime % 2 == 0)
				{
					balloon.setLocation((int) houseMain.getX() + hor - 1,(int) houseMain.getY() - vert);
					direction = "left";
				}
			}
			//Used for attaching balloons on crates.
			else if(isCrateBalloon)
			{
				balloon.setLocation((int) designatedCrate.crate.getX() + hor, 
						(int) designatedCrate.crate.getY() - vert); 
				//move the balloon left and right
				if(direction.equals("left") && sleepTime % 2 == 0)
				{
					balloon.setLocation((int) designatedCrate.crate.getX() + hor + 1,
							(int) designatedCrate.crate.getY() - vert);
					direction = "right";	
				}
				else if (direction.equals("right") && sleepTime % 2 == 0)
				{
					balloon.setLocation((int) designatedCrate.crate.getX() + hor - 1,
							(int) designatedCrate.crate.getY() - vert);
					direction = "left";
				}
			}
			
			//Called during the game, as the balloons are not static.
			else
			{
				if(!finalCoordHasBeenHit)
				{
					if(tempTimer == 24)
					{
						finalCoordHasBeenHit = true;
					}
					
					balloon.setLocation((int) (hor += (positionSpeed * deltaX)), 
							(int) (vert += (positionSpeed * deltaY))); 
					tempTimer++;
				}
				else
				{
					//Set the balloons location.
					balloon.setLocation((int) houseMain.getX() + rawXDifference, 
							(int) houseMain.getY() - vert); 
			
					//move the balloon left and right
					if(direction.equals("left") && sleepTime % 2 == 0)
					{
						balloon.setLocation((int) houseMain.getX() + rawXDifference + 1, 
								(int) houseMain.getY() - vert); 
						direction = "right";	
					}
					else if (direction.equals("right") && sleepTime % 2 == 0)
					{
						balloon.setLocation((int) houseMain.getX() + rawXDifference - 1, 
								(int) houseMain.getY() - vert); 
						direction = "left";
					}
				}
			}
		}
		sleepTime++;
		
		//Draw line to the balloon if its still connected.
		if(!flyAway && !gotHit && !isCrateBalloon)
		{
			g.setColor(Color.WHITE);
			g.drawLine((int) balloon.getX() + (int)balloon.getWidth()/2, (int) balloon.
					getY() + (int) balloon.getHeight(), (int) houseMain.getCenterX() - 6, 
					(int) houseMain.getMinY());
		}
		//If its attached to a crate, draw a line to it.
		else if(isCrateBalloon && !flyAway && !gotHit)
		{
			g.setColor(Color.WHITE);
			g.drawLine((int) balloon.getX() + (int)balloon.getWidth()/2, (int) balloon.
					getY() + (int) balloon.getHeight(), (int) designatedCrate.crate.getCenterX(), 
					(int) designatedCrate.crate.getMinY());
		}
		
		if(burnt)
		{
			g.setColor(Color.BLACK);
		}
		//If the balloon is not "burnt" it still can be either in a "red mode" 
		//indicating it has just been hit by a an obstacle, or regular.
		else
		{
			g.setColor(color);
		}
		g.fillOval((int) balloon.getX(), (int) balloon.getY(), (int) 
				balloon.getWidth(), (int) balloon.getHeight());
	}
}