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
public interface OthelloGameFactory {

	/**
	 * Creates a Othello game with two computers.
	 *
	 * @return Othello game
	 */
	public OthelloGame createComputerGame();

	/**
	 * Creates a Othello game with one computer playing against one human.
	 *
	 * @return Othello game
	 */
	public OthelloGame createOnePlayerGame();

	/**
	 * Creates a Othello game with two humans.
	 *
	 * @return Othello game
	 */
	public OthelloGame createTwoPlayerGame();

	/**
	 * Creates a custom Othello game.
	 *
	 * @param nodes the nodes in the board
	 * @param players the participating players
	 * @return Othello game
	 */
	public OthelloGame createGame(Set<NodeData> nodes, List<Player> players);

}
