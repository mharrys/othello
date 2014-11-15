/**
 * A factory for producing complete othello games.
 *
 * @author Mattias Harrysson
 */
public interface GameFactory {

	/**
	 * Creates a complete Othello game with two computers.
	 *
	 * @return complete Othello game
	 */
	public Game createComputerGame();

	/**
	 * Creates a complete Othello game with one computer playing against one human.
	 *
	 * @return complete Othello game
	 */
	public Game createOnePlayerGame();

}
