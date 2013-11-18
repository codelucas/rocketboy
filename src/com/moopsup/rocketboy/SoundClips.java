package com.moopsup.rocketboy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class SoundClips 
{
    public static Clip INFLATE = loadClip("/Sound/inflate.wav");
    public static Clip IGNITE = loadClip("/Sound/jetpackIgnite.wav");
    public static Clip ALARM = loadClip("/Sound/airHorn.wav");
    public static Clip MISSILE_SOUND = loadClip("/Sound/missileSound.wav");
    public static Clip BEEP = loadClip("/Sound/beep.wav");
    public static Clip SPLAT = loadClip("/Sound/splat.wav");
    public static Clip JAB = loadClip("/Sound/jab.wav");
    
    public static void touch()
    {
    }

    private static Clip loadClip(String resourceName)
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(SoundClips.class.getResource(resourceName));

            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);

            return clip;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void play(Clip clip, int x)
    {
        if (clip!=null)
        {
            try
            {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
//                float pan = (x-160)/160.0f;
//                ((FloatControl)clip.getControl(FloatControl.Type.BALANCE)).setValue(pan);
//                ((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue((float)(Math.random()*0.5+0.5));
//                ((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue((float)(Math.random()*0.5+0.5));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}