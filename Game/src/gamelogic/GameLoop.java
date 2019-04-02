package gamelogic;

import org.apache.log4j.Logger;

/**
 * Server GameLoop. This class will cause update and render to run at 60fps. It will continuously check to see if the game is over.
 * If the game is over, The game loop will end.
 * @author jakefo98
 *
 */
public class GameLoop implements Runnable {
	// logger
	private final static Logger logger = Logger.getLogger(gamelogic.GameLoop.class);

	// game thread
	private Thread thread;
	private boolean running;

	// game state
	private GameStateManager gsm;

	/**
	 * Constructor.
	 * Calls init().
	 */
	public GameLoop() {
		init();
	}

	/**
	 * Initialises the thread.
	 */
	public void start() {
		thread = new Thread(this);
		// listener?
		thread.start();
	}

	/**
	 * Creates the GameStateManager. Sets the starting state to 0.
	 */
	private void init() {
		// initialise the GSM and anything else
		gsm = new GameStateManager();

	}

	/**
	 * Will run the loop at 60fps until the game is over.
	 */
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

	/**
	 * Get the correct GameState from the GameStateManager then call update method in that State.
	 */
	private void update() {
		gsm.update();
	}

	/**
	 * Get the correct GameState from the GameStateManager then call render method in that State.
	 */
	private void render() {
		gsm.render();
	}
}
