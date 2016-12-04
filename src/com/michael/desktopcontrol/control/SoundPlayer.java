package com.michael.desktopcontrol.control;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
	public static synchronized void playBeepSound() {
		  new Thread(new Runnable() {
		   // Clip finishing; see comments.
		    public void run() {

		        try {
		           // Open an audio input stream.
		        
		           AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/volbeep.wav"));
		           // Get a sound clip resource.
		           Clip clip = AudioSystem.getClip();
		           // Open audio clip and load samples from the audio input stream.
		           clip.open(audioIn);
		           clip.start();
		        } catch (UnsupportedAudioFileException e) {
		           e.printStackTrace();
		        } catch (IOException e) {
		           e.printStackTrace();
		        } catch (LineUnavailableException e) {
		           e.printStackTrace();
		        }
		    }
		  }).start();
		}// The wrapper thread is unnecessary, unless it blocks on the
		 
}
