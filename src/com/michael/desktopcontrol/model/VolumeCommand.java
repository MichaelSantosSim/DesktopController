package com.michael.desktopcontrol.model;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.sound.sampled.AudioSystem;

import com.michael.desktopcontrol.control.SoundPlayer;
import com.michael.desktopcontrol.exceptions.CommandException;

public class VolumeCommand  implements Command{

	@Override
	public void run(String param) throws CommandException {
		try{
			CommandLineCommand clc = new CommandLineCommand("nircmd/nircmd.exe");
			
			if(param.contains("+")){
				clc.run("changesysvolume 6666");
			}else if(param.contains("-")){
				clc.run("changesysvolume -6666");
			}else{
				System.out.println("Could not use \"" + param + "\" for reload");
				return;
			}
			SoundPlayer.playBeepSound();
			
		}catch(Exception e){
			System.out.println("Error on Volume: \n" + e.getMessage());
		}
		
	}
}
