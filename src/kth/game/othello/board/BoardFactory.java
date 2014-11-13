package kth.game.othello.board;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

/**
 * A factory for producing boards.
 * 
 * @author Henrik Hygerth
 */
public interface BoardFactory {

	/**
	 * Creates an Board.
	 * 
	 * @return An Board game
	 */
	public Board constructBoard(Player player1, Player player2);

	/**
	 * Reconstructs a Board.
	 * 
	 * @return An Board game
	 */
	public Board constructBoard(List<Node> nodes, List<Node> nodesToSwap, String playerId);
	
	/**
	 * 
	 * @return
	 */
	public int getRows();
	
	/**
	 * 
	 * @return
	 */
	public int getCols();
}