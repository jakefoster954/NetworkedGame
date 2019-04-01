package networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Client extends Thread {

	// logger
	private final static Logger logger = Logger.getLogger(networking.client.Client.class);

	private boolean running;

	private ClientSender clientSender;
	
	public Client() {
	}

	@Override
	public void run() {
		running = true;

		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 4545);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			clientSender = new ClientSender(out);

			clientSender.start();

			while (running) {
				String serverMessage = (String)in.readObject();
				System.out.println("### LOBBY DATA ###");
				System.out.println(serverMessage);
			}

			// when the client sender terminates
			clientSender.join();
			out.close();
			in.close();
			socket.close();
			System.out.println("streams and socket closed");

		} catch (InterruptedException e) { 
			logger.error("Interrupted Exception");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IO Exception");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String s) {
		clientSender.send(s);
		
		//might get concurrency issues if it doesn't send.
		if (s.length() > 0 && s.substring(0, 2).equals("CC")) {
			clientSender.close();
			running = false;
		}
	}
}
