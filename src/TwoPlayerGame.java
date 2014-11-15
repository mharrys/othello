/**
 * Setup and run a one player game of Othello between two humans.
 *
 * @author Mattias Harrysson
 */
public class TwoPlayerGame {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createTwoPlayerGame();
		game.run();
	}

}