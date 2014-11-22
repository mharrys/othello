package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.Node;

/**
 * This class is responsible for swapping the nodes
 * 
 * @author Henrik Hygerth
 *
 */
public class NodeSwapper {

	private NodeFinder nodeFinder;

	/**
	 * Constructs node swapper from specified node finder.
	 *
	 * @param nodeFinder the node finder
	 */
	public NodeSwapper(NodeFinder nodeFinder) {
		this.nodeFinder = nodeFinder;	
	}

	/**
	 * Make move by specified player to specified node.
	 * 
	 * @param bf the board factory for constructing the new board
	 * @param board the current board
	 * @param nodesToSwap the nodes that should be swapped
	 * @param playerId the id of the moving player
	 * @param nodeId the id of the node to be placed
	 * @return the nodes that has been swapped
	 */
	public List<Node> swap(BoardFactory bf, Board board, List<Node> nodesToSwap, String playerId, String nodeId) {
		// include the node where the player made the move to be updated and returned
		nodesToSwap.add(nodeFinder.getNodeFromId(board.getNodes(), nodeId));

		board = bf.constructBoard(board.getNodes(), nodesToSwap, playerId);
		return nodesToSwap;
	}

}
