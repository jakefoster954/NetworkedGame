package gamelogic;

public abstract class State {

	public State() {
		init();
	}

	public abstract void init();

	public abstract void update();

	public abstract void render();

}
