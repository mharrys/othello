package kth.game.othello.board;

import java.util.List;

/**
 * This class is responsible for swapping nodes.
 *
 * @author Mattias Harrysson
 */
public class NodeSwapperImpl implements NodeSwapper {

	private List<NodeImpl> nodes;

	/**
	 * @param nodes the domain of nodes to perform swapping operations on
	 */
	public NodeSwapperImpl(List<NodeImpl> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void swap(List<Node> nodesToSwap, String playerId, String nodeId) {
		for (Node nodeToSwap : nodesToSwap) {
			for (NodeImpl node : nodes) {
				if (node.getId().equals(nodeToSwap.getId())) {
					node.mark(playerId);
					break;
				}
			}
		}
	}

}
