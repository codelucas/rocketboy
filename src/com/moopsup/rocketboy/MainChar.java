package com.moopsup.rocketboy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MainChar 
{
	static final int HOUSE_WIDTH = 34;
	static final int HOUSE_HEIGHT = 57;
	
	BufferedImage[] CHARACTER_SPRITES = new BufferedImage[8];
	BufferedImage SKULL_COUNTER;
	
	Rectangle theCharacter, shield;
	//Health and boost fields.
	HouseHealthBar healthBar;
	BoostBar boostBar;
	
	int wheelSpinSpeed;
	int balloonCount;
	int SIDESCROLL_FACTOR = 12;
	int startDmgCounter = 25;
	int counterX, counterY;
	
	//Timers for captions or counter images
	int safeTimer, isHit, healthBoosted, boostTimer;
	
	//Trigonometric cannon/projectile fields.
	double littleX, littleY, destinationX, destinationY = 0;
	
	//boolean status's
	boolean isHittingPlatform, rocketsOn, jetPackColorSwitcher, gettingGas, hitMissile, counterOnce;
	
	int coinCount, hitCoin;
	Random RND;
	
	//Affine for the turning gear.
	AffineTransform affine = new AffineTransform();
	Rectangle underlyingGear, unusedHeater = null;
	int radians, xDisplaceMent = 0;
	
	//Type strings for direction
	String type, previousType;
	
	int spriteTimer;
	
	public MainChar(BufferedImage[] CHARACTER_SPRITES, BufferedImage DAMAGE_COUNTER)
	{
		jetPackColorSwitcher = true;
		counterOnce = true;
		balloonCount = 0;
		this.CHARACTER_SPRITES = CHARACTER_SPRITES;
		this.SKULL_COUNTER = DAMAGE_COUNTER;
		
		isHittingPlatform = false;
		wheelSpinSpeed = 5;
		gettingGas = false;
		
		spriteTimer = 0;
		rocketsOn = false;
		RND = new Random();
		hitCoin = 0;
		coinCount = 0;
		type = "NONE";
		previousType = "NONE";
		theCharacter = new Rectangle(RocketBoy.GAME_WIDTH / 2, RocketBoy.GAME_HEIGHT - 190, HOUSE_WIDTH, HOUSE_HEIGHT); 	
		
		healthBar = new HouseHealthBar();
		boostBar = new BoostBar();
		
		hitMissile = false;
		
		safeTimer = 0;
		shield = new Rectangle(0, 0, 0, 0);
		isHit = 0;
		healthBoosted = 0;
	}
	
	public void resetEverything()
	{
		counterOnce = true;
		radians = 0;
		affine = new AffineTransform();
		underlyingGear = null;
		gettingGas = false;
		balloonCount = 0;
		isHittingPlatform = false;
		spriteTimer = 0;
		rocketsOn = false;
		RND = new Random();
		hitCoin = 0;
		coinCount = 0;
		type = "NONE";
		previousType = "NONE";
		theCharacter = new Rectangle(RocketBoy.GAME_WIDTH / 2, RocketBoy.GAME_HEIGHT - 190, HOUSE_WIDTH, HOUSE_HEIGHT); 	
		boostTimer = 0;
		healthBar = new HouseHealthBar();
		boostBar = new BoostBar();
		//heliumBar = new HeliumBar();
		hitMissile = false;
		
		safeTimer = 0;
		shield = new Rectangle(0, 0, 0, 0);
		isHit = 0;
		healthBoosted = 0;
	}
	
	//NOTE: The draw code is contained in the ImageLibary for this particular class.
	
	//"RIGHT", "LEFT", or "NONE".
	public void updateXAxis()
	{
		if(type.equals("RIGHT"))
		{						
			int goingTo = (int) theCharacter.getX() + SIDESCROLL_FACTOR;
			if(goingTo > RocketBoy.GAME_WIDTH - 40)
			{
				goingTo = goingTo - SIDESCROLL_FACTOR;
			}
			theCharacter.setLocation(goingTo, (int) theCharacter.getY());
			xDisplaceMent = (RocketBoy.GAME_WIDTH / 2) - goingTo;
			
			previousType = "RIGHT";
		}
		else if(type.equals("LEFT"))
		{ 			
			int goingTo = (int) theCharacter.getX() - SIDESCROLL_FACTOR;
			if(goingTo < 0)
			{
				goingTo = goingTo + SIDESCROLL_FACTOR;
			}
			theCharacter.setLocation(goingTo, (int) theCharacter.getY());
			xDisplaceMent = (RocketBoy.GAME_WIDTH / 2) - goingTo;
			
			previousType = "LEFT";
		}
	}
	
	public void setDirection(String type)
	{
		this.type = type;
	}
	
	public void updateCannonPointer(int x, int y)
	{
		destinationX = x;
		destinationY = y;
	}
	//Main Draw Method.
	public void draw(Graphics2D g, Graphics2D jetpackAffine)
	{
		//if(cannonVisible)
		//	drawCannon(g);
		
		drawForceFields(g);
		drawJetPack(g, jetpackAffine);
		drawFlare(g);
		drawActualChar(g);
	}
	
//	CHARACTER_SPRITES[0] = RIGHT_ONE; CHARACTER_SPRITES[1] = RIGHT_TWO; CHARACTER_SPRITES[2] = RIGHT_THREE;
//	CHARACTER_SPRITES[3] = RIGHT_FOUR; CHARACTER_SPRITES[4] = LEFT_ONE; CHARACTER_SPRITES[5] = LEFT_TWO;
//	CHARACTER_SPRITES[6] = LEFT_THREE; CHARACTER_SPRITES[7] = LEFT_FOUR; CHARACTER_SPRITES[8] = RIGHT_UP;
//	CHARACTER_SPRITES[9] = LEFT_UP; CHARACTER_SPRITES[10] = RIGHT_JETPACK; CHARACTER_SPRITES[11] = LEFT_JETPACK;
//	CHARACTER_SPRITES[12] = FLAME_ONE;  CHARACTER_SPRITES[13] = FLAME_TWO;  CHARACTER_SPRITES[14] = FLAME_THREE; 
//	CHARACTER_SPRITES[15] = FLAME_FOUR;
	
	public void drawJetPack(Graphics2D g, Graphics2D jetpackAffine)
	{
		if(type.equals("LEFT"))
		{
			unusedHeater = new Rectangle((int) theCharacter.getX() + 17, (int) theCharacter.getY() + 3,
					20, 40);
			underlyingGear = new Rectangle((int) (unusedHeater.getMinX() + (unusedHeater.getWidth() - 10)), (int)
					(unusedHeater.getMinY() + (unusedHeater.getHeight() - 12)), 10 , 10);
		}
		else if(type.equals("RIGHT"))
		{
			unusedHeater = new Rectangle((int) theCharacter.getX() - 5, (int) theCharacter.getY() + 3,
					20, 40);
			underlyingGear = new Rectangle((int) (unusedHeater.getMinX() + (unusedHeater.getWidth() - 18)), (int)
					(unusedHeater.getMinY() + (unusedHeater.getHeight() - 12)), 10 , 10);
		}
		else if(type.equals("NONE"))
		{
			if(previousType.equals("LEFT"))
			{
				unusedHeater = new Rectangle((int) theCharacter.getX() + 17, (int) theCharacter.getY() + 3,
						20, 40);
				underlyingGear = new Rectangle((int) (unusedHeater.getMinX() + (unusedHeater.getWidth() - 10)), (int)
						(unusedHeater.getMinY() + (unusedHeater.getHeight() - 12)), 10 , 10);
			}
			//Default normally face right if nothing is set, otherwise follow the keys.
			else
			{
				unusedHeater = new Rectangle((int) theCharacter.getX() - 5, (int) theCharacter.getY() + 3,
						20, 40);
				underlyingGear = new Rectangle((int) (unusedHeater.getMinX() + (unusedHeater.getWidth() - 18)), (int)
						(unusedHeater.getMinY() + (unusedHeater.getHeight() - 12)), 10 , 10);
			}
		}
		if(MainPanel.gameOver || MainPanel.gameChar.healthBar.fillCounter <= 0)
		{
			//Don't draw the heater.
		}
		else
		{
			//Draw the Heater Skin
			if(previousType.equals("LEFT"))
			{
				g.drawImage(CHARACTER_SPRITES[11], (int) theCharacter.getMaxX() - 12, (int) unusedHeater.getY() + 5, 20, 40, null);
			}
			//Default normally face right if nothing is set, otherwise follow the keys.
			else
			{
				g.drawImage(CHARACTER_SPRITES[10], (int) theCharacter.getMinX() - 8, (int) unusedHeater.getY() + 5, 20, 40, null); 
			}
		}
		radians += wheelSpinSpeed;
		
		affine.setToRotation(Math.toRadians(radians), (int) underlyingGear.getX() + underlyingGear.getWidth() / 2, 
				(int) underlyingGear.getY() + underlyingGear.getHeight() / 2);
		jetpackAffine.setTransform(affine);
		
		if(gettingGas)
		{
			jetpackAffine.setColor(new Color(220, 20, 60));
		}
		else
		{
			jetpackAffine.setColor(Color.DARK_GRAY);
		}
		
		//Only draw the jetpack/gears if the game isn't over yet
		if(!MainPanel.gameOver && MainPanel.gameChar.healthBar.fillCounter > 0)
		{
			jetpackAffine.fillOval((int) underlyingGear.getX(), (int) underlyingGear.getY(), (int) underlyingGear.getWidth(),
					(int) underlyingGear.getHeight());
			jetpackAffine.setColor(Color.WHITE);
			jetpackAffine.fillOval((int) underlyingGear.getCenterX() - 1, (int) underlyingGear.getY(), 3, 3);
			jetpackAffine.fillOval((int) underlyingGear.getCenterX() - 5, (int) underlyingGear.getCenterY(), 3, 3);
			jetpackAffine.fillOval((int) underlyingGear.getCenterX() + 1, (int) underlyingGear.getCenterY() + 1, 3, 3);
		}
		
		gettingGas = false;
		if(radians >= Integer.MAX_VALUE - 10)
		{
			radians = 0;
		}
	}
	
	public void drawCannon(Graphics2D g)
	{
		//Draw the rotating cannon (Based off of our underlying line).
	    double xDistance = destinationX - theCharacter.getCenterX(); //Get x distance from gun to mouse
	    double yDistance = destinationY - theCharacter.getCenterY(); //Get y distance from gun to mouse
	    double radAngle = Math.atan(yDistance/xDistance); //Use atan to calculate the angle
	    if(destinationX >= theCharacter.getCenterX())
	    {
	    	littleY  = Math.sin(radAngle) * 90;  
	    	littleX = Math.cos(radAngle) * 90;
	    }
	    else
	    {
	    	littleY = Math.sin(radAngle) * (-90); 
	    	littleX = Math.cos(radAngle) * (-90);
	    }
		g.setColor(Color.DARK_GRAY);
		Line2D underlying = new Line2D.Double(theCharacter.getCenterX(), theCharacter.getCenterY(),
				(theCharacter.getCenterX() + littleX), (theCharacter.getCenterY() + littleY));
		
		int[] cannonX = {(int) (underlying.getX1() - 5), (int) (underlying.getX1() + 5), 
				(int) (underlying.getX2() + 5), (int) (underlying.getX2() - 5)};
		
		int[] cannonY = {(int) underlying.getY1() + 3, (int) underlying.getY1() + 3, 
				(int) (underlying.getY2() - 5), (int) (underlying.getY2() - 5)};
		
		g.fillPolygon(cannonX, cannonY, 4);
		//Draw the laser pointer.
//		g.setColor(Color.RED);
//		g.draw(underlying);	
	}
	
	public void drawFlare(Graphics2D g)
	{
		if(previousType.equals("LEFT"))
		{
			if(boostTimer > 0)
			{	
				if((spriteTimer - 0) % 4 == 0) 
					g.drawImage(CHARACTER_SPRITES[12], (int) unusedHeater.getX() + 9, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 1) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[13], (int) unusedHeater.getMinX() + 9, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 2) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[14], (int) unusedHeater.getMinX() + 9, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 3) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[15], (int) unusedHeater.getMinX() + 9, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
			}
		}
		//Default normally face right if nothing is set, otherwise follow the keys.
		else
		{
			if(boostTimer > 0)
			{	
				if((spriteTimer - 0) % 4 == 0) 
					g.drawImage(CHARACTER_SPRITES[12], (int) unusedHeater.getX() - 1, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 1) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[13], (int) unusedHeater.getMinX() - 1, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 2) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[14], (int) unusedHeater.getMinX() - 1, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
				else if((spriteTimer - 3) % 4 == 0)
					g.drawImage(CHARACTER_SPRITES[15], (int) unusedHeater.getMinX() - 1, (int) unusedHeater.getMaxY() - 5, 12, 35, null);
			}
		}	
	}
	
	public void drawForceFields(Graphics2D g)
	{
		if(safeTimer > 0)
		{
			shield = new Rectangle((int) theCharacter.getX() - 115, (int) theCharacter.getY()
					- 235, 260, 260);
			g.setColor(new Color(255, 130, 171, 128)); //(R, G, B, ALPHA 0 - 255)
			g.fillOval((int) theCharacter.getX() - 160, (int) theCharacter.getY() - 280, 350, 350);
			safeTimer--;
		}
		else
		{
			shield = new Rectangle(0, 0, 0, 0);
		}
	}

	//CHARACTER_SPITES index: 0 - 3 = Right sprites, 4 - 7 = Left sprites 
	public void drawActualChar(Graphics2D g)
	{ 		
		if(boostTimer > 0)
		{   
			g.setColor(new Color(255, 216, 163)); 
			boostBar.fillCounter = boostBar.fillCounter - 1;
			boostTimer--;
		}
		
		if(MainPanel.gameOver || MainPanel.gameChar.healthBar.fillCounter <= 0)
		{
			//Because the char is lying down, this is a special case, flip house width/height for the drawing!
			g.drawImage(CHARACTER_SPRITES[16], (int) theCharacter.getX(), (int) theCharacter.getY() + 27, HOUSE_HEIGHT, HOUSE_WIDTH, null);
		}
		else
		{
		if(type.equals("LEFT"))
		{	
			//If the velocity isn't zero, we can't have our guy walk cuz hes flying! Other than that go for the animation.
			if(MainPanel.vertVelocity != 0)
				g.drawImage(CHARACTER_SPRITES[9], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);

			else
			{
				if(spriteTimer < 3 || spriteTimer >= 12)
				{
					if(spriteTimer >= 12)
						spriteTimer = 0;
					g.drawImage(CHARACTER_SPRITES[5], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 6)
				{
					g.drawImage(CHARACTER_SPRITES[6], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 9)
				{
					g.drawImage(CHARACTER_SPRITES[7], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 12)
				{
					g.drawImage(CHARACTER_SPRITES[4], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
			}
		}
		else if(type.equals("RIGHT"))
		{
			if(MainPanel.vertVelocity != 0)
				g.drawImage(CHARACTER_SPRITES[8], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
			else
			{
				if(spriteTimer < 3 || spriteTimer >= 12)
				{
					if(spriteTimer >= 12)
						spriteTimer = 0;
					g.drawImage(CHARACTER_SPRITES[1], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 6)
				{
					g.drawImage(CHARACTER_SPRITES[2], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 9)
				{
					g.drawImage(CHARACTER_SPRITES[3], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
				else if(spriteTimer < 12)
				{
					g.drawImage(CHARACTER_SPRITES[0], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
					spriteTimer++;
				}
			}
		}
		else if(type.equals("NONE"))
		{
			if(previousType.equals("LEFT"))
			{
				if(MainPanel.vertVelocity != 0)
					g.drawImage(CHARACTER_SPRITES[9], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
				else
					g.drawImage(CHARACTER_SPRITES[4], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
			}
			//Default normally face right if nothing is set, otherwise follow the keys.
			else
			{
				if(MainPanel.vertVelocity != 0)
					g.drawImage(CHARACTER_SPRITES[8], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
				else
					g.drawImage(CHARACTER_SPRITES[0], (int) theCharacter.getX(), (int) theCharacter.getY(), HOUSE_WIDTH, HOUSE_HEIGHT, null);
			}
		}
		}
		//g.fill(houseMain);
		if(isHit > 0)
		{ 
			if(counterOnce)
			{
				counterX = (int) theCharacter.getX() + RND.nextInt(55) - 20;
				counterY = (int) theCharacter.getY() + RND.nextInt(70) - 20;
				counterOnce = false;
			}
			g.drawImage(SKULL_COUNTER, counterX, counterY, startDmgCounter, startDmgCounter, null);
			startDmgCounter += 3;
		}
		else if(hitCoin > 0)
		{
			g.setFont(new Font("Book Antiqua", Font.BOLD, 22));
			g.setColor(new Color(238, 238, 0, 220));
			g.drawString("+100", (int) theCharacter.getX() - 55, (int) theCharacter.getY() + 10);
		}
		else if(healthBoosted > 0)
		{
			g.setFont(new Font("Book Antiqua", Font.BOLD, 15));
			g.setColor(new Color(220, 20, 60, 220));
			g.drawString("+50HP", (int) theCharacter.getX() - 55, (int) theCharacter.getY() + 10);
		}
		if(spriteTimer == Integer.MAX_VALUE - 1) {spriteTimer = 0;}
		
		if(isHit <= 0) {startDmgCounter = 17; counterOnce = true;}
		
		healthBoosted--; isHit--; hitCoin--; spriteTimer++;
	}
}
