package graphics.gamestate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import graphics.WindowSize;
import graphics.tilemap.Background;

public class StartState extends State {
	Background background;
	
	Image title;
	Image play;
	Image help;
	Image quit;
	
	private int x = 0;
	private int y = 0;
	
	public StartState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		background = new Background("res/background/flower.png");
		title = loadImage("res/text/title.png");
		play = loadImage("res/text/play.png");
		help = loadImage("res/text/help.png");
		quit = loadImage("res/text/quit.png");
	}

	@Override
	public void update() {
		background.update();
		
		x = (x+1) % WindowSize.WIDTH;
		y = (y+1) % WindowSize.HEIGHT;
	}

	@Override
	public void render(Graphics2D g) {
		background.render(g);
		g.drawImage(title, 400, 100, null);
		g.drawImage(play, 520, 300, 220, 105, null);
		g.drawImage(help, 520, 400, 220, 105, null);
		g.drawImage(quit, 520, 500, 220, 105, null);
	}
	
	@Override
	public void keyPressed(int key) {
		System.out.println("Key Pressed: " + key);
	}

	@Override
	public void keyReleased(int key) {
		System.out.println("Key Released: " + key);
	}

	@Override
	public void mousePressed(int button, Point p) {
		System.out.println("Mouse button " + button + " clicked at: " + p);
	}

	@Override
	public void mouseReleased(int button, Point p) {
		System.out.println("Mouse button " + button + " released at: " + p);
		
	}
}
