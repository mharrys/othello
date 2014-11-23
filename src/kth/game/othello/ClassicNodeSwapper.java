package kth.game.othello;

import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;

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
		for (ClassicNode node : nodes) {
			for (Node nodeToSwap : nodesToSwap) {
				if (node.getId().equals(nodeToSwap.getId())) {
					node.mark(playerId);
				}
			}
		}
	}

}
