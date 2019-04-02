package graphics.tilemap;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {

	private BufferedImage image;
	
	private Point2D.Double position;
	private Point2D.Double change;
	
	
	public Background (String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		position = new Point2D.Double(0,0);
		change = new Point2D.Double(0,0);
	}
	
	public void update() {
		this.positionChange(position.x + change.x, position.y+change.y);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)position.x, (int)position.y, null);
		g.drawImage(image, (int)position.x-image.getWidth(), (int)position.y, null);
		g.drawImage(image, (int)position.x, (int)position.y-image.getHeight(), null);
		g.drawImage(image, (int)position.x-image.getWidth(), (int)position.y-image.getHeight(), null);
		
	}
	
	public Point2D.Double getPosition(){
		return position;
	}
	
	public void setPosition(Point2D.Double newPosition){
		this.positionChange(newPosition.x, newPosition.y);
	}
	
	public Point2D.Double getChange(){
		return change;
	}
	
	public void setChange(Point2D.Double newChange){
		change.x = newChange.x % image.getWidth();
		change.y = newChange.y % image.getHeight();		
	}
	
	private void positionChange(double x, double y) {
		position.x = x % image.getWidth();
		if(position.x<0) {
			position.x += image.getWidth();
		}
		position.y = y % image.getHeight();	
		if(position.y<0) {
			position.y += image.getHeight();
		}
	}
	
}
