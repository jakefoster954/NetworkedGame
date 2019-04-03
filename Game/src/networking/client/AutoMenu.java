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
		//W = 87
		//A = 65
		//S = 83
		//D = 68
		Scanner s = new Scanner(System.in);
		while (true) {
			String input = s.nextLine();

			//for movement. WONT BE NEEDED IN GUI. 'KeyEvent' CAN BE USED INSTEAD!
			if (input.startsWith("KP") || input.startsWith("KR")) {
				if (input.length() > 2) {
					input = input.substring(0,2) + (int)input.charAt(2);
				}
			}
			
			client.send(input);
			
			if (input.startsWith("CC")) {
				break;
			}
		}
	}
}