package com.michael.desktopcontrol.model;

import java.io.IOException;

import com.michael.desktopcontrol.exceptions.CommandException;

public class RunChromeCommand implements Command {
	
	@Override
	public void run(String param) throws CommandException {
		Process p;
		try {
			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://www.aovivobrasil.com/tvamigos2/" + param + ".php"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
