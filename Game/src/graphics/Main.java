package graphics;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//Switch to name of game
		JFrame gameWindow = new JFrame();
		GamePanel gamePanel = new GamePanel();
		gameWindow.setContentPane(gamePanel);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		gameWindow.pack();
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		gamePanel.start();
	}
}