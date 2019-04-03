package networking.client;

import java.util.Scanner;

public class AutoMenu {
	public static boolean running;
	
	public static void main(String[] args) throws InterruptedException { //for thread.sleep testing
		running = true;
		
		Client client = new Client();
		client.start();
		Thread.sleep(10);
		client.send("UNJAKE");
		Thread.sleep(10);
		client.send("CLLOBBY0");
		Thread.sleep(10);
		client.send("RU");
		Thread.sleep(10);
		client.send("RD");
		
		//change state from lobby to game
		Scanner s = new Scanner(System.in);
		while (true) {
			String input = s.nextLine();
			client.send(input);
			
			System.out.println(input);
			if (input.startsWith("CC")) {
				System.out.println("here");
				break;
			}
		}
	}
}