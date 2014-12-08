package kth.game;

/**
 * The responsibility of this class is to run a Othello game from start to finish.
 *
 * @author Mattias Harrysson
 */
public interface OthelloGame {

	/**
	 * Start a Othello game with random starting player.
	 */
	public void start();

	/**
	 * Start a Othello game with a specified starting player.
	 *
	 * @param playerId the starting player id
	 */
	public void start(String playerId);

}
