package com.michael.desktopcontrol;

import java.awt.event.ActionEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import com.michael.desktopcontrol.control.CommandManager;
import com.michael.desktopcontrol.control.Server;
import com.michael.desktopcontrol.control.ShutdownCommand;
import com.michael.desktopcontrol.control.SystemTrayManager;
import com.michael.desktopcontrol.model.BrightnessCommand;
import com.michael.desktopcontrol.model.CommandLineCommand;
import com.michael.desktopcontrol.model.CommandLineFactory;
import com.michael.desktopcontrol.model.ReloadPageCommand;
import com.michael.desktopcontrol.model.RunChromeCommand;
import com.michael.desktopcontrol.model.ToggleFullScreenCommand;
import com.michael.desktopcontrol.model.VolumeCommand;

public class main {

	public static void main(String[] args) {
		
		SystemTrayManager stm = SystemTrayManager.getInstance();
		
		setCommandManager();
		
		Server server = new Server();
		Thread serverThread = new Thread(server);
		
		stm.addExitEvent((ActionEvent ae)->{
			serverThread.stop();
		});
		
		serverThread.run();
		
		try {
			String serverAddress = Inet4Address.getLocalHost().getHostAddress();
			stm.showNotification("Running at: " + serverAddress);
			System.out.println("Running server at " + serverAddress);
		} catch (UnknownHostException e) {
			System.out.println("Error at Main");
		}
		
	}
	
	
//	Magnify.exe /lens (defaults to lens view)
//	Magnify.exe /fullscreen (defaults to fullscreen view)
//	Magnify.exe /docked (defaults to docked view)
	
	public static void setCommandManager(){
		CommandManager cm = CommandManager.getInstance();
		
		cm.addCommand("magnify", new CommandLineCommand("Magnify.exe"));
		cm.addCommand("ipconfig", new CommandLineCommand("ipconfig", true));
		cm.addCommand("shutdown", new ShutdownCommand("shutdown"));
		cm.addCommand("reload", new ReloadPageCommand());
		cm.addCommand("togglefullscreen", new ToggleFullScreenCommand());
		cm.addCommand("tvAmigos", new RunChromeCommand());

		cm.addCommand("volume", new VolumeCommand());
		cm.addCommand("brightness", new BrightnessCommand());
	}

}
