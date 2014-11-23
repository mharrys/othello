package kth.game.othello;

import kth.game.othello.board.Node;

import java.util.List;

/**
* This class is responsible for swapping nodes.
*
* @author Henrik Hygerth
*/
public interface NodeSwapper {

	/**
	 * Make move by specified player to specified node.
	 *
	 * @param nodesToSwap the nodes that will be swapped
	 * @param playerId the id of the moving player
	 * @param nodeId the id of the node to be placed
	 */
	public void swap(List<Node> nodesToSwap, String playerId, String nodeId);

}
