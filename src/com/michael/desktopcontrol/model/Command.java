package com.michael.desktopcontrol.model;

import com.michael.desktopcontrol.exceptions.CommandException;

public interface Command {
	public void run(String param) throws CommandException;
}
