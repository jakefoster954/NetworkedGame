package networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Client extends Thread {

	// logger
	private final static Logger logger = Logger.getLogger(networking.client.Client.class);

	private boolean running;

	private ClientSender clientSender;
	
	private ClientDatabase database;
	
	public Client() {
		database = new ClientDatabase();
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
				if (!decodeMessage(serverMessage)) System.out.println("Invalid message format");
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
	
	public boolean decodeMessage(String s) {
		if (s.length() < 2) {
			return false;
		}
		String command = s.substring(0, 2);
		String message = s.substring(2);
		
		switch(command) {
		case "CC": //close connection
			running = false;
			break;
		case "LD": //lobby data
			System.out.println("### LOBBY DATA ###");
			System.out.println(message);
			break;
		case "SG": //start game
			System.out.println("###GAME START###");
			break;
		case "TM":
			System.out.println("###TILEMAP###");
			database.setTm(message);
			System.out.println(message);
			break;
		default:
			return false;
		}
		return true;
		
	}
	
	public void send(String s) {
		while (clientSender == null) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {System.out.println("Thread.sleep failed");}
		}
		clientSender.send(s);
	}
}
