package gamelogic.sprite;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

public class SnakeData implements Serializable {
	protected LinkedList<Point> position;
	protected int bearing;
	
	
	public LinkedList<Point> getPosition() {
		return position;
	}

	public int getBearing() {
		return bearing;
	}
}
