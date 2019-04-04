package gamelogic.sprite;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	
	protected LinkedList<Point> position;
	protected int bearing;
	
	public Snake() {
		position = new LinkedList<Point>();
		position.add(new Point(0,0));
		bearing = 90;
	}
	
	public Snake(int size) {
		position = new LinkedList<Point>();
		position.add(new Point(0,0));
		bearing = 90;
	}
	
	public void update() {
		moveSnake();
	}
	
	protected void extendSnake() {
		position.add(new Point(position.getLast()));
	}
	
	protected void moveSnake() {
		int tail = position.size() - 1;
		
		switch(bearing) {
		case 0:
			position.addFirst(position.pollLast());
			if (tail > 0) {
				position.getFirst().x = position.get(1).x;
				position.getFirst().y = position.get(1).y - 1;
			} else {
				position.getFirst().y -= 1;
				System.out.println("up");
			}
			break;
		case 90:
			position.addFirst(position.pollLast());
			if (tail > 0) {
				position.getFirst().x = position.get(1).x + 1;
				position.getFirst().y = position.get(1).y;
			} else {
				position.getFirst().x += 1;
				System.out.println("right");
			}
			break;
		case 180:
			position.addFirst(position.pollLast());
			if (tail > 0) {
				position.getFirst().x = position.get(1).x;
				position.getFirst().y = position.get(1).y + 1;
			} else {
				position.getFirst().y += 1;
				System.out.println("down");
			}
			break;
		case 270:
			position.addFirst(position.pollLast());
			if (tail > 0) {
				position.getFirst().x = position.get(1).x - 1;
				position.getFirst().y = position.get(1).y;
			} else {
				position.getFirst().x -= 1;
				System.out.println("left");
			}
			break;
		default:
			System.out.println("Not a valid bearing for only 4 directions");
		}
	}
	
	//should this be public
	public void setBearing(int bearing) {
		this.bearing = bearing;
	}
	
	public LinkedList<Point> getPosition() {
		return position;
	}
	
	public int getBearing() {
		return bearing;
	}
}
