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

	public GameLoop(Lobby l) {
		game = new GameState(l);
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		long start; // nanoseconds
		long elapsed; // nanoseconds

		running = true;

		// game loop
		while (running) {

			start = System.nanoTime();

			update();
			render();

			// added for testing. REMOVE!
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			elapsed = System.nanoTime() - start;
		}
	}

	private void update() {
		game.update();
	}

	private void render() {
		game.render();
	}
}
