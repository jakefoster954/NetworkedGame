package gamelogic;

import org.apache.log4j.Logger;

public class GameLoop implements Runnable {
	// logger
	private final static Logger logger = Logger.getLogger(gamelogic.GameLoop.class);

	// game thread
	private Thread thread;
	private boolean running;

	// game state
	private GameStateManager gsm;

	public GameLoop() {
		init();
	}

	public void start() {
		thread = new Thread(this);
		// listener?
		thread.start();
	}

	private void init() {
		// initialise the GSM and anything else
		gsm = new GameStateManager();

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
		gsm.update();
	}

	private void render() {
		gsm.render();
	}
}
