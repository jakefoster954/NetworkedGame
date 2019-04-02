package gamelogic;

import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> states = new ArrayList<GameState>();
	private GameState currentState;

	public GameStateManager() {
		// add all states that need to be made
		states.add(new LobbyState(this));
		// set the default starting state
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
}
