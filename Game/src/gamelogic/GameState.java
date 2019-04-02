package gamelogic;

import gamelogic.tilemap.Tilemap;
import networking.server.Lobby;

public class GameState extends State {
	
	//Networking Stuff
	private Lobby lobby;
	
	public GameState(Lobby lobby) {
		super();
		
		this.lobby = lobby;
	}
	
	public GameState() {
		super();
		
		lobby = null;
	}

	@Override
	public void init() {
		int[][] temp = {{0,0,0},
						{0,0,0},
						{0,0,0}};
		Tilemap tm = new Tilemap(temp);
		tm.print();
		System.out.println();
		System.out.println(tm.toString());
	}

	@Override
	public void update() {
		//System.out.println("update");
	}

	@Override
	public void render() {
		//System.out.println("render");
	}
	
}
