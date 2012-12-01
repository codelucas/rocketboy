package com.moopsup.rocketboy;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Images 
{
	//MainPanel
	BufferedImage HOME_BUTTON = null;
	BufferedImage SKY = null;
	BufferedImage SKULL_COUNTER = null;
	BufferedImage CANNONBALL = null;
	BufferedImage CLOUD_ONE = null;
	BufferedImage CLOUD_TWO = null;
	BufferedImage CLOUD_THREE = null;
	BufferedImage CLOUD_FOUR = null;
	BufferedImage TREE = null;
	BufferedImage BARN = null;
	BufferedImage GRASS = null;
    BufferedImage QUESTION_UPGRADE = null;
    BufferedImage SPIKE = null;
	BufferedImage PLATFORM_ONE = null;
	BufferedImage PLATFORM_SPIKE = null;
	BufferedImage BALLOON_CRATE = null;
	BufferedImage GASPUMP_FILLED = null;
	
	BufferedImage[] VOLUME_BUTTONS = new BufferedImage[2];
	
	BufferedImage[] MISSILE_SHEETS = new BufferedImage[7];
	BufferedImage[] CHARACTER_SPRITES = new BufferedImage[17];
	
	//Instructions
	BufferedImage INSTRUCTIONS = null;
	
	//StartMenu
	BufferedImage START_BG =  null;
	BufferedImage ROCKET_LOGO = null;
	BufferedImage LUCAS_AUTHOR = null;
	
	//Highscores
	BufferedImage HIGHSCORES = null;
	
	public void loadAll() throws IOException
	{
		//MainPanel
		GASPUMP_FILLED = ImageIO.read(RocketBoy.class.getResource("/gasPumpFilled.gif"));
		SPIKE = ImageIO.read(RocketBoy.class.getResource("/Spike.gif"));
		QUESTION_UPGRADE = ImageIO.read(RocketBoy.class.getResource("/QuestionUpgrade.gif"));
		TREE = ImageIO.read(RocketBoy.class.getResource("/Tree.gif"));
		BARN = ImageIO.read(RocketBoy.class.getResource("/Barn.gif"));
		GRASS = ImageIO.read(RocketBoy.class.getResource("/Grass.gif"));
		HOME_BUTTON = ImageIO.read(RocketBoy.class.getResource("/HomeButton.gif"));
		SKY = ImageIO.read(RocketBoy.class.getResource("/Sky.gif"));
		BALLOON_CRATE = ImageIO.read(RocketBoy.class.getResource("/balloonCrate.png"));
		CLOUD_ONE = ImageIO.read(RocketBoy.class.getResource("/CloudOne.gif"));
		CLOUD_TWO = ImageIO.read(RocketBoy.class.getResource("/CloudTwo.gif"));
		CLOUD_THREE = ImageIO.read(RocketBoy.class.getResource("/CloudThree.gif"));
		CLOUD_FOUR = ImageIO.read(RocketBoy.class.getResource("/CloudFour.gif"));
		
		SKULL_COUNTER = ImageIO.read(RocketBoy.class.getResource("/skullCounter.png"));
		
		BufferedImage MISSILE_ONE = ImageIO.read(RocketBoy.class.getResource("/missileOne.png"));
		BufferedImage MISSILE_TWO = ImageIO.read(RocketBoy.class.getResource("/missileTwo.png"));
		BufferedImage MISSILE_THREE = ImageIO.read(RocketBoy.class.getResource("/missileThree.png"));
		BufferedImage MISSILE_FOUR = ImageIO.read(RocketBoy.class.getResource("/missileFour.png"));
		BufferedImage MISSILE_FIVE = ImageIO.read(RocketBoy.class.getResource("/missileFive.png"));
		BufferedImage ALARM_PIC = ImageIO.read(RocketBoy.class.getResource("/missileWarning.png"));
		BufferedImage MISSILE_CLOSE = ImageIO.read(RocketBoy.class.getResource("/missileCloseWarning.png"));
		
		MISSILE_SHEETS[0] = MISSILE_ONE; MISSILE_SHEETS[1] = MISSILE_TWO; MISSILE_SHEETS[2] = MISSILE_THREE;
		MISSILE_SHEETS[3] = MISSILE_FOUR; MISSILE_SHEETS[4] = MISSILE_FIVE; MISSILE_SHEETS[5] = ALARM_PIC;
		MISSILE_SHEETS[6] = MISSILE_CLOSE;

		BufferedImage RIGHT_ONE = ImageIO.read(RocketBoy.class.getResource("/rightOne.png"));
		BufferedImage RIGHT_TWO = ImageIO.read(RocketBoy.class.getResource("/rightTwo.png"));
		BufferedImage RIGHT_THREE = ImageIO.read(RocketBoy.class.getResource("/rightThree.png"));
		BufferedImage RIGHT_FOUR = ImageIO.read(RocketBoy.class.getResource("/rightFour.png"));
		BufferedImage RIGHT_UP = ImageIO.read(RocketBoy.class.getResource("/rightUp.gif"));
		BufferedImage RIGHT_JETPACK = ImageIO.read(RocketBoy.class.getResource("/jetpackRight.gif"));
		
		BufferedImage LEFT_ONE = ImageIO.read(RocketBoy.class.getResource("/leftOne.png"));
		BufferedImage LEFT_TWO = ImageIO.read(RocketBoy.class.getResource("/leftTwo.png"));
		BufferedImage LEFT_THREE = ImageIO.read(RocketBoy.class.getResource("/leftThree.png"));
		BufferedImage LEFT_FOUR = ImageIO.read(RocketBoy.class.getResource("/leftFour.png"));
		BufferedImage LEFT_UP = ImageIO.read(RocketBoy.class.getResource("/leftUp.gif"));
		BufferedImage LEFT_JETPACK = ImageIO.read(RocketBoy.class.getResource("/jetpackLeft.gif"));
		
		BufferedImage CHAR_DEAD = ImageIO.read(RocketBoy.class.getResource("/charDead.png"));
		
		BufferedImage FLAME_ONE = ImageIO.read(RocketBoy.class.getResource("/flameOne.gif"));
		BufferedImage FLAME_TWO = ImageIO.read(RocketBoy.class.getResource("/flameTwo.gif"));
		BufferedImage FLAME_THREE = ImageIO.read(RocketBoy.class.getResource("/flameThree.gif"));
		BufferedImage FLAME_FOUR = ImageIO.read(RocketBoy.class.getResource("/flameFour.gif"));
		
		PLATFORM_ONE = ImageIO.read(RocketBoy.class.getResource("/platformOne.png"));
		PLATFORM_SPIKE = ImageIO.read(RocketBoy.class.getResource("/platformSpike.png"));
		
		CHARACTER_SPRITES[0] = RIGHT_ONE; CHARACTER_SPRITES[1] = RIGHT_TWO; CHARACTER_SPRITES[2] = RIGHT_THREE;
		CHARACTER_SPRITES[3] = RIGHT_FOUR; CHARACTER_SPRITES[4] = LEFT_ONE; CHARACTER_SPRITES[5] = LEFT_TWO;
		CHARACTER_SPRITES[6] = LEFT_THREE; CHARACTER_SPRITES[7] = LEFT_FOUR; CHARACTER_SPRITES[8] = RIGHT_UP;
		CHARACTER_SPRITES[9] = LEFT_UP; CHARACTER_SPRITES[10] = RIGHT_JETPACK; CHARACTER_SPRITES[11] = LEFT_JETPACK;
		CHARACTER_SPRITES[12] = FLAME_ONE;  CHARACTER_SPRITES[13] = FLAME_TWO;  CHARACTER_SPRITES[14] = FLAME_THREE; 
		CHARACTER_SPRITES[15] = FLAME_FOUR; CHARACTER_SPRITES[16] = CHAR_DEAD;    
		
		VOLUME_BUTTONS[0] = ImageIO.read(RocketBoy.class.getResource("/volumeOn.png"));
		VOLUME_BUTTONS[1] = ImageIO.read(RocketBoy.class.getResource("/volumeOff.png"));
		
		//Instructions
		INSTRUCTIONS = ImageIO.read(RocketBoy.class.getResource("/Instructions.jpg"));
		
		//StartMenu
		START_BG = ImageIO.read(RocketBoy.class.getResource("/IntroPic.jpg"));
		ROCKET_LOGO = ImageIO.read(RocketBoy.class.getResource("/RocketBoyLogo.png"));
		LUCAS_AUTHOR = ImageIO.read(RocketBoy.class.getResource("/lucasAuthorText.png"));
		
		//Highscores
		//NOTE: This is the pic, the txt HS file is imported.
		HIGHSCORES = ImageIO.read(RocketBoy.class.getResource("/Highscores.png"));
	}
}
