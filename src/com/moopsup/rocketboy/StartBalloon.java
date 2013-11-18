package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

//Special balloon objects designed exlusively for start/exit screens and other
//animation related things (NON GAME).
public class StartBalloon 
{
	Rectangle balloon;
	Random RND = new Random();
	Color color;
	int hor;
	int vert;
	int speed;
	int lineLength;
	String direction;
	int time = 0;
	
	public StartBalloon()
	{
		balloon = new Rectangle(0, 0, 16, 24); 
		//An interesting algorithm for evenly distributing points in a circle
		//Note the square root used on the circle radius, it is the key factor.
		hor = RND.nextInt(RocketBoy.GAME_WIDTH);
		vert = RND.nextInt(RocketBoy.GAME_HEIGHT);
		speed = RND.nextInt(4) + 2;
		
		lineLength = RND.nextInt(15) + 14;
		
		direction = "left";
		
		//Create a random "bright color" using RGB.
		final float hue = RND.nextFloat();
		final float saturation = 0.9f;
		final float luminance = 1.0f;
		color = Color.getHSBColor(hue, saturation, luminance); 
		//color = new Color(RND.nextInt(135) + 120, RND.nextInt(135) + 120, RND.nextInt(135) + 120, 200);
	}
	
	public void draw(Graphics2D g)
	{ 
		balloon.setLocation(hor, vert += -speed);
		
		if(vert <= 0)
		{
			hor = RND.nextInt(RocketBoy.GAME_WIDTH);
			vert = RocketBoy.GAME_HEIGHT;
			balloon = new Rectangle(hor, vert, 16, 24); 
		}
		//Special sequence which shakes the balloons to make them look more realistic.
		if(direction.equals("left") && time % 30 == 0)
		{
			hor = (int) balloon.getX() + 1;
			balloon.setLocation(hor, vert);
			direction = "right";	
		}
		else if (direction.equals("right") && time % 30 == 0)
		{
			hor = (int) balloon.getX() - 1;
			balloon.setLocation(hor, vert);
			direction = "left";
		}
	    time++;
	    
		g.setColor(Color.WHITE);
		g.drawLine((int) balloon.getX() + (int) balloon.getWidth()/2, (int)balloon.
				getY() + (int)balloon.getHeight() - 3, (int) balloon.getX() + 
				(int) balloon.getWidth()/2 , (int)balloon.getY() + (int)balloon.
				getHeight() + lineLength);
		
		g.setColor(color);
		g.fillOval((int) balloon.getX(), (int) balloon.getY(), (int) 
				balloon.getWidth(), (int) balloon.getHeight());
	}
}
