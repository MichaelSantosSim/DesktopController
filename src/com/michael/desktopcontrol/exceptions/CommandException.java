package com.michael.desktopcontrol.exceptions;

public class CommandException extends Exception {
	
	private String message = "An CommandException Occured";
	
	public CommandException(String message){
		this.setMessage(message);
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
