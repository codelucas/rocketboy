package com.moopsup.rocketboy;

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

import javax.swing.JPanel;

import com.moopsup.rocketboy.RocketBoy.Layout;

class Instructions extends JPanel implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	Layout layout;
	
	public Instructions(Layout layout)
	{
		setFocusable(true); addKeyListener(this); addMouseListener(this);
		addMouseMotionListener(this); addFocusListener(this); 
		this.layout = layout;
	}
	
	public void paintComponent(Graphics g2)
	{
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(layout.images.INSTRUCTIONS, 0, 0, RocketBoy.GAME_WIDTH, RocketBoy.GAME_HEIGHT, null);
		g.drawImage(layout.images.HOME_BUTTON, RocketBoy.GAME_WIDTH - 35, 0, 35, 35, null);
		repaint();
	}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) 
	{
		if(arg0.getX() >= RocketBoy.GAME_WIDTH - 35 && arg0.getY() <= 35)
		{ 
			layout.swapView("Start Menu");
		}
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void focusGained(FocusEvent arg0) {}
	public void focusLost(FocusEvent arg0) {}
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}