package gamelogic;

import java.awt.event.KeyEvent;

import gamelogic.sprite.Snake;
import gamelogic.tilemap.Tilemap;
import networking.server.Lobby;
import networking.server.User;

public class GameState extends State {
	
	//Networking Stuff
	private Lobby lobby;
	
	private Snake snake;
	
	public GameState(Lobby lobby) {
		this.lobby = lobby;
		
		init();
	}

	@Override
	protected void init() {
		int[][] temp = new int[8][8]; //auto initialised to 0
		Tilemap tm = new Tilemap(temp, 16);
		//send the tilemap to all users.
		for (User u: lobby.getUsers()) {
			u.send("TM" + tm.toString());
		}
		
		snake = new Snake();
	}

	@Override
	protected void update() {
		snake.update();
		System.out.println(snake.getPosition().getFirst());
		
		checkInputs();
	}

	@Override
	protected void render() {
		//System.out.println("render");
	}
	
	public void checkInputs() {
		for (User u: lobby.getUsers()) {
			if (u.isPressed(KeyEvent.VK_D)) {
				snake.setBearing(90);
			} else if (u.isPressed(KeyEvent.VK_A)) {
				snake.setBearing(270);
			} else if (u.isPressed(KeyEvent.VK_W)) {
				snake.setBearing(0);
			} else if (u.isPressed(KeyEvent.VK_S)) {
				snake.setBearing(180);
			}
		}
	}
}
