package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server {
	private ServerSocket serverSocket  = null;
	private Socket connectionToClient = null;
	private HashMap<String, ServerConnection> listOfConnections;
	
	// init the listening server socket on the port specified and creats a map for storage of connections
	public void init(int port) throws IOException{
		log("Setting up server socket...");
		serverSocket = new ServerSocket(port);
		log("Server socket setup complete.");
		listOfConnections = new HashMap<String, ServerConnection>();
	}

	// loop forever, accepting connections and starting those in new threads
	// adds created serverConnection to list of Connections
	public void waitForConnections() throws IOException{
		log("Waiting for connections...");
		while (true){
			connectionToClient = serverSocket.accept();
			ServerConnection sc = new ServerConnection(connectionToClient, this);
			Thread newThread = new Thread(sc);
			newThread.start();
			log("Connection to client established.");
			//log("New thread: " + newThread.getName());
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

	//Sends a string to all the clients OR if the string that was sent into this method is /quit it does nothing
	public void sendToClients(String name, String input){
		if (input.equals("/quit")){
			return;
		}
		for(String s: listOfConnections.keySet()) 
			listOfConnections.get(s).send(name + ": " + input);
			//log("sending input " + input + " to: " + s);
	}

	private void log(String message){
		System.out.println("Server: " + message);
	}

	private void logError(String message){
		System.err.println("Server: " + message);
	}

	//Saves connection to a list, then sends message
	public void saveConnectionToMap(String name, ServerConnection sc){
		listOfConnections.put(name, sc);
		sendToClients("Server: ", name +" has connected to server");
		log(name + " has connected");
	}

	//removes a connection from the list, then sends message
	public void removeFromList(String clientName) {
		sendToClients("Server: ", clientName +" has disconnected");
		log(clientName + " has disconnected");
		listOfConnections.remove(clientName);
	}

	
}