package graphics;

import java.util.Scanner;

public class Menu {
	private Scanner s;

	private String home = "### SNAKES ###" + "\n" +
					"1. Play Game" + "\n" +
					"2. Help" + "\n" +
					"3. Quit" + "\n" +
					">> ";

	private String name = "### NAME ###" + "\n" +
					"Please enter your name" + "\n" +
					">> ";
	
	private String help =	"### HELP ###" + "\n" + "Stop being a baby and figure out the GUI!" + "\n" +
					"Press 'Enter' to return to main menu." + "\n" +
					">> ";

	private String lobby1a = "### LOBBY ###";
	
	private String lobby1b = "1. Create Lobby" + "\n" +
					"2. Join Lobby" + "\n" +
					"3. QUIT" + "\n" +
					">> ";
	
	public Menu() {
		s = new Scanner(System.in);
		home();

	}

	private void home() {
		String input = "";
		while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
			System.out.print(home);
			input = s.nextLine();
			switch (input) {
			case "1":
				name();
				break;
			case "2":
				help();
				break;
			case "3":
				break;
			default:
				System.out.println("INVALID INPUT");
			}
		}
	}

	private void name() {
		String input = "";
		System.out.print(name);
		input = s.nextLine();
		lobby();
	}
	
	private void help() {
		System.out.print(help);
		s.nextLine();
		home();
	}

	private void lobby() {
		String input = "";
		while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
			System.out.print(lobby1a);
			System.out.println();
			//update the current lobby data. Not sure where it is stored yet.
			System.out.print(lobby1b);
			input = s.nextLine();
			switch (input) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			default:
				System.out.println("INVALID INPUT");
			}
		}
		
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}
