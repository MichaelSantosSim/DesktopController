package com.michael.desktopcontrol.model;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.michael.desktopcontrol.exceptions.CommandException;

public class ToggleFullScreenCommand implements Command {

	@Override
	public void run(String param) throws CommandException {
		try{
			Robot robot = new Robot();
			
			if(param.contains("dummy")){
				robot.mouseMove(950, 300);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				robot.mouseMove(0, 0);
			}
			else{
				System.out.println("Could not use \"" + param + "\" for toggle fs");
			}
		}catch(Exception e){
			System.out.println("Error on Volume: \n" + e.getMessage());
		}
		
	}
	
}
