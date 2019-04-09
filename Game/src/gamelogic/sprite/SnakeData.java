package gamelogic.sprite;

import java.awt.Point;
import java.util.LinkedList;

public class SnakeData {
	protected LinkedList<Point> position;
	protected int bearing;
	
	public SnakeData(LinkedList<Point> position, int bearing) {
		this.position = position;
		this.bearing = bearing;
	}
	
	public LinkedList<Point> getPosition() {
		return position;
	}
	
	public int getBearing() {
		return bearing;
	}
}