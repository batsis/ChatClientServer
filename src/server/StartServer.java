package server;

import java.io.IOException;

public class StartServer {

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.init(52000);
		} catch (IOException e) {
			System.err.println("Failed to setup a server socket. " + e.getMessage());
			System.exit(1);
		}
		try {
			server.waitForConnections();
		} catch (IOException e) {
			System.err.println("Failed to setup connection. " + e.getMessage());
			System.exit(1);
		}
		server.close();
	}
}


