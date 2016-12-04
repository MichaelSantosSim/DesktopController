package com.michael.desktopcontrol.control;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.michael.desktopcontrol.exceptions.CommandException;
import com.michael.desktopcontrol.model.Command;

public class ShutdownCommand implements Command{
	
	private String command = "";
	
	public ShutdownCommand(String command) {
		if(command != null && command.length() > 0){
			this.command = command;
		}
	}
	
	@Override
	public void run(String param) throws CommandException {
		try{
			Process child = Runtime.getRuntime().exec(command + " " + param);
			child.waitFor();

			BufferedReader reader=new BufferedReader( new InputStreamReader(child.getInputStream(), "UTF-8")); 
			String line; 
			while((line = reader.readLine()) != null) 
			{ 
				System.out.println(line);
			}
		}catch(Exception e){
			throw new CommandException("Could not execute command line\n" + e.getMessage());
		}
		
	}

}
