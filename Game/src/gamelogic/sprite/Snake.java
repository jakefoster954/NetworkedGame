package gamelogic.sprite;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	private Point head;
	private LinkedList<Point> tail;
	private double bearing;
	
	public Snake() {
		init();
		head = new Point(0,0);
		bearing = 0;
	}
	
	public Snake(int size) {
		init();
		int tailSize = size - 1;
	}
	
	public void init() {
		tail = new LinkedList<Point>();
	}
}
