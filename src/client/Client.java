package client;

import gui_package.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

	private Socket connectionToServer = null;
	private PrintWriter outStream     = null;
	private BufferedReader inStream   = null;
	private String name;
	private GUI gui;
	
	//Konstruktor, tar emot ipadress och port, sedan kör connect
	public Client(String serverAddress, int serverPort) throws IOException{
		log("creating connection...");
		connect(serverAddress, serverPort);
	}
	
	// set up a connection to a specified server and port
	public void connect(String serverAddress, int serverPort) throws IOException{
		log("Connecting to server...");

		//Set up a socket and connect it to our server
		connectionToServer = new Socket(serverAddress, serverPort);
		
		//The getOutputStream and getInputStream methods return byte streams
		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream  = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));

		log("Connected!");
	}
	
	// set up a connection to a specified server and port
	// this version using a InetAddress
	public void connect(InetAddress serverAddress, int serverPort) throws IOException{
		log("Connecting to server...");
		//Set up a socket and connect it to our server
		connectionToServer = new Socket(serverAddress, serverPort);
		//The getOutputStream and getInputStream methods return byte streams
		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream  = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
		log("Connected!");
	}
	
	// close the streams and the socket
	public void close(){
		log("Closing connection.");
		try{
			inStream.close();
			outStream.close();
			connectionToServer.close();
		}catch(IOException e){
			logError("Failed to properly close the connection. " + e.getMessage());
		}
		
		log("Connection closed.");
	}
	
	// write a message to the stream going to the server
	public void send(String message){
//		log("Sending...");
		outStream.println(message);
		outStream.flush();
		if(message.equals("/quit")){
			System.exit(1);
		}
//		log("Message sent.");
	}
	
	// read a message from the stream coming from the server
	public String receive(){
		log("Receiving...");

		String receivedMessage = "";
		try {
			receivedMessage = inStream.readLine();
			
			//if there is no message to recieve, just return
			if (receivedMessage == null){
				return null;
			}
			
		} catch (IOException e) {
			logError("Receive failed! " + e.getMessage());
		}
		//if a message is recieved
		log("Message received!");
		return receivedMessage;
	}
	
	private void log(String message){
		System.out.println("Client " + Thread.currentThread().getName() + ": " + message);
	}

	private void logError(String message){
		System.err.println("Client: " + message);
	}

	public String getName(){
		return name;
	}
	
	// this is what happens when the Thread is run
	public void run() {
		while(true){
			
			gui.addToChatOutput(receive());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setGui(GUI gui){
		this.gui = gui;
	}
}