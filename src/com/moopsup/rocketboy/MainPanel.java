package com.moopsup.rocketboy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.JPanel;

import com.moopsup.rocketboy.RocketBoy.Layout;

class MainPanel extends JPanel implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{		
	private static final long serialVersionUID = 1L;
	Layout layout; 
	//MAIN GAME VARIABLES
	static Rectangle ground = new Rectangle(0, RocketBoy.GAME_HEIGHT, RocketBoy.GAME_WIDTH, 400);
	static boolean gameOver = false;
	
	Random RND = new Random();
	
	//For the fade effect.
	float alpha = 0.0f;
	
	static int addBalloonSequence = 0;
	
	static final int LOWEST_ALTITUDE_POSSIBLE = -410;
	static final int DAMAGE_PER_MISSILE = -12;
	static final int DAMAGE_PER_SPIKE = -15;
	static final int EVENT_DURATION = 7;
	static final int FORCEFIELD_SAFE_TIMER = 75;
	static final int PLATFORM_SPIKE_DAMAGE = 2;
	static final int HEALTH_BOOST = 50; //Restores half hp.
	static final int HELIUM_BOOST = 80; //Restores a third of the needed helium.
	static final int ALTITUDE_DROP_FOR_BALLOON_RELEASE = 60;
	static final int FREEFALL_INCREMENT = 10;
 	
	//Physics related fields.
	static int vertVelocity = 0; 
	static int horVelocity = 0; 
	static int time = 0;
	static int altitude = 0;
	static int pointValueAltitude = 0;
	static int levelCounter = 1;
	static final int BOOST_SPEED = 15;
	//These two are fodder variables for when someone releases a balloons, 
	//we just simulate a quick drop in altitude.
	static int destinationAltitude = Integer.MAX_VALUE; //This is a sentinel value
	
	//Our main char object for the game
	static MainChar gameChar;
	static int charWidthRandom = 0;
	static int charHeightRandom = 0;
	static boolean[] GAME_MOVES;
	
	//Balloon Fields.
	static ArrayList <Balloon> balloons = new ArrayList<Balloon>();
	static ArrayList <Balloon> flyBalloons = new ArrayList<Balloon>();
	static ArrayList <Cloud> clouds = new ArrayList<Cloud>();
	static boolean addBalloon = false;
	static boolean removeBalloon = false;
	int balloonXCoord, balloonYCoord;
    boolean blowingBalloon = false;
    
    boolean volumeOn = true;

	//Enemy obstacle Fields.
	static ArrayList<EnemyObject> enemyObjects = new ArrayList<EnemyObject>();
	
	//The highscores
	static final String highscoresList = "HIGHSCORES.txt";
	
	//Upgrade Fields.
	static ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();                                
	
	//Platforms
	static ArrayList<GeneralPlatform> platforms = new ArrayList<GeneralPlatform>();
	//This is a very crucial variable which will be incremented every 300 altitudes,
	//the !!number and frequency!! of platforms is essentially based off of this.
	static int platNumberFactor = 1;
	static int platDistanceFactor = 150;
	boolean splatOnce = true;
	
	DeathScreen deathScreen;
	
	public MainPanel(Layout layout, DeathScreen deathScreen)
	{  
		setFocusable(true); addKeyListener(this); addMouseListener(this);
		addMouseMotionListener(this); addFocusListener(this);  
					
		gameChar = new MainChar(layout.images.CHARACTER_SPRITES, layout.images.SKULL_COUNTER);
		
		for(int c = 0; c < 15; c++)
		{
			clouds.add(new Cloud(layout.images.CLOUD_ONE, layout.images.CLOUD_TWO, 
					layout.images.CLOUD_THREE, layout.images.CLOUD_THREE));
		}
		//Add the first missile.
		enemyObjects.add(new Missile(layout.images.MISSILE_SHEETS, this));
		
		BalloonCrate firstCrate = new BalloonCrate(layout.images.BALLOON_CRATE);
		firstCrate.yCoord = (int) ground.getMinY() - 150;
		upgrades.add(firstCrate);
		GAME_MOVES = new boolean[4]; 
		for(int i = 0; i < 4; i++)
			GAME_MOVES[i] = false;
		GAME_MOVES[0] = true; //Default jetpack upwards true.
		
		this.layout = layout;
		this.deathScreen = deathScreen;
	}
	
	public void paintComponent(Graphics g2)
	{   
		//Clone is used for separate affine objects.
		Graphics clone = g2.create();
		Graphics clone2 = g2.create();
		
		Graphics2D g = (Graphics2D) g2;
		Graphics2D jetpackAffine = (Graphics2D) clone;
		Graphics2D missileAffine = (Graphics2D) clone2;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.handleKeys();
		this.updateSetting(g);
		this.updatePhysics();
		this.updateCollisions();
		this.drawMain(g, jetpackAffine, missileAffine);	
		this.sleep();
		if(!volumeOn && layout.sequencer.isRunning())
		{
			layout.sequencer.stop();
		}
		if(volumeOn && !layout.sequencer.isRunning())
		{
			try 
			{
				layout.sequencer.setSequence(layout.SORAIRO_DAYS);
			} catch (InvalidMidiDataException e) 
			{
				e.printStackTrace();
			}
			layout.sequencer.start();
		}
		this.repaint();
	}
	
	public void handleKeys()
	{
		if(GAME_MOVES[0])
		{
			gameChar.boostTimer = 0;
			for(int h = 0; h < balloons.size(); h++)
			{
				balloons.get(h).boostTime = 0;
			}
		}
		else if(gameChar.boostBar.fillCounter >= 10)
		{
			gameChar.boostTimer = gameChar.boostBar.fillCounter;
			for(int h = 0; h < balloons.size(); h++)
			{
				balloons.get(h).boostTime = gameChar.boostBar.fillCounter;
			}
			if(volumeOn)
				SoundClips.play(SoundClips.IGNITE, 0);
		}
		
		if(GAME_MOVES[1])
			gameChar.setDirection("RIGHT");
		
		if(GAME_MOVES[2])
			removeBalloon = true;
		
		else
			removeBalloon = false;
		
		if(GAME_MOVES[3])
			gameChar.setDirection("LEFT");
		
		if(!GAME_MOVES[3] && !GAME_MOVES[1])
			gameChar.setDirection("NONE");
	}
	
	public void updateSetting(Graphics2D g)
	{
		//Check if you lost!
		if(gameChar.healthBar.fillCounter <= 0 || gameOver)
		{
			if(splatOnce && volumeOn)
			{
				SoundClips.play(SoundClips.SPLAT, 0);
				splatOnce = false;
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g.fillRect(0, 0, 1000, 625);
			
		    //Increase the opacity and repaint
		    alpha += 0.005f;
		    if (alpha >= 0.20f) 
		    {
		        alpha = 0.20f;
		        //When the fading is done, bring us to the Death Screen.
		        layout.swapView("DeathScreen");	
		        deathScreen.recieveData(levelCounter, pointValueAltitude); 
		        reset();
		    } 
		    else 
		        repaint();
		}
		
		if(altitude < 2500)	
			levelCounter = 1;
		else if(altitude < 5000)	
			levelCounter = 2;
		else if(altitude < 8000)	
			levelCounter = 3;
		else if(altitude < 13000)
			levelCounter = 4;
		else if(altitude < 15000)
			levelCounter = 5;
		else if(altitude < 18000)
			levelCounter = 6;
		else if(altitude < 21000)
			levelCounter = 7;
		else if(altitude < 24000)
			levelCounter = 8;
		else if(altitude < 26000)
			levelCounter = 9;
													  
		//Handle the missile entrance/leaves.      																	
		if(time % 50 == 0 && altitude > 0)
		{
			enemyObjects.add(new Missile(layout.images.MISSILE_SHEETS, this));	
		}
//		if(time % 500 == 0 && altitude > 0)
//		{
//			enemyObjects.add(new Spike(SPIKE));	
//		}
		if(time % 200 == 0 && altitude > 0)
		{
			upgrades.add(new Coin());
		}
		//Handle the upgrade entrances and leaves.
		if(time % 350 == 0)
		{
			upgrades.add(new RandomUpGrade(layout.images.QUESTION_UPGRADE));
		}
		
		//Remove any stray upgrades (coins, platforms, upgrades).
		if(upgrades.size() > 0)
		{
			for(int i = 0; i < upgrades.size(); i++)
			{
				if(upgrades.get(i).isPresent == false)
				{ 
					upgrades.remove(i);
				}
			}
		}  
		//Remove unnecessary enemies
		if(enemyObjects.size() > 0)
		{
			for(int i = 0; i < enemyObjects.size(); i++)
			{
				if(enemyObjects.get(i).removeMe == true)
				{ 
					enemyObjects.remove(i);
				}
			}
		}  
		//Remove stray platforms
		if(platforms.size() > 0)
		{
			for(int i = 0; i < platforms.size(); i++)
			{
				if(platforms.get(i).removeMe == true)
				{ 
					platforms.remove(i);
				}
			}
		}  
		//The platform spacing algorithm, they will be spaced randomly from 150-300 units away from each other, as we go higher
		//we multiply the spacing by a factor corresponding to the altitude. 
		//"platDistanceFactor" begins at 150, and it keeps growing as the levels get more difficult.
		if(altitude >= ((RND.nextInt(150) + platDistanceFactor) * platNumberFactor) && altitude != 0)
		{
			if(time % 2 == 0)
			{
				upgrades.add(new BalloonCrate(layout.images.BALLOON_CRATE));
			}
			
			platforms.add(new Platform(layout.images.PLATFORM_ONE, layout.images.PLATFORM_SPIKE, 
					layout.images.GASPUMP_FILLED));
			platNumberFactor++;
			platDistanceFactor = platDistanceFactor + 3; //We increment the distance per factor by three.
		}
	}
	
	//Method whose sole purpose is to re-update the main three physics variables
	//of this program, altitude, vertical velocity and horizontal velocity.
	public void updatePhysics()
	{
		//Update the char X position based off of WASD keys.
		gameChar.updateXAxis();																
		
		//BALLOON MANAGEMENT SECTION.
		
		charWidthRandom = RND.nextInt((int) gameChar.theCharacter.getWidth()) + (int) gameChar.theCharacter.getMinX(); 
		charHeightRandom = RND.nextInt((int) gameChar.theCharacter.getHeight()) + (int) gameChar.theCharacter.getMinY(); 
		
		if((addBalloonSequence > 0) && balloons.size() < 250
				&& (time % 4 == 0) && gameChar.balloonCount > 0)
		{	
			gameChar.rocketsOn = true;
			gameChar.wheelSpinSpeed = 30;
			balloons.add(new Balloon(charWidthRandom, charHeightRandom));
			gameChar.balloonCount--;
			addBalloonSequence--;
		}
		else if(addBalloonSequence == 0)
		{
			gameChar.rocketsOn = false;
			gameChar.wheelSpinSpeed = 5;
		}
		
		if((removeBalloon == true) && (balloons.size() > 0) && (time % 4 == 0))
		{
			//Insert a temporary drop in velocity for every balloon released (approximately 30 altitude?)
			if(altitude > 60)
			{
				destinationAltitude = altitude - ALTITUDE_DROP_FOR_BALLOON_RELEASE;
			}
			else
			{
				destinationAltitude = 0;
			}
			//Remove the used balloon and toss it into the flying balloon "list".
			Balloon currentBalloon = balloons.get(balloons.size() - 1);
			currentBalloon.flyAway = true;
			flyBalloons.add(balloons.remove(balloons.size() - 1));	
		}
		
		//VECTOR/PHYSICS MANAGEMENT SECTION          *********************************
														
		if(altitude > destinationAltitude && !gameChar.isHittingPlatform && gameChar.boostTimer <= 0)	
		{
			vertVelocity = 5;
		}
		
		else
		{
			if(altitude == destinationAltitude)
			{
				//Max value is kind of a sentinel.
				destinationAltitude = Integer.MAX_VALUE; 
			}
			
			//The balloon/velocity algorithm
			int adjustedSpeed = (int) ((balloons.size() - 5) * 2);
		
			//35 is the maximum vertical velocity
			if(adjustedSpeed > 25)
			{	
				adjustedSpeed = 25;
			}
			
			//If the boost is on
			if(gameChar.boostTimer > 0)
			{	
				vertVelocity = -BOOST_SPEED;
			}
			//Drop the char quickly if there are no balloons and were not on a plat.
			else if(balloons.size() == 0 && altitude > LOWEST_ALTITUDE_POSSIBLE && !gameChar.isHittingPlatform) 
			{	
				if(time % 5 == 0)
					vertVelocity += FREEFALL_INCREMENT;
			}
			//If we are on any platform, the vert velocity is zero. 
			else if(gameChar.isHittingPlatform && gameChar.boostTimer <= 0)
			{   
				vertVelocity = 0; 
				destinationAltitude = Integer.MAX_VALUE; 
			}
			else if(vertVelocity >= 40 && addBalloonSequence == 0)
			{
				//Do nothing, maintain the already high velocity.
			}
			//The "normal" vert velocity would just be the negative adj speed.
			else
			{
				vertVelocity = (int) (-adjustedSpeed);
			}	
		}  		
		
		altitude += -vertVelocity;
		//We want the point value to not go down if they drop.
		//System.out.println(pointValueAltitude);
		if(vertVelocity < 0)
			pointValueAltitude += -vertVelocity;
		
		//One click only means add a balloon once, but if the mouse is held down,
		//the balloon's size is increased with helium 
		//addBalloon = false;
		//removeBalloon = false;
		if(time == Integer.MAX_VALUE - 1)
		{
			time = 0;
		}
		time++;
	}

	//Main draw method which calls upon drawText() for drawing text and the image library draw methods for the main drawing.
	public void drawMain(Graphics2D g, Graphics2D jetpackAffine, Graphics2D missileAffine)
	{	
		g.drawImage(layout.images.SKY, 0, 0, RocketBoy.GAME_WIDTH, RocketBoy.GAME_HEIGHT, null);
		//If the char hits the edges of the Panel.
		//TO BE CODED...
		ImageLibary libary = new ImageLibary(layout.images.TREE, layout.images.BARN, layout.images.GRASS);
		ImageLibary.drawClouds(g, clouds, vertVelocity);
		libary.drawGroundAndSkyscrapers(g, ground);
		ImageLibary.drawThePlatforms(g, platforms);
		ImageLibary.drawBalloons(g, balloons, flyBalloons, gameChar.theCharacter);
		ImageLibary.drawEnemyObjects(g, missileAffine, enemyObjects);
		ImageLibary.drawUpgrades(g, upgrades);
		ImageLibary.drawChar(gameChar, g, jetpackAffine);  
		ImageLibary.drawBoostBar(g, gameChar.boostBar);
		ImageLibary.drawHealthBar(g, gameChar.healthBar);
		drawText(g);
		g.drawImage(layout.images.HOME_BUTTON, RocketBoy.GAME_WIDTH - 35, 0, 35, 35, null);
		if(volumeOn)
			g.drawImage(layout.images.VOLUME_BUTTONS[0], RocketBoy.GAME_WIDTH - 30, RocketBoy.GAME_HEIGHT - 30, 30, 30, null);
		else
			g.drawImage(layout.images.VOLUME_BUTTONS[1], RocketBoy.GAME_WIDTH - 30, RocketBoy.GAME_HEIGHT - 30, 30, 30, null);
			
	}
	
	public void updateCollisions()
	{	
		//char collisions
		//Push the ground/barn down with the velocity (not the char!).
		ground.setLocation((int)ground.getX(), (int) ground.getY() - vertVelocity);

		if(gameChar.theCharacter.intersects(ground) && vertVelocity >= 40)
		{
			gameOver = true;
		}
		//Collision with ground.
		if(gameChar.theCharacter.intersects(ground) && vertVelocity > 0)
		{ 
		    ground.setLocation((int)ground.getX(), (int) gameChar.theCharacter.getMaxY() - 10);
			vertVelocity = 0;
			altitude = (int) -ground.getHeight();
		}
		//ALL UPGRADE COLLISIONS
		for(int u = 0; u < upgrades.size(); u++)
		{
			if(upgrades.get(u).collides(gameChar.theCharacter))
			{
				if(upgrades.get(u) instanceof Coin)
				{
					gameChar.coinCount++;
					gameChar.hitCoin = EVENT_DURATION; 
				}
				else if(upgrades.get(u) instanceof RandomUpGrade)
				{
					RandomUpGrade temp = (RandomUpGrade) upgrades.get(u);
					//If this is a forcefield.
					if(temp.whatIsThis == 0)
					{
						gameChar.safeTimer = FORCEFIELD_SAFE_TIMER;
					}
					//If this is a health bar thing.
					else if(temp.whatIsThis == 1)
					{
						gameChar.healthBar.fillCounter += HEALTH_BOOST;
						gameChar.healthBoosted = EVENT_DURATION;
					}
				}
				//If this is a balloon crate
				else if(upgrades.get(u) instanceof BalloonCrate)
				{
					BalloonCrate crate = (BalloonCrate) upgrades.get(u);
					gameChar.balloonCount += crate.numberOfBalloonsInCrate;
					addBalloonSequence = crate.numberOfBalloonsInCrate;
					//Play our inflate sound!
					if(volumeOn)
						SoundClips.play(SoundClips.INFLATE, 0);
				}
				upgrades.remove(u);
			}
		}
		//PLATFORM COLLISIONS
		int quickPlatCount = 0;
		for(int p = 0; p < platforms.size(); p++)
		{
			if(platforms.get(p).landsOn(gameChar.theCharacter)) 
			//Remember, a negative velocity means going up...
			{   
				gameChar.isHittingPlatform = true;
				quickPlatCount++;  
			}
			//Complicated collision shit, to make things short, its hard to make two x y points collide property
			//when the god dam vertical velocity is fucking huge, so i had to custom make the velocity smaller
			//before landing to make it look "cushioned", but the real intention was to make smooth colision handling. 
			if(gameChar.theCharacter.getMaxY() > platforms.get(p).platform.getMinY() - 50 && gameChar.theCharacter.getMaxY() < 
					platforms.get(p).platform.getMinY() + 3 && gameChar.boostTimer <= 0 && addBalloonSequence <= 0 &&
							gameChar.theCharacter.getMinX() >= platforms.get(p).platform.getMinX() - 10 && gameChar.theCharacter.getMaxX() 
					<= platforms.get(p).platform.getMaxX() + 15)
			{
				vertVelocity = 5;
			}
			
			//If a platform spike hits the char or balloons.
			//if(gameChar.theCharacter.getMinY() <= platforms.get(p).underSpike.getMaxY())
			if(platforms.get(p).underSpike.intersects(gameChar.theCharacter) && vertVelocity < 0)
			{
				gameChar.healthBar.fillCounter = gameChar.healthBar.fillCounter - PLATFORM_SPIKE_DAMAGE; 
				gameChar.isHit = EVENT_DURATION;
				vertVelocity = 0;
			}
			
			if(gameChar.theCharacter.intersects(platforms.get(p).gasStation))
			{
				if(gameChar.boostBar.fillCounter <= 100)
				{
					gameChar.boostBar.fillCounter++;
					gameChar.gettingGas = true;
					gameChar.wheelSpinSpeed = 40;
				}
			}
		
			for(int b = 0; b < balloons.size(); b++)
			{
				if(balloons.get(b).balloon.intersects(platforms.get(p).underSpike))
				{
					balloons.get(b).flyAway = true;
					flyBalloons.add(balloons.remove(b));
				}
			}
		} 
		//If we detect that were standing on no platform, were off!
		if(quickPlatCount == 0)
		{
			gameChar.isHittingPlatform = false;
		}
		
		//ALL ENEMY OBJECT COLLISIONS
		for(int e = 0; e < enemyObjects.size(); e++)
		{
			//On shield
			if(enemyObjects.get(e).collides(gameChar.shield)) //Fix this one
			{
				if(enemyObjects.get(e) instanceof Missile)
				{
					enemyObjects.get(e).isImpeded = true;
				}
				else if(enemyObjects.get(e) instanceof Spike)
				{
					enemyObjects.get(e).isImpact = true;
				}
			}
			//On balloons
			for(int bIndex = 0; bIndex < balloons.size(); bIndex++)
			{
				if(enemyObjects.get(e).collides(balloons.get(bIndex).getBalloon()))
				{
					if(enemyObjects.get(e) instanceof Missile)
					{
						balloons.get(bIndex).flyAway = true;
						balloons.get(bIndex).gotHit = true;
						flyBalloons.add(balloons.remove(bIndex));
						enemyObjects.get(e).isImpact = true; 
					}
					
					else if(enemyObjects.get(e) instanceof Spike)
					{
						balloons.get(bIndex).flyAway = true;
						balloons.get(bIndex).gotHit = true;	
						flyBalloons.add(balloons.remove(bIndex));
					}
				}
			}
			
			//On flyBalloons.
			for(int fIndex = 0; fIndex < flyBalloons.size(); fIndex++)
			{ 
				if(enemyObjects.get(e).collides(flyBalloons.get(fIndex).balloon)
						&& flyBalloons.get(fIndex).gotHit == false)
				{
					if(enemyObjects.get(e) instanceof Missile)
					{
						enemyObjects.get(e).hitByFlyBalloon = true;
					}
					//Spikes don't react to fly balloons.
					else if(enemyObjects.get(e) instanceof Spike)
					{
						enemyObjects.get(e).hitByFlyBalloon = true;
					}
				}
			}
			
			//On char
			if(enemyObjects.get(e).collides(gameChar.theCharacter))
			{	if(volumeOn)
					SoundClips.play(SoundClips.JAB, 0);
			
				if(enemyObjects.get(e) instanceof Missile)
				{
					enemyObjects.remove(e);
					gameChar.healthBar.fillCounter += DAMAGE_PER_MISSILE;
					enemyObjects.add(new Missile(layout.images.MISSILE_SHEETS, this));			
					gameChar.isHit = EVENT_DURATION;
				}
				else if(enemyObjects.get(e) instanceof Spike)
				{
					gameChar.isHit = EVENT_DURATION;
					gameChar.healthBar.fillCounter += DAMAGE_PER_SPIKE;	
					enemyObjects.remove(e);
				}
			}
		}
	}
	
	public void drawText(Graphics2D g)
	{
		g.setColor(new Color(139, 28, 98, 128));
		//The points display based off of altitude.
		g.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		g.drawString("Points: " + pointValueAltitude, 10, 20); 
		g.drawString("Vertical Velocity: " + -vertVelocity, 10, 40);
		g.setColor(new Color(139, 28, 98, 80));
		g.drawString("L I F E", 220, 20);
		g.drawString("B O O S T", 358, 20);
		//g.drawString("H E L I U M", 690, 20);
		g.setColor(new Color(238, 64, 0, 210));
		g.drawString("LEVEL# " + levelCounter, 480, 18);
		
	}
	//Method to reset the game when reset is pressed or when you die.
	public void reset()
	{
		splatOnce = true;
		pointValueAltitude = 0;
		addBalloonSequence = 0;
		platDistanceFactor = 150;
		platNumberFactor = 1;
		gameOver = false;
		ground = new Rectangle(0, RocketBoy.GAME_HEIGHT, RocketBoy.GAME_WIDTH, 400);
		balloons.clear();
		flyBalloons.clear();
		enemyObjects.clear();
		altitude = 0;
		levelCounter = 1;
		time = 0;	
		vertVelocity = 0; 
		horVelocity = 0; 
		upgrades.clear();
		alpha = 0.0f;
		gameChar.resetEverything();
		destinationAltitude = Integer.MAX_VALUE;
		platforms.clear();
		
		//Every new "restart" does NOT generate a new game object, we need to
		//regenerate a lot of old stuff.
		
		//Add the first missile.
		enemyObjects.add(new Missile(layout.images.MISSILE_SHEETS, this));
		//Add the first platform
		platforms.add(new Platform(layout.images.PLATFORM_ONE, 
				layout.images.PLATFORM_SPIKE, layout.images.GASPUMP_FILLED));
		//Add the initial crate.
		BalloonCrate firstCrate = new BalloonCrate(layout.images.BALLOON_CRATE);
		firstCrate.yCoord = (int) ground.getMinY() - 150;
		upgrades.add(firstCrate);
	}
	
	public void sleep()
	{
		try
		{
			Thread.sleep(25);
		}
		catch(Exception e){}
	}
	//The event handlers.
	public void keyPressed(KeyEvent e)
	{	
	    int keyCode = e.getKeyCode();
	    switch(keyCode) 
	    { 
			case KeyEvent.VK_A:
				GAME_MOVES[3] = true;
				break;
			case KeyEvent.VK_D:
				GAME_MOVES[1] = true;
				break;
			case KeyEvent.VK_W:
				GAME_MOVES[0] = !GAME_MOVES[0];
				break;
			case KeyEvent.VK_S:
				GAME_MOVES[2] = true;
				break;	
	     }
	}
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode) 
		{ 
			case KeyEvent.VK_A:
				GAME_MOVES[3] = false;
				break;
			case KeyEvent.VK_D:
				GAME_MOVES[1] = false;
				break;
			case KeyEvent.VK_S:
				GAME_MOVES[2] = false;
				break;	
		}	
	}
	public void keyTyped(KeyEvent e){}	
	public void focusGained(FocusEvent e){}
	public void focusLost(FocusEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getX() >= RocketBoy.GAME_WIDTH - 35 && e.getY() <= 35)
		{ 
			reset();
			layout.swapView("Start Menu");
		}
		else if(e.getX() >= RocketBoy.GAME_WIDTH - 30 && e.getY() >= RocketBoy.GAME_HEIGHT - 30)
		{
			volumeOn = !volumeOn;
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}	