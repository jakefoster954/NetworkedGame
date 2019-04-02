package networking.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class User extends Thread {

	private ObjectInputStream in;
	private UserSender userSender;
	//private ServerSender serverSender;
	
	private boolean running;

	private String username;
	private boolean ready;
	
	public User(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		userSender = new UserSender(out);
		userSender.start();
		
		ready = false;
	}

	@Override
	public void run() {
		running = true;
		try {
			while (running) {

				// handle input
				String clientMessage = (String)in.readObject();
				System.out.println("Server received: " + clientMessage);
				if (!decodeMessage(clientMessage)) System.out.println("Invalid message format");
			}
		} catch (IOException e) {
			System.out.println("Something went wrong with the client.");
		} catch (ClassNotFoundException e) {
			System.out.println("The class was not found");
		}
		System.out.println("Server Receiver Thread ended");
	}

	public boolean decodeMessage(String s) {
		if (s.length() < 2) {
			return false;
		}
		String command = s.substring(0, 2);
		String message = s.substring(2);
		
		switch(command) {
		case "UN": //username
			this.username = message;
			break;
		case "RD": //request data (Regarding lobbies)
			userSender.send(Server.getLobbies());
			break;
		case "CL": //create lobby
			ready = false;
			if (!Server.createLobby(this, message)) System.out.println("Lobby was not created!");
			break;
		case "JL": //join lobby
			ready = false;
			if (!Server.joinLobby(this, message)) System.out.println("Lobby was not joined!");
			break;
		case "LL": //leave lobby
			if (!Server.leaveLobby(this)) System.out.println("Lobby was not left!");
			break;
		case "RU": //ready up
			ready = !ready;
			break;
		case "CC": //close client
			userSender.send("CC");
			userSender.close(); //might close before it sends. CONCURRENCY :(
			try {
				userSender.join();
			} catch (InterruptedException e) {System.out.println("userSender.join");}
			running = false;
			Server.deleteUser(this);
			break;
		default:
			return false;
		}
		return true;
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
