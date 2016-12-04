package com.michael.desktopcontrol.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.michael.desktopcontrol.model.Command;

public class CommandManager {
	
	private Map<String, Command> commandList = new HashMap<String, Command>();
	
	private static CommandManager instance;
	
	private CommandManager(){}
	
	public void runCommand(String command, String param) throws 	Exception{
		/*  ClassCastException,
			NullPointerException
		*/
		commandList.get(command).run(param);
	}
	
	public void addCommand(String name, Command command) throws UnsupportedOperationException,
																ClassCastException,
																NullPointerException,
																IllegalArgumentException{
		
		commandList.put(name, command);
	}
	
	public void addAllCommands(Map<String, Command> commandList){
		this.commandList.putAll(commandList);
	}
	
	public static CommandManager getInstance(){
		if(instance == null){
			instance = new CommandManager();
		}
		
		return instance;
	}
}
