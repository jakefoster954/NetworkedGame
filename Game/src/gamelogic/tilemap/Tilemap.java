package gamelogic.tilemap;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tilemap implements Serializable {

	private int[][] tilemap;

	// tile dimensions
	private int tileWidth, tileHeight;

	public Tilemap(String filePath) {
		tilemap = MapCreator.makeMap(filePath);
		tileWidth = tileHeight = 64;
	}
	
	public Tilemap(BufferedImage i) {
		tilemap = MapCreator.makeMap(i);
		tileWidth = tileHeight = 64;
	}
	
	public Tilemap(int[][] tilemap) {
		this.tilemap = tilemap;
		tileWidth = tileHeight = 64;
	}

	public boolean onGround(Rectangle r) {
		if (!isColliding(r)) {
			r.setLocation(r.x, r.y + 1);
			if (isColliding(r)) {
				r.setLocation(r.x, r.y - 1);
				return true;
			} else {
				r.setLocation(r.x, r.y - 1);
			}
		}
		return false;
	}

	public boolean isColliding(Rectangle r) {
		// get all tiles the rectangle is touching
		Point tl = new Point(r.x / tileWidth, r.y / tileHeight);
		Point br = new Point((r.x + r.width) / tileWidth, (r.y + r.height) / tileHeight);
		for (int j = tl.y; j <= br.y; j++) {
			for (int i = tl.x; i <= br.x; i++) {
				if (tilemap[j][i] / 10 == 2) {
					return true;
				}
			}
		}
		return false;
	}

	public int[][] getTileMap() {
		return tilemap;
	}
	
	public void print() {
		for (int j = 0; j < tilemap.length; j++) {
			for (int i = 0; i < tilemap[0].length; i++) {
				System.out.print(tilemap[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	//Converts the array to a string transposed
	public String toString() {
		String s = "";
		for (int j = 0; j < tilemap.length; j++) {
			for (int i = 0; i < tilemap[0].length; i++) {
				s += (tilemap[j][i] + " ");
			}
			s = s.substring(0, s.length() - 1) + "\n";
		}
		return s;
	}
}
