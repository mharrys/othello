/**
 * Setup and run a one player game of Othello between two computers.
 *
 * @author Mattias Harrysson
 */
public class ComputerGame {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createComputerGame();
		game.run();
	}

}
