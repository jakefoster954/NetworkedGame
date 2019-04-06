package networking.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import gamelogic.GameData;

public class UserSender extends Thread {

	private ObjectOutputStream out;
	private boolean running;

	private enum State {LOBBY, GAME};
	private State currentState;
	
	private BlockingQueue<String> lobbyMessagesToSend;
	private BlockingQueue<GameData> gameMessagesToSend;
	
	public UserSender(ObjectOutputStream out) {
		this.out = out;
		currentState = State.LOBBY;
		lobbyMessagesToSend = new LinkedBlockingQueue<String>();
		gameMessagesToSend = new LinkedBlockingQueue<GameData>();
	}
	@Override
	public void run() {
		running = true;

		System.out.println("SERVER SENDER RUNNING");
		try {
			while (running) {
				// wait for message to be added to blocking queue
				switch(currentState)  {
				case LOBBY:
					String lobbyMessage = lobbyMessagesToSend.take();
					out.writeObject(lobbyMessage);
					if (lobbyMessage.startsWith("CC")) {
						running = false;
					} else if (lobbyMessage.startsWith("TM")) {
						currentState = currentState.GAME;
					}
					break;
				case GAME:
					GameData gameMessage = gameMessagesToSend.take();
					out.writeObject(gameMessage);
					if (!(gameMessage.getMessage() == null) && gameMessage.getMessage().startsWith("CC")) {
						running = false;
					}
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("The Object couldn't be sent");
		} catch (InterruptedException e) {
			System.out.println("Blocking queue error");
		}

		System.out.println("User Sender Thread ended");
	}
	
	public void send(String s) {
		lobbyMessagesToSend.add(s);
	}
	
	public void send(GameData gameData) {
		gameMessagesToSend.add(gameData);
	}
}