package networking.server;

import java.util.ArrayList;

public class Lobby {
	private int maxSize;
	private String lobbyName;
	private ArrayList<User> users;
	
	public Lobby(User host, String lobbyName, int maxSize) {
		this.maxSize = maxSize;
		this.lobbyName = lobbyName.replaceAll("\\s+", "_");
		users = new ArrayList<User>();
		users.add(host);
	}
	
	public Lobby(User host, String lobbyName) {
		maxSize = 4;
		this.lobbyName =  lobbyName.replaceAll("\\s+", "_");
		users = new ArrayList<User>();
		users.add(host);
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
}
