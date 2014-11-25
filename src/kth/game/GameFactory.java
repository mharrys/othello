package kth.game;

import kth.game.othello.board.factory.NodeData;
import kth.game.othello.player.Player;

import java.util.List;
import java.util.Set;

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

	/**
	 * Creates a complete Othello game with two humans.
	 *
	 * @return complete Othello game
	 */
	public Game createTwoPlayerGame();

	/**
	 * Creates a custom Othello game.
	 *
	 * @param nodes the nodes in the board
	 * @param players the participating players
	 * @return custom Othello game
	 */
	public Game createGame(Set<NodeData> nodes, List<Player> players);

}
