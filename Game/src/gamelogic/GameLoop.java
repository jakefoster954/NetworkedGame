package gamelogic;

import org.apache.log4j.Logger;

import networking.server.Lobby;

public class GameLoop implements Runnable {
	// logger
	private final static Logger logger = Logger.getLogger(gamelogic.GameLoop.class);

	// game thread
	private Thread thread;
	private boolean running;

	// game state
	private GameState game;
	
	private Lobby lobby;
	
	private int maxFPS = 2;

	public GameLoop(Lobby lobby) {
		this.lobby = lobby;
		game = new GameState(lobby);
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		long start; // nanoseconds
		long elapsed; // nanoseconds
		long wait; // milliseconds
		long target = 1_000 / maxFPS; // milliseconds


		running = true;

		// game loop
		while (running) {

			start = System.nanoTime();

			game.update();
			game.render();

			elapsed = System.nanoTime() - start;

			if (elapsed / 1_000_000 < target) {
				wait = target - elapsed / 1_000_000;
				try {
					Thread.sleep(wait);
					elapsed = System.nanoTime() - start;
				} catch (InterruptedException e) {
					logger.fatal("Thread.sleep failed");
				}
			}
			
			if (lobby.getUsers().size() == 0) {
				running = false;
			}
		}
	}
}
