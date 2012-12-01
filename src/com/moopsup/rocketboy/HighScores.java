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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import com.moopsup.rocketboy.RocketBoy.Layout;

class HighScores extends JPanel implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	Layout layout;                                                                  
	int ylocator = 50;
	PriorityQueue mainQueue;
	ArrayList<StartBalloon> startBalloons = new ArrayList<StartBalloon>();
	BufferedReader reader = null;
	
	public HighScores(Layout layout)
	{
		setFocusable(true); addKeyListener(this); addMouseListener(this);
		addMouseMotionListener(this); addFocusListener(this); 
		
		this.layout = layout; 

		//Special sequence needed to read stuff from a file.
		InputStream in = HighScores.class.getResourceAsStream("/" + MainPanel.highscoresList);
		reader = new BufferedReader(new InputStreamReader(in));
		
		for(int i = 0; i < 10; i++)
		{
			startBalloons.add(new StartBalloon());
		}
		//Prepare to read the high scores file.
		try
		{
			refreshHighScores();	   
		}
		catch(Exception e){}
	}
	
	public void refreshHighScores() throws IOException
	{
		InputStream in = HighScores.class.getResourceAsStream("/" + MainPanel.highscoresList);
		reader = new BufferedReader(new InputStreamReader(in));
		try
		{
			mainQueue = parseFile(reader);
		}
		catch(Exception e){System.out.println("parse file issue...");}
		finally
		{
			reader.close();
		}
	}
	
	public void sleep()
	{
		try
		{
			Thread.sleep(25);
		}
		catch(Exception e){}
	}
	
	//Parse the high scores list.
	public PriorityQueue parseFile(BufferedReader reader) throws IOException                                         
	{  
		PriorityQueue returnQueue = new PriorityQueue();
		String line = reader.readLine();
		while(line != null) 
		{
			Scanner disect = new Scanner(line);
			String name = disect.next();
			int key = Integer.parseInt(disect.next()); 
			int level = Integer.parseInt(disect.next());
			returnQueue.insert(key, new ScoreEntry(name, key, level));
			line = reader.readLine();
		}
		return returnQueue;
	}
	
	public void paintComponent(Graphics g2)
	{   
		Graphics2D g = (Graphics2D) g2;  
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		
		g.drawImage(layout.images.HIGHSCORES, 0, 0, 1000, 625, null);
		
		for(int i = 0; i < startBalloons.size(); i++)
		{
			startBalloons.get(i).draw(g);
		}
		
		g.setColor(Color.WHITE);
		//The Title
		g.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		g.drawString("The Leaderboard", 420, 25);

		//Write the scores down
		g.setColor(Color.BLACK);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		
		int sharedY = ylocator; //Two Y values are needed to maintain ordering!
		
		while(!mainQueue.isEmpty())
		{ 																				
			ScoreEntry current = mainQueue.removeMax(); 
			g.drawString("Name: " + current.name, 320, sharedY);
			g.drawString("Score: " + current.score, 460, sharedY);
			g.drawString("Level: " + current.level, 600, sharedY);
			sharedY += 15;
		}
		//Refill the queue so our list actually remains on the applet.
		mainQueue.refill();                                                
		g.drawImage(layout.images.HOME_BUTTON, RocketBoy.GAME_WIDTH - 35, 0, 35, 35, null);
		this.sleep();
		repaint();	
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) 
	{
		if(arg0.getX() >= RocketBoy.GAME_WIDTH - 35 && arg0.getY() <= 35)
		{ 
			layout.swapView("Start Menu");
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void focusGained(FocusEvent arg0) {}
	public void focusLost(FocusEvent arg0) {}
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}