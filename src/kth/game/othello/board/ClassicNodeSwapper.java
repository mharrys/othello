package kth.game.othello.board;

import java.util.List;

/**
 * This class is responsible for swapping nodes.
 *
 * @author Mattias Harrysson
 */
public class ClassicNodeSwapper implements NodeSwapper {

	private List<ClassicNode> nodes;

	public ClassicNodeSwapper(List<ClassicNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void swap(List<Node> nodesToSwap, String playerId, String nodeId) {
		for (Node nodeToSwap : nodesToSwap) {
			for (ClassicNode node : nodes) {
				if (node.getId().equals(nodeToSwap.getId())) {
					node.mark(playerId);
					break;
				}
			}
		}
	}

}
