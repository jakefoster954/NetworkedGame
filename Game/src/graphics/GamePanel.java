package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import graphics.gamestate.GameStateManager;

public class GamePanel extends JPanel implements Runnable , KeyListener , MouseListener {

	private GameStateManager gsm;
	private Graphics2D g;
	private BufferedImage image;
	
	private boolean running;
	
	private final long FRAME_RATE = 60;
	private final long FRAME_TIME = 1000/FRAME_RATE;
	
	public GamePanel() {
		super();
		this.setPreferredSize(new Dimension(WindowSize.WIDTH*WindowSize.SCALE_FACTOR,WindowSize.HEIGHT*WindowSize.SCALE_FACTOR));
		this.setFocusable(true);
		this.requestFocus();
	}

	public void init() {
		image = new BufferedImage(WindowSize.WIDTH, WindowSize.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
		running = true;
	}
	
	public void start() {
		Thread thread = new Thread(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		thread.start();
	}
	
	@Override
	public void run() {
		this.init();
		
		
		while(running) {
			long oldTime = System.nanoTime();
			
			gsm.update();
			gsm.render(g);
			this.drawToScreen();
			
			long newTime = System.nanoTime();
			long waitTime = FRAME_TIME -((newTime - oldTime) / 1_000_000);
			
			//System.out.println("Start: "+oldTime+ " End: " + newTime + " Wait: " + waitTime);
			
			if (waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private void drawToScreen() {
		Graphics drawG = this.getGraphics();
		drawG.drawImage(image, 0, 0, WindowSize.WIDTH*WindowSize.SCALE_FACTOR, WindowSize.HEIGHT*WindowSize.SCALE_FACTOR, null);
		drawG.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e.getButton(), new Point(e.getX(), e.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e.getButton(), new Point(e.getX(), e.getY()));
	}
	
}
