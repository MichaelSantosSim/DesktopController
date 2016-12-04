package com.michael.desktopcontrol.exceptions;

public class ServerException extends Exception {
private String message = "An ServerException Occured";
	
	public ServerException(String message){
		this.setMessage(message);
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
}
