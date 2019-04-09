package graphics.gamestate;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Point;

import graphics.Texture;

public class InitialState extends State {
	Texture background;
	Texture pepper;
	
	
	public InitialState() {
		init();
	}

	@Override
	protected void init() {
		background = new Texture("./res/sprite/pepper.png");
		pepper = new Texture("./res/sprite/pepper.png");
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void render() {
		//background
		background.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(-1f, 1f);
			glTexCoord2f(1, 0);
			glVertex2f(1f, 1f);
			glTexCoord2f(1, 1);
			glVertex2f(1f, -1f);
			glTexCoord2f(0, 1);
			glVertex2f(-1f, -1f);
		glEnd();
		
		//pepper
		pepper.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(-0.5f, 0.5f);
			glTexCoord2f(1, 0);
			glVertex2f(0.5f, 0.5f);
			glTexCoord2f(1, 1);
			glVertex2f(0.5f, -0.5f);
			glTexCoord2f(0, 1);
			glVertex2f(-0.5f, -0.5f);
		glEnd();
		
	}

	@Override
	protected void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mousePressed(int button, Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mouseReleased(int button, Point p) {
		// TODO Auto-generated method stub
		
	}

}
