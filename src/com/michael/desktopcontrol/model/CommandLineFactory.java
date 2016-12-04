package com.michael.desktopcontrol.model;

import java.util.HashMap;
import java.util.Map;

public class CommandLineFactory {
	private Map<String, Command> commandList = new HashMap<String, Command>();
	
	public void addCommand(String commandName, String command){
		commandList.put(commandName, new CommandLineCommand(command));
	}
	
	public Map<String, Command> getCommandList(){
		return this.commandList;
	}
}
