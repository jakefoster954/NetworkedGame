package gamelogic.sprite;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

public class GameData implements Serializable {
	protected LinkedList<Point> position;
	protected int bearing;
	
	
	public LinkedList<Point> getPosition() {
		return position;
	}

	public int getBearing() {
		return bearing;
	}
	
	public void setPosition(LinkedList<Point> position) {
		this.position = position;
	}

	public void setBearing(int bearing) {
		this.bearing = bearing;
	}
}
