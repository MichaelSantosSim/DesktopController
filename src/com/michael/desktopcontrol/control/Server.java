package com.michael.desktopcontrol.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.function.BiConsumer;

import com.michael.desktopcontrol.exceptions.CommandException;
import com.michael.desktopcontrol.exceptions.ServerException;
import com.michael.desktopcontrol.model.CommandLineCommand;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Server implements Runnable {
	
	HttpServer server;
	
	public void run(){
		try{
			runServer();
		}catch(ServerException e){
			SystemTrayManager.getInstance().showNotification(e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	
	public void runServer() throws ServerException{
		
		try {
			server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", new MyHandler());
			server.setExecutor(null); // creates a default executor
			server.start();
			
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		}
	}
	

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t){
			
			OutputStream os = t.getResponseBody();
			
			try{
				
				try{
					CommandManager cm = CommandManager.getInstance();
					String str = convertStreamToString(t.getRequestBody());
					String[] params = str.split(":");
					cm.runCommand(params[0], params[1]);
					
				}catch(Exception e){
					System.out.println("Server CommandManager Run: " + e.getMessage());
					SystemTrayManager.getInstance().showNotification("Error");
				}
				
				
				String response = "Everything is Fine";
				t.sendResponseHeaders(200, response.length());
				
				
				os.write(response.getBytes());
				os.close();
			
			}catch(IOException e){
				System.out.println(e.getMessage());
			}catch (Exception e) {
				System.out.println("HttpHandler error: \n" + e.getMessage());
			}finally{
				
				try {
					os.close();
				} catch (Exception e) {
					System.out.println("HttpHandler: failed to close OutputStream:\n" + e.getMessage());
				}
				
			}
		}
		
		private String convertStreamToString(java.io.InputStream is) {
		    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		    return s.hasNext() ? s.next() : "";
		}
	}
}

