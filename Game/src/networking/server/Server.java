package networking.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public abstract class Server {
	private final static Logger logger = Logger.getLogger(networking.server.Server.class);

	private static boolean running;

	private static ArrayList<Lobby> gamesInProgress = new ArrayList<Lobby>();
	private static ArrayList<Lobby> lobbies = new ArrayList<Lobby>();
	private static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {
		running = true;

		ServerSocket server = null;
		try {
			server = new ServerSocket(4545);
		} catch (IOException e) {
			logger.error("Failed to create Socket");
		}

		try {
			// start listening for a connection
			while (running) {
				// create socket for this client
				Socket client = server.accept();
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				User user = new User(in, out);
				user.start();
				users.add(user);
			}

			// Don't think i close streams...
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getLobbies() {
		String s = "";
		for (Lobby l : lobbies) {
			s += l.toString();
			s += "\n";
		}
		if (s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public static boolean createLobby(User u, String lobbyName) {
		// check lobby name doesn't already exist
		for (Lobby l : lobbies) {
			if (l.getLobbyName().equals(lobbyName)) {
				return false;
			}
		}

		// find the user that wants to create the lobby and remove them from users.
		for (User user : users) {
			if (u == user) {
				lobbies.add(new Lobby(u, lobbyName));
				users.remove(user);
				return true;
			}
		}
		return false;
	}

	public static boolean joinLobby(User u, String lobbyName) {
		// check user isn't already in a lobby
		boolean located = false;
		for (User user : users) {
			if (user == u) {
				located = true;
				break;
			}
		}
		if (located == false) {
			return false;
		}

		// find the lobby with the name matching the required lobby
		for (Lobby l : lobbies) {
			if (l.getLobbyName().equals(lobbyName) && l.getAvailableSpace() > 0) {
				l.addUser(u);
				users.remove(u);
				return true;
			}
		}
		return false;
	}

	public static boolean leaveLobby(User u) {
		// get the lobby name
		Lobby l = getLobby(u);
		if (l==null) {
			return false;
		}
		
		for (User user : l.getUsers()) {
			if (user == u) {
				users.add(u);
				// check to see if the user is the only member of the lobby
				if (l.getUsers().size() == 1) {
					l.close();
					l.removeUser(u);
					lobbies.remove(l);
					gamesInProgress.remove(l);
					return true;
				} else {
					return l.removeUser(u);
				}
			}
		}
		return false;
	}

	public static Lobby getLobby(User u) {
		for (Lobby l : lobbies) {
			for (User user : l.getUsers()) {
				if (user == u) {
					return l;
				}
			}
		}
		for (Lobby l : gamesInProgress) {
			for (User user : l.getUsers()) {
				if (user == u) {
					return l;
				}
			}
		}
		return null;
	}

	public static boolean deleteUser(User u) {
		leaveLobby(u);
		for (User user : users) {
			if (u == user) {
				users.remove(user);
				return true;
			}
		}
		return false;
	}

	// THIS DOES NOT DELETE THE USERS. THE LINK TO THE USERS CAN BE PASSED TO THE
	// GAME THREAD!!!
	public static boolean gameStarted(Lobby lobby) {
		for (Lobby l : lobbies) {
			if (lobby == l) {
				gamesInProgress.add(lobby);
				lobbies.remove(l);
				return true;
			}
		}
		return false;
	}
}
