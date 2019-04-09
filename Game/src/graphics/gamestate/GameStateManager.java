package graphics.gamestate;

import java.awt.Point;
import java.util.ArrayList;

public class GameStateManager {

	public static GameStateManager gameStateManager = null;
	
	private ArrayList<State> states = new ArrayList<State>();
	private State currentState;
	//TODO: some permanent data storage
	
	public static GameStateManager getInstance() {
		if (gameStateManager == null) {
			gameStateManager = new GameStateManager();
			System.out.println("Instance created");
		}
		System.out.println("Instance Returned");
		return gameStateManager;
	}
	
	private GameStateManager() {
		System.out.println("constructor");
		states.add(new InitialState());
		currentState = states.get(0);
	}
	
	public void setState(int stateValue) {
		currentState = states.get(stateValue);
	}
	
	public void update() {
		currentState.update();
		
	}

	public void render() {
		currentState.render();
	}

	public void keyPressed(int k) {
		currentState.keyPressed(k);
	}

	public void keyReleased(int k) {
		currentState.keyReleased(k);
		
	}

	public void mousePressed(int b, Point p) {
		currentState.mousePressed(b, p);
	}


	public void mouseReleased(int b, Point p) {
		currentState.mouseReleased(b, p);
	}

}
