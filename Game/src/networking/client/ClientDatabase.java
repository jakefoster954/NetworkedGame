package networking.client;

import gamelogic.GameData;

public class ClientDatabase {

	private int[][] tm;
	private GameData snake;
	
	public int[][] getTm() {
		return tm;
	}
	
	protected void setTm(String s) {
		String[] temp = s.split("\n");
		int height = temp.length;
		if (height <= 0) {
			return;
		}
		
		String[] r0 = temp[0].split(" ");
		int width = r0.length;
		if (width <= 0) {
			return;
		}
		
		int[][] tm = new int[height][width];
		for (int i =0; i < height; i++) {
			String[] row = temp[i].split(" ");
			for (int j = 0; j < width; j++) {
				tm[i][j] = Integer.parseInt(row[j]);
			}
		}
		this.tm = tm;
	}
	
	public GameData getSnake() {
		return snake;
	}
	
	protected void setSnake(GameData snake) {
		this.snake = snake;
	}
	
}
