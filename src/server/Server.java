package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	private ServerSocket serverSocket  = null;
	private Socket connectionToClient = null;
	private BufferedReader clientListener = null;
	private HashMap<String, ServerConnection> listOfConnections;
	
	// init the listening server socket on the port specified
	public void init(int port) throws IOException{
		log("Setting up server socket...");
		serverSocket = new ServerSocket(port);
		log("Server socket setup complete.");
		listOfConnections = new HashMap<String, ServerConnection>();
	}
	
	// loop forever, accepting connections and starting those in new threads
	// adds created thread to list of threads
	public void waitForConnections() throws IOException{
		log("Waiting for connections...");
		while (true){
			connectionToClient = serverSocket.accept();
			ServerConnection sc = new ServerConnection(connectionToClient, this);
			
			Thread newThread = new Thread(sc);
			newThread.start();
			log("Connection to client established.");
			log("New thread: " + newThread.getName());
		}
	}
	
	// close the server side
	public void close(){
		log("Closing connection.");
		try{
			serverSocket.close();
		}catch(IOException e){
			logError("Failed to properly close the connection. " + e.getMessage());
		}	
		log("Connection closed.");
	}
	
	public void sendToClients(String name, String input){
		if (input.equals("/quit")){
			return;
		}
		for(String s: listOfConnections.keySet()) 
			//TODO: do not send to sender
			listOfConnections.get(s).send(name + ": " + input);
//			System.out.println("sending input " + input + " to: " + s);
	}
	
	
	private void log(String message){
		System.out.println("Server: " + message);
	}

	private void logError(String message){
		System.err.println("Server: " + message);
	}
	
	public void saveConnectionToMap(String name, ServerConnection sc){
		listOfConnections.put(name, sc);
		sendToClients("Server: ", name +" has connected to server");
	}

	public void removeFromList(String clientName) {
		sendToClients("Server: ", clientName +" has disconnected");
		listOfConnections.remove(clientName);
		
	}
}