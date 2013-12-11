package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable{

	private Socket socket = null;
	private PrintWriter out;
	private BufferedReader in;
	private Server server;
	private String clientName = null;

	// create with a socket
	public ServerConnection(Socket socket, Server server){
		this.socket = socket;
		this.server = server;
	}

	// write message to the stream going to the client
	public void send(String message){
		out.println(message);
		out.flush();
	}

	// read a message from the stream coming in from the client
	public String receive(){
		String receivedMessage = "";
		try {
			receivedMessage = in.readLine();
		} catch (IOException e) {
			System.err.println("Receive failed! " + e.getMessage());
		}
		return receivedMessage;
	}

	public void update(){
		String in = "";
		while (!in.equals("/quit")){
			System.out.println("updating");
			in = receive();
			System.out.println(in);
			server.sendToClients(clientName, in);

		}
	}

	public String getClientName(){
		String in = null;
		//send("what is your name?");
		boolean isEmpty = true;
		while (isEmpty){
			in = receive();
			if(in != null){
				isEmpty = false;
			}
		}
//		System.out.println("DEBUG: name of client " + in);
		clientName = in;
		return clientName;
	}

	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			server.saveConnectionToMap(getClientName(), this);
			update();
			// afterwards, close the socket
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
