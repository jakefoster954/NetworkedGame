package networking.server;

import java.util.ArrayList;

import gamelogic.GameLoop;

public class Lobby extends Thread {
	private boolean running;
	
	private int maxSize;
	private String lobbyName;
	private ArrayList<User> users;
	
	public Lobby(User host, String lobbyName, int maxSize) {
		this.maxSize = maxSize;
		this.lobbyName = lobbyName.replaceAll("\\s+", "_");
		users = new ArrayList<User>();
		users.add(host);
		
		this.start();
	}
	
	public Lobby(User host, String lobbyName) {
		maxSize = 4;
		this.lobbyName =  lobbyName.replaceAll("\\s+", "_");
		users = new ArrayList<User>();
		users.add(host);
		
		this.start();
	}
	
	@Override
	public void run() {
		running = true;
		double time = 0;
		int delay = 5000;
		
		while (running) {
			
			while (!allReady()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {System.out.println("Thread.sleep Failed");}
				time = System.currentTimeMillis();
			}
			
			while(allReady()) {
				if ((System.currentTimeMillis() - time) > delay) {
					for (User u: users) {
						u.setReady(false);
						u.send("SG");
					}
					//start the game
					System.out.println("START GAME");
					Server.gameStarted(this);
					GameLoop game = new GameLoop(this);
					game.start();
					running = false;
					break;
				}
			}
		}
	}
	
	public String getLobbyName() {
		return lobbyName;
	}
	
	public int getAvailableSpace() {
		return maxSize-users.size();
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public String toString() {
		String s = lobbyName;
		for (User u: users) {
			s += " ";
			s += u.getUsername();
			s += ":";
			s += u.isReady();
			
		}
		return s;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public boolean removeUser(User u) {
		for (User user: users) {
			if (u == user) {
				users.remove(u);
				return true;
			}
		}
		return false;
	}
	
	public boolean allReady() {
		for (User user: users) {
			if (!user.isReady()) {
				return false;
			}
		}
		return true;
	}
	
	public void close() {
		running = false;
	}
}
