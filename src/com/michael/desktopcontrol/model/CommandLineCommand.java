package com.michael.desktopcontrol.model;

import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;

import com.michael.desktopcontrol.control.SystemTrayManager;
import com.michael.desktopcontrol.exceptions.CommandException;

public class CommandLineCommand implements Command{

	private String command = "";
	boolean showNotification = false;
	
	// Commando comum sem parametros
	
	public CommandLineCommand(String command, boolean showNotification) {
		if(command != null && command.length() > 0){
			this.command = command;
		}
		this.showNotification = showNotification;
	}
	
	public CommandLineCommand(String command) {
		if(command != null && command.length() > 0){
			this.command = command;
		}
	}

	@Override
	public void run(String param) throws CommandException{
		try{
			Process child = Runtime.getRuntime().exec(command + " " + param);
			child.waitFor();

			BufferedReader reader=new BufferedReader( new InputStreamReader(child.getInputStream(), "UTF-8")); 
			String line; 
			while((line = reader.readLine()) != null) 
			{ 
				System.out.println(line);
			}
			if(showNotification){
				SystemTrayManager.getInstance().showNotification(command + " done");
			}
		}catch(Exception e){
			throw new CommandException("Could not execute command line\n" + e.getMessage());
		}
	}

}
