package gamelogic;

public class LobbyState extends GameState {
	
	public LobbyState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		System.out.println("update");
	}

	@Override
	public void render() {
		System.out.println("render");
	}
	
}
