package client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class StartClient {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Client cl1 = new Client("127.0.0.1", 52000);
		try {
			//create two client connections
			
			//Client cl2 = new Client("127.0.0.1", 52000);
			// create and start Threads using those connections
			Thread t1 = new Thread(cl1);
			t1.start();
			//Thread t2 = new Thread(cl2);
			//t2.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
		cl1.send(sc.nextLine());
		//System.out.println(cl1.receive());
		}
	}
}
