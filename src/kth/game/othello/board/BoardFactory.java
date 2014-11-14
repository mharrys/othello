package kth.game.othello.board;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * A factory for producing boards.
 * 
 * @author Henrik Hygerth
 */
public interface BoardFactory {

	/**
	 * Creates an board in a starting state from two specified players.
	 *
	 * @param player1 the first player
	 * @param player2 the second player
	 * @return constructed board game
	 */
	public Board constructBoard(int rows, int cols, Player player1, Player player2);

	/**
	 * Reconstructs a board with a collection of nodes to be occupied by specified player.
	 *
	 * @param nodesToSwap the nodes to be occupied by the player
	 * @param playerId the player to occupy the nodes
	 * @return constructed board game
	 */
	public Board constructBoard(List<Node> nodes, List<Node> nodesToSwap, String playerId);

}