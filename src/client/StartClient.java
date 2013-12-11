package client;

import gui_package.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class StartClient {

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		
		Client cl1 = new Client("127.0.0.1", 52000);
		GUI gui = new GUI();
		cl1.setGui(gui);
		gui.setClient(cl1);
		
		try {
			Thread t1 = new Thread(cl1);
			t1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true){
//		cl1.send(sc.nextLine());
		}
	}
}
