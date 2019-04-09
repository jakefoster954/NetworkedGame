package graphics.gamestate;

import java.awt.Point;

public abstract class State {

	protected GameStateManager gsm;
	
	public State() {
	}
	
	protected abstract void init();
	
	protected abstract void update();
	
	protected abstract void render();

	protected abstract void keyPressed(int key);

	protected abstract void keyReleased(int key);
	
	protected abstract void mousePressed(int button, Point p);
	
	protected abstract void mouseReleased(int button, Point p);
}
