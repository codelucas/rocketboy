package com.moopsup.rocketboy;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

public class RocketBoy extends JApplet
{
	/**
	 * Designed and implemented by Lucas Ou-Yang, this was
	 * my first game i've ever developed and it was a blast
	 * making it. TODO: Add more emotional/logistical stuff later.
	 */
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = 625;
	
	public void init()
	{	
		setSize(GAME_WIDTH, GAME_HEIGHT);
		Layout topContainer = new Layout();
		setContentPane(topContainer); 
	}
	
	class Layout extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		//Main Class holding all images, passed through this card layout.
		//THIS MUST be loaded before component initialization!
		public Images images = new Images();
		
		//Create instances of our panels, layouts are passed as parameters to all states.
		//NOTE: THe startmenu constructor calls init() in this class, which opens the pictures!
		final StartMenu startMenu = new StartMenu(this);
		final DeathScreen deathScreen = new DeathScreen(this);
		final MainPanel mainGame  = new MainPanel(this, deathScreen);
		final Instructions instructions = new Instructions(this);
		final HighScores highscores = new HighScores(this); 
		
	    //Music Fields.
	    Sequence SORAIRO_DAYS;
	    Sequencer sequencer;

	    String previousScreen = "Start";
	    
	    CardLayout myLayout = new CardLayout();
	    
	    public void init()
	    {
			try
			{
				images.loadAll();
			}catch(IOException e){}
	    }
	    
	    public Layout()
	    { 	
	        //Set the layout and add the menus. Assign them names 
	        setLayout(myLayout);
	        add(startMenu, "Start Menu");
	        add(mainGame, "The Game");
	        add(instructions, "Instructions");
	        add(highscores, "Highscores");
	        add(deathScreen, "DeathScreen");
	        setSize(GAME_WIDTH, GAME_HEIGHT);
	        try 
		    {
	        	SORAIRO_DAYS = MidiSystem.getSequence(RocketBoy.class.getResource("/Sound/SorairoDays.mid"));
		        // Create a sequencer for the sequence
		        sequencer = MidiSystem.getSequencer();
		        sequencer.open();	
		        //sequencer.setSequence(SORAIRO_DAYS);
		        /////sequencer.setLoopCount(Integer.MAX_VALUE);
			    //sequencer.start();
		    } 
	        catch (MalformedURLException e){} 
		    catch (IOException n) {} 
		    catch (MidiUnavailableException k) {} 
		    catch (InvalidMidiDataException p) {}
	    	setVisible(true);  
	    }
	    
	    //A method designed to swap windows so that my game can have a proper menu
	    //system. Note the "requestFocusInWindow()" method, its important because
	    //it resets the event listeners to the proper ones for each JPanel.
	    
	    //MUSIC HANDLING SEQUENCE: First play the start song, "ROCK_SHOW", and when the screen
	    //is changed to "Game", stop the old sequence and play the new one "WORST_ENEMY".
	    //If we change from other screens like high scores or credits to start menu, maintain
	    //the same start menu song for the sake of the listener.
	    public void swapView(String name)
	    {
	    	myLayout.show(getContentPane(), name);
	    	
	    	if(name.equals("The Game"))
		    {  
	    	   if(mainGame.volumeOn)
		       {
	    		   //sequencer.stop();		       
	    		   try 
	    		   {
	    			   sequencer.setSequence(SORAIRO_DAYS);
	    		   } 
	    		   catch (InvalidMidiDataException e) {}	
	    		   sequencer.setLoopCount(Integer.MAX_VALUE);
	    		   sequencer.start();
		    	}
		       mainGame.requestFocusInWindow();
		       previousScreen = "Game";
		    }
		    else if(name.equals("Start Menu"))
		    {        
		       if(previousScreen.equals("Game"))
		       {
		    	   sequencer.stop();	       
			       mainGame.requestFocusInWindow();
//			       try 
//			       {
//			    	   sequencer.setSequence(ROCK_SHOW);
//			       } 
//			       catch (InvalidMidiDataException e) {}
			       
			       //sequencer.setLoopCount(Integer.MAX_VALUE);
			       //sequencer.start();
		       }
		       startMenu.requestFocusInWindow();
		       previousScreen = "Start";
		    }  
		    else if(name.equals("Highscores"))
		    {
		    	try
		    	{
		    		highscores.refreshHighScores();      
		    	}
		    	catch(Exception e){}
		    	highscores.requestFocusInWindow(); 
		    	previousScreen = "Highscores";
		    }
		    else if(name.equals("DeathScreen"))
		    {
		    	deathScreen.requestFocusInWindow();
		    	previousScreen = "Deathscreen";
		    }
	    }
	}
}