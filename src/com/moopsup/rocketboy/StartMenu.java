package com.moopsup.rocketboy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import javax.swing.JPanel;

import com.moopsup.rocketboy.RocketBoy.Layout;

class StartMenu extends JPanel implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	Layout layout;                                               
	ArrayList<StartBalloon> startBalloons = new ArrayList<StartBalloon>();
	Font fontHeader = new Font("Book Antiqua", Font.BOLD, 22);
	Font fontName = new Font("Book Antiqua", Font.PLAIN, 14);
	
	Font fontPlay = new Font("Book Antiqua", Font.PLAIN, 14);
	Font fontInstructions = new Font("Book Antiqua", Font.PLAIN, 14);
	Font fontHighScores = new Font("Book Antiqua", Font.PLAIN, 14);
	Font fontCredits = new Font("Book Antiqua", Font.PLAIN, 14);
	
	MainChar startChar;
	Platform onlyPlatform;
	
	ArrayList<Balloon> attachedBalloons = new ArrayList<Balloon>();
	ArrayList<Cloud> startClouds = new ArrayList<Cloud>();
	Random RND = new Random();
	int smallTimer;
	
	public StartMenu(Layout layout)
	{
		layout.init();
		smallTimer = 0;
		setFocusable(true); addKeyListener(this); addMouseListener(this);
		addMouseMotionListener(this); addFocusListener(this); 
		this.layout = layout;
		
		startChar = new MainChar(layout.images.CHARACTER_SPRITES, layout.images.SKULL_COUNTER);
		startChar.theCharacter.setLocation(300, 350);
		
		onlyPlatform = new Platform(layout.images.PLATFORM_ONE, layout.images.PLATFORM_SPIKE);
		onlyPlatform.platform.setLocation((int) startChar.theCharacter.getCenterX() - 80 , (int) startChar.theCharacter.getMaxY());
		onlyPlatform.underSpike.setLocation((int) onlyPlatform.platform.getX() + 6, (int) onlyPlatform.platform.getMaxY() - 1);
		
		//Add a sequence of balloons for a pretty start menu.
		for(int i = 0; i < 10; i++)
		{
			startBalloons.add(new StartBalloon());
		}
		for(int k = 0; k < 100; k++)
		{
			attachedBalloons.add(new Balloon());
		}
		for(int c = 0; c < 15; c++)
		{
			Cloud insert = new Cloud(layout.images.CLOUD_ONE, layout.images.CLOUD_TWO, 
					layout.images.CLOUD_THREE, layout.images.CLOUD_FOUR);
			startClouds.add(insert);
		}
	}
	
	public void paintComponent(Graphics g2)
	{   
		Graphics clone = g2.create();
		Graphics2D g = (Graphics2D) g2;  
		Graphics2D jetpackAffine = (Graphics2D) clone;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(layout.images.START_BG, 0, 0, RocketBoy.GAME_WIDTH, RocketBoy.GAME_HEIGHT, null);
		
		if(smallTimer % 60 == 0)
		{
			startChar.theCharacter.setLocation((int) (startChar.theCharacter.getX() + RND.nextInt(3) - 1),
					(int) (startChar.theCharacter.getY() + RND.nextInt(3) - 1));
		}
		
		for(int i = 0; i < startBalloons.size(); i++)
		{
			startBalloons.get(i).draw(g);
		}
		for(int k = 0; k < attachedBalloons.size(); k++)
		{
			attachedBalloons.get(k).draw(g, startChar.theCharacter);
		}
		for(int c = 0; c < 15; c++)
		{
			startClouds.get(c).draw(g);
		}
		
		smallTimer++;
		g.setColor(Color.WHITE);	
		
		g.setFont(fontHeader);
		g.drawImage(layout.images.ROCKET_LOGO, 375, 5, 300, 100, null);
		g.setFont(fontName);
		g.drawImage(layout.images.LUCAS_AUTHOR, 462, 85, 100, 33, null);
		//Fonts for the buttons, they become bold if moused over.
		g.setFont(fontPlay);
		g.drawString("Play", 480, 350);
		g.setFont(fontInstructions);
		g.drawString("Instructions", 480, 370);
		g.setFont(fontHighScores);
		g.drawString("Highscores", 480, 390);
		g.setFont(fontCredits);
		g.drawString("Credits", 480, 410);
		
		startChar.draw(g, jetpackAffine);
		onlyPlatform.draw(g);
		
		this.sleep();
		repaint();
	}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) 
	{
		int xCoord = arg0.getX();
		int yCoord = arg0.getY();
		//If it intersects Button One.
		if(xCoord >= 480 && xCoord <= 510 && yCoord >= 337 && yCoord <= 355)
		{
			fontPlay = new Font("Book Antiqua", Font.BOLD, 16);
		}
		else
		{
			fontPlay = new Font("Book Antiqua", Font.PLAIN, 14);
		}
		//Button 2.
		if(xCoord >= 480 && xCoord <= 545 && yCoord >= 356 && yCoord <= 370)
		{
			fontInstructions = new Font("Book Antiqua", Font.BOLD, 16);
		}
		else
		{
			fontInstructions = new Font("Book Antiqua", Font.PLAIN, 14);
		}
		//Button 3.
		if(xCoord >= 480 && xCoord <= 545 && yCoord >= 376 && yCoord <= 390)
		{
			fontHighScores = new Font("Book Antiqua", Font.BOLD, 16);
		}
		else
		{
			fontHighScores = new Font("Book Antiqua", Font.PLAIN, 14);
		}
		//Button 4.
		if(xCoord >= 480 && xCoord <= 525 && yCoord >= 395 && yCoord <= 410)
		{
			fontCredits = new Font("Book Antiqua", Font.BOLD, 16);
		}
		else
		{
			fontCredits = new Font("Book Antiqua", Font.PLAIN, 14);
		}
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) 
	{
		int xCoord = arg0.getX();
		int yCoord = arg0.getY();
		//If it intersects the Start Button.
		if(xCoord >= 480 && xCoord <= 510 && yCoord >= 337 && yCoord <= 355)
		{
			layout.swapView("The Game");
		}
		//Instructions
		if(xCoord >= 480 && xCoord <= 545 && yCoord >= 356 && yCoord <= 370)
		{
			layout.swapView("Instructions");
		}
		//High scores
		if(xCoord >= 480 && xCoord <= 545 && yCoord >= 376 && yCoord <= 390)
		{
			layout.swapView("Highscores");
		}
		//Add credits.
	}
	public void sleep()
	{
		try
		{
			Thread.sleep(25);
		}
		catch(Exception e){}
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void focusGained(FocusEvent e) {}
	public void focusLost(FocusEvent e) {}
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0){}
	public void keyTyped(KeyEvent arg0) {}
}