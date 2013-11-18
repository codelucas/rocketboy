package com.moopsup.rocketboy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;


//Green missile means that it is being impeded by balloons.
//Red means it is destroying balloons or the house.
//Yellow means that its being held in a force field.
public class Missile extends EnemyObject
{
	static final int HEIGHT = 97;
	static final int WIDTH = 22;
	
	BufferedImage[] MISSILE_SHEETS = null;
	
	Random RND;
	Rectangle missile;
	int VERT_SPEED = 60;
    int spriteTimer;
	AffineTransform affine = new AffineTransform();
	
	//Missile turn variables
	boolean goRight;
	int turnTimer, leftRightFactor = 0;
	int turnRadians, turnWidth;
	//Missile angle variables
	int angleTimer = 0;
	boolean shiftRight;
	int turnSpeed;
	//Random interval of time where a moving alarm signal appears.
	int alarmMode;
	int targetAlarmDestination, deltaX, startX, alarmMoveCounter; 
	double alarmSpeed;
	boolean alarmOneTime = true;
	boolean missileOneTime = true;
	boolean closeAlarmOneTime = true;
	//A panel is passed in to check if the user has volume on or off (Missiles have custom sounds).
	MainPanel panel;
    
	public Missile(BufferedImage[] MISSILE_SHEETS, MainPanel panel)
	{	
		this.MISSILE_SHEETS = MISSILE_SHEETS;
		this.panel = panel;
		RND = new Random(); 
		
		alarmMode = RND.nextInt(120) + 50;
		targetAlarmDestination = (int) MainPanel.gameChar.theCharacter.getX();
		alarmSpeed = 0.03;
		alarmMoveCounter = 25;
		
		missile = new Rectangle(RND.nextInt(RocketBoy.GAME_WIDTH), 1, WIDTH, HEIGHT);
		isImpact = false;
		hitByFlyBalloon = false;
		isImpeded = false;
		spriteTimer = 0;
		
		if(Math.random() < 0.5)
		{
			goRight = true;
			shiftRight = false;
		}
		else
		{
			goRight = false;
			shiftRight = true;
		}
		
		int randomDecider = RND.nextInt(2); 
		
		if(randomDecider == 0)
		{
			turnWidth = 10;
			turnSpeed = 5; 
		}
		else if (randomDecider == 1)
		{
			turnWidth = 25;
			turnSpeed = 12; 
		}
	}
	//Main draw method.
	public void draw(Graphics2D g, Graphics2D missileAffine)
	{
		if(alarmMode > 0)
		{
			if(alarmOneTime)
			{
				startX = (int) missile.getX();
				deltaX = targetAlarmDestination - startX;
				alarmOneTime = false;
			}
			if(alarmMoveCounter > 0)
			{
				alarmMoveCounter--;
			}
			else
			{
				//If our alarm reaches its destination, generate new locations.
				targetAlarmDestination = (int) MainPanel.gameChar.theCharacter.getX();
				alarmMoveCounter = 25;
				alarmOneTime = true;
			}
			//Because these are alarms, be sure to keep the rotation at a zero so other
			//missiles can't influence us.
			affine.setToRotation(0, 0, 0);
	    	missileAffine.setTransform(affine);
			missile.setLocation(startX += deltaX * alarmSpeed, 1);
		}
		
		else
		{ 
			if(missileOneTime && panel.volumeOn)
			{
				SoundClips.play(SoundClips.MISSILE_SOUND, 0);
				missileOneTime = false;
			}
			
			//Missile turn logic
			if(turnTimer % turnWidth == 0)
				goRight = !goRight;
			if(goRight)
				leftRightFactor = 5;
			else if(!goRight)
				leftRightFactor = -5;
			
			//Missile angle shift logic
			if(shiftRight)
			{
				if(turnRadians > -turnSpeed)
					turnRadians += -1;
				else
					shiftRight = false;
			}
			else if(!shiftRight)
			{
				if(turnRadians < turnSpeed)
					turnRadians += 1;
				else
					shiftRight = true;
			}
			//Checker for if its being hit by a shield.
			if(isImpeded)
				VERT_SPEED = 0;
			else
				VERT_SPEED = 60;
			//Checker for hit by fly balloon.
			if(hitByFlyBalloon)
	    		missile.setLocation((int) missile.getX() + leftRightFactor, (int) missile.getY() + VERT_SPEED - 4);
	    	else
	    		missile.setLocation((int) missile.getX() + leftRightFactor, (int) missile.getY() + VERT_SPEED);
	    
	    	//Affine (turn) the missile).
	    	affine.setToRotation(Math.toRadians(turnRadians), (int) missile.getX() + missile.getWidth() / 2, 
	    			(int) missile.getY() + missile.getHeight() / 2);
	    	missileAffine.setTransform(affine);
		}

	    //Boundary checker.
	    if(missile.getY() > RocketBoy.GAME_HEIGHT || missile.getY() <= 0)
	    	removeMe = true;
	    drawMissileSkin(missileAffine);
	   
	    if(turnTimer == Integer.MAX_VALUE - 1)
	    	turnTimer = 0;
	    
	    turnTimer++;
	}
	
	public void drawMissileSkin(Graphics2D g)
	{ 
		if(alarmMode > 0)
		{
			if(alarmMode > 25)
				g.drawImage(MISSILE_SHEETS[5], startX, 3, 45, 45, null);
			else
			{
				if(closeAlarmOneTime && panel.volumeOn)
				{
					SoundClips.play(SoundClips.BEEP, 0);
					closeAlarmOneTime = false;
				}
				g.drawImage(MISSILE_SHEETS[6], startX, 3, 45, 45, null);
			}
			alarmMode--;
		}
		else
		{
			if(spriteTimer == 0)
			{
				g.drawImage(MISSILE_SHEETS[4], (int) missile.getX(), (int) missile.getY() - 7, (int) missile.getWidth(), (int) missile.getHeight() + 10, null);
				spriteTimer = 1;
			}
			else if(spriteTimer == 1)
			{
				g.drawImage(MISSILE_SHEETS[3], (int) missile.getX(), (int) missile.getY() - 7, (int) missile.getWidth(), (int) missile.getHeight() + 10, null);
				spriteTimer = 2;
			}
			else if(spriteTimer == 2)
			{
				g.drawImage(MISSILE_SHEETS[2], (int) missile.getX(), (int) missile.getY() - 7, (int) missile.getWidth(), (int) missile.getHeight() + 10, null);
				spriteTimer = 3;
			}
			else if(spriteTimer == 3)
			{
				g.drawImage(MISSILE_SHEETS[1], (int) missile.getX(), (int) missile.getY() - 7, (int) missile.getWidth(), (int) missile.getHeight() + 10, null);
				spriteTimer = 4;
			}
			else if(spriteTimer == 4)
			{  
				g.drawImage(MISSILE_SHEETS[0], (int) missile.getX(), (int) missile.getY() - 7, (int) missile.getWidth(), (int) missile.getHeight() + 10, null);
				spriteTimer = 0;
			}
		}
		isImpeded = false;
		isImpact = false;
		hitByFlyBalloon = false;
	}
	
	public boolean collides(Rectangle characterObject) 
	{
		if(missile.intersects(characterObject))
		{
			return true;
		}
		return false;
	}
}
