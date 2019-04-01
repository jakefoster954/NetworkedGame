package networking.client;

import java.util.Scanner;

public class Menu {
	public static boolean running;
	
	public static void main(String[] args) throws InterruptedException { //for thread.sleep testing
		running = true;
		
		Client client = new Client();
		client.start();
		
		Scanner s = new Scanner(System.in);
		while (running) {
			Thread.sleep(50);
			System.out.print(">> ");
			String input = s.nextLine();
			client.send(input);
			if (input.length() > 0 && input.substring(0, 2).equals("CC")) {
				running = false;
			}
		}
	}
}