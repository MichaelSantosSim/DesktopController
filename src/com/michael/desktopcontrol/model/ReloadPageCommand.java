package com.michael.desktopcontrol.model;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.michael.desktopcontrol.exceptions.CommandException;

public class ReloadPageCommand implements Command{

	@Override
	public void run(String param) throws CommandException {
		try{
			Robot robot = new Robot();
			
			if(param.contains("dummy")){
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.mouseMove(2, 200);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				robot.keyPress(KeyEvent.VK_F5);
				robot.mouseMove(0, 0);
			}
			else{
				System.out.println("Could not use \"" + param + "\" for reload");
			}
		}catch(Exception e){
			System.out.println("Error on Volume: \n" + e.getMessage());
		}
		
	}

}
