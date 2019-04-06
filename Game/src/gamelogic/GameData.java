package gamelogic;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

public class GameData implements Serializable {
	public GameData() {
	}
	
	public GameData(String message) {
		this.message = message;
	}
	
	private String message = null;
	
	private LinkedList<Point> position;
	private int bearing;
	
	
	public LinkedList<Point> getPosition() {
		return position;
	}

	public int getBearing() {
		return bearing;
	}
	
	public String getMessage() {
		return message;
	}
	
	protected void setPosition(LinkedList<Point> position) {
		this.position = position;
	}

	protected void setBearing(int bearing) {
		this.bearing = bearing;
	}

	protected void setMessage(String message) {
		this.message = message;
	}
}
