package com.moopsup.rocketboy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.moopsup.rocketboy.RocketBoy.Layout;

class DeathScreen extends JPanel implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{  
	private static final long serialVersionUID = 1L;
	Layout layout;
	BufferedImage DEATH_SCREEN = null;
	TextField inputName; 
	float alpha = 0.20f;
	String name = null;
	int level;
	int score; 
	OutputStreamWriter out;
	Writer out1;
	String highscoresList = MainPanel.highscoresList;
	
	public DeathScreen(Layout layout)
	{
		setFocusable(true); addKeyListener(this); addMouseListener(this);
		addMouseMotionListener(this); addFocusListener(this);  
		this.layout = layout;
		try 
		{
			DEATH_SCREEN = ImageIO.read(RocketBoy.class.getResource("/Deathscreen.jpg"));
		} 
		catch (IOException e) {}
		
		try  
		{   
			//URL urlHighscoresData = new URL(getCodeBase(), highscoresList);
			//URLConnection connection = urlHighscoresData.openConnection();
		   // connection.setDoOutput(true);
		   // out = new OutputStreamWriter(connection.getOutputStream());
		    out1 = new FileWriter(new File(highscoresList), true);   
		} 
		catch (IOException e) {}
		
		inputName = new TextField("Press Enter when finished, no numbers please"); 
		inputName.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
		
		inputName.addKeyListener(this);  
		inputName.setEditable(true); 
		inputName.setVisible(true);
		add(inputName);
	}
	
	public void recieveData(int level, int score)
	{
		this.level = level;
		this.score = score;
	}
	
	public void paintComponent(Graphics g2)
	{  
		Graphics2D g = (Graphics2D) g2;
		g.setColor(new Color(142, 142, 142));
		g.fillRect(0, 0, RocketBoy.GAME_WIDTH, RocketBoy.GAME_HEIGHT);
		inputName.setLocation(340, 275); 
		g.drawImage(DEATH_SCREEN, 0, 0, RocketBoy.GAME_WIDTH, RocketBoy.GAME_HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		repaint();
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void focusGained(FocusEvent arg0) {}
	public void focusLost(FocusEvent arg0) {}
	public void keyPressed(KeyEvent arg0) 
	{
		int keyCode = arg0.getKeyCode();
		   
	    if(keyCode == KeyEvent.VK_ENTER)
	    {
	    	name = inputName.getText();
        	for(int i = 0; i < name.length(); i++)
        	{
        		if(name.charAt(i) == ' ' && i < name.length() - 1)
        		{
        			name = name.substring(0, i) + name.substring(i + 1);
        		}
        	}
        	
	        if(inputName.getText().length() > 9 || inputName.getText().length() < 1)
	        {	
	        	
	        }
	        else if(name.equals("\r\n"))
	        {
	        	name = "rn";
	        }
	        else
	        { 																						    
	        	try 
	        	{ 
	        		out1.write("\r\n" + name +  " " + score + " " + level);
				} catch (IOException e) {}
	        	try 
	        	{
	        		out1.close();
				} catch (IOException e) {}		
	        	layout.swapView("Start Menu");	        
	        }
	    }
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}