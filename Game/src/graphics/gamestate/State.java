package graphics.gamestate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class State {

	protected GameStateManager gsm;
	
	public State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	
	abstract void update();
	
	abstract void render(Graphics2D g);

	public abstract void keyPressed(int key);
	
	public abstract void keyReleased(int key);
	
	public abstract void mousePressed(int button, Point p);
	
	public abstract void mouseReleased(int button, Point p);
	
	public Image loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
