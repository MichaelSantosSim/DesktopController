package com.michael.desktopcontrol.model;

import com.michael.desktopcontrol.control.SoundPlayer;
import com.michael.desktopcontrol.exceptions.CommandException;

public class BrightnessCommand implements Command {

	@Override
	public void run(String param) throws CommandException {
		try{
			CommandLineCommand clc = new CommandLineCommand("nircmd/nircmd.exe");
			
			if(param.contains("+")){
				clc.run("changebrightness 10");
			}else if(param.contains("-")){
				clc.run("changebrightness -10");
			}else{
				System.out.println("Could not use \"" + param + "\" for reload");
				return;
			}
			
		}catch(Exception e){
			System.out.println("Error on Volume: \n" + e.getMessage());
		}

	}

}
