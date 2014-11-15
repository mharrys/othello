/**
 * Setup and run a one player game of Othello between a player and a computer.
 *
 * @author Mattias Harrysson
 */
public class OnePlayerGame {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createOnePlayerGame();
		game.run();
	}

}