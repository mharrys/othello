package kth.game.othello;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * This class is responsible for finding nodes
 * 
 * @author Henrik Hygerth
 *
 */
public class NodeFinder {

	public NodeFinder() {
		
	}

	/**
	 * Returns node from specified id.
	 * 
	 * @param nodes the list of all the nodes
	 * @param nodeId the node id
	 * @return the node with specified id, or null if not found
	 */
	public Node getNodeFromId(List<Node> nodes, String nodeId) {
		Node result = null;
		for (Node node : nodes) {
			if (node.getId().equals(nodeId)) {
				result = node;
			}
		}

		return result;
	}

	/**
	 * Returns node from specified coordinates.
	 * 
	 * @param nodes the list of all the nodes
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return the node with on specified coordinates, or null if not found
	 */
	public Node getNodeFromGrid(List<Node> nodes, int x, int y) {
		Node result = null;
		for (Node node : nodes) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y) {
				result = node;
				break;
			}
		}

		return result;
	}

	/**
	 * Returns list of marked adjacent nodes to specified node.
	 * 
	 * @param nodes the list of all the nodes
	 * @param node the pivot node
	 * @return list of adjacent nodes to the pivot node that are marked
	 */
	public List<Node> getAdjacentMarkedNodes(List<Node> nodes, Node node) {
		List<Node> markedNodes = new ArrayList<Node>();

		final int x = node.getXCoordinate();
		final int y = node.getYCoordinate();

		for (Node n : nodes) {
			if (n.isMarked()) {
				final int adjX = n.getXCoordinate();
				final int adjY = n.getYCoordinate();
				final boolean deltaH = (adjX == x + 1 || adjX == x - 1);
				final boolean deltaV = (adjY == y + 1 || adjY == y - 1);
				final boolean adjHorizontal = deltaH && (adjY == y);
				final boolean adjVertical = deltaV && (adjX == x);
				final boolean adjDiagonal = deltaH && deltaV;
				if (adjDiagonal || adjHorizontal || adjVertical) {
					markedNodes.add(n);
				}
			}
		}

		return markedNodes;
	}

	/**
	 * Returns list of nodes around specified node that are the opposing player to the specified player.
	 * 
	 * @param nodes the list of all the nodes
	 * @param playerId the playing player id
	 * @param node the pivot node
	 * @return list of adjacent nodes to the pivot node that are occupied by the opponent player
	 */
	public List<Node> getAdjacentOpponentNodes(List<Node> nodes, String playerId, Node node) {
		List<Node> markedNodes = getAdjacentMarkedNodes(nodes, node);

		Iterator<Node> iter = markedNodes.iterator();
		while (iter.hasNext()) {
			Node n = iter.next();
			if (n.getOccupantPlayerId().equals(playerId)) {
				iter.remove();
			}
		}

		return markedNodes;
	}
}
