package gamelogic;

import gamelogic.tilemap.Tilemap;
import networking.server.Lobby;
import networking.server.User;

public class GameState extends State {
	
	//Networking Stuff
	private Lobby lobby;
	
	public GameState(Lobby lobby) {
		this.lobby = lobby;
		
		init();
	}

	@Override
	protected void init() {
		int[][] temp = new int[8][8]; //auto initialised to 0
		Tilemap tm = new Tilemap(temp);
		//send the tilemap to all users.
		for (User u: lobby.getUsers()) {
			u.send("TM" + tm.toString());
		}
	}

	@Override
	protected void update() {
		//System.out.println("update");
	}

	@Override
	protected void render() {
		//System.out.println("render");
	}
	
	public void keyPressed(int key) {
		//specify movement stuff
	}
	
	public void keyReleased(int key) {
		//specify movement stuff
	}
	
}
