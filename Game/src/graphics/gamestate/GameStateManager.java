package graphics.gamestate;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<State> states = new ArrayList<State>();
	private State currentState;
	//TODO: some permanent data storage
	
	public GameStateManager() {
		states.add(new StartState(this));
		currentState = states.get(0);
	}
	
	public void setState(int stateValue) {
		currentState = states.get(stateValue);
	}
	
	public void update() {
		currentState.update();
		
	}

	public void render(Graphics2D g) {
		currentState.render(g);
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
