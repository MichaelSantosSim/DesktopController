package com.michael.desktopcontrol.control;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.michael.desktopcontrol.view.DebugWindow;

public class SystemTrayManager {
	
	private static SystemTrayManager instance = null;
	static PopupMenu popup = new PopupMenu();
	static TrayIcon trayIcon = null;
	static SystemTray tray = null;
	MenuItem exitItem = new MenuItem("Exit");
	DebugWindow dw = null;
	
	
	private List<String> messages = new ArrayList<String>(){
		{
			add("I want your soul");
			add("Don't be afraid");
			add("Pretend i'm not here");
			add("How curious");
			add("12508845");
			add("You drowned him");
			add("What you've become?");
			add("Are you proud of what you've done?");
			add("3405691582");
			add("BEGONE!");
			add("I know what you've done");
			add("You murderer");
		}
		
		@Override
		public String get(int index) {
			Random rand = new Random();
			int randomNum = rand.nextInt((this.size()));
			return super.get(randomNum);
		};
	};
	
	
	private SystemTrayManager() {
		
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		
		trayIcon =
				new TrayIcon(getHappyFace());
		tray = SystemTray.getSystemTray();
		

		// Create a pop-up menu components
//		CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//		CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//		Menu displayMenu = new Menu("Display");
//		MenuItem errorItem = new MenuItem("Error");
//		MenuItem warningItem = new MenuItem("Warning");
//		MenuItem infoItem = new MenuItem("Info");
//		MenuItem noneItem = new MenuItem("None");
		

		//Add components to pop-up menu
		popup.add(getAboutMenu());
		setDebugItem();
		popup.addSeparator();
		popup.add(exitItem);
//		popup.add(cb2);
//		popup.addSeparator();
//		popup.add(displayMenu);
//		displayMenu.add(errorItem);
//		displayMenu.add(warningItem);
//		displayMenu.add(infoItem);
//		displayMenu.add(noneItem);
		
		try {
			setTrayIconToolTip();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setExitItem();
		
		trayIcon.setPopupMenu(popup);
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

	}
	
	
	private void setExitItem(){
		addExitEvent((ActionEvent e)->{
			tray.remove(trayIcon);
			System.exit(0);
		});
	}
	
	private void setDebugItem(){
        if(System.getenv().containsKey("debug")){
        	dw = new DebugWindow();
        	
        	MenuItem debugItem = new MenuItem("Debug");
        	debugItem.addActionListener((ActionEvent e)->{
        		dw.setVisible(true);
        	});
        	popup.add(debugItem);
        	dw.setVisible(true);
        }
	}
	
	private void setTrayIconToolTip() throws UnknownHostException{
		trayIcon.setToolTip("Running at " + Inet4Address.getLocalHost().getHostAddress());
		
	}
	
	private MenuItem getAboutMenu(){
		MenuItem aboutItem = new MenuItem("About");
		
		aboutItem.addActionListener((ActionEvent e)->{
			trayIcon.displayMessage("Desktop Controller",  messages.get(0), TrayIcon.MessageType.NONE);
		});
		
		return aboutItem;
	}
	
	public void showNotification(String message){
		trayIcon.displayMessage("Desktop Controller",  message, TrayIcon.MessageType.NONE);
	}
	
	public void addExitEvent(ActionListener al){
		exitItem.addActionListener(al);
		
	}


	public Image getHappyFace(){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("resources/app.png"));
		} catch (IOException e) {
			
		}
		return img;
	}
	
	
	
	public static SystemTrayManager getInstance(){
		if(instance == null){
			instance = new SystemTrayManager();
		}
		return instance;
	}
}
