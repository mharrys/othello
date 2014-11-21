package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;

/**
 * This class is responsible for calculating nodes to capture
 * 
 * @author Henrik Hygerth
 */
public class NodeCapturer {

	private NodeFinder nodeFinder;
	private int cols = 8;
	private int rows = 8;

	public NodeCapturer(NodeFinder nodeFinder) {
		this.nodeFinder = nodeFinder;
	}

	/**
	 * Returns the captured nodes that a player can make from the specified empty node in the specified direction which
	 * is occupied by the opponent player.
	 *
	 * @param nodes list of all the nodes
	 * @param playerId the moving player id
	 * @param from the empty node
	 * @param direction the adjacent node occupied by the moving player opponent
	 * @return list of nodes to be captured in this direction by the empty node
	 */
	public List<Node> nodesToCaptureInDirection(List<Node> nodes, String playerId, Node from, Node direction) {
		List<Node> captures = new ArrayList<Node>();

		int x = from.getXCoordinate();
		int y = from.getYCoordinate();
		int adjX = direction.getXCoordinate();
		int adjY = direction.getYCoordinate();

		int stepX = adjX - x;
		int stepY = adjY - y;
		// start looking on the next adjacent node
		x += stepX;
		y += stepY;

		boolean validCapture = false;
		while (x >= 0 && y >= 0 && x < cols && y < rows) {
			Node n = nodeFinder.getNodeFromGrid(nodes, x, y);

			if (!n.isMarked()) {
				// we hit a unmarked node before finding a node which was occupied by one of the moving players
				break;
			} else if (n.getOccupantPlayerId().equals(direction.getOccupantPlayerId())) {
				captures.add(n);
			} else if (n.getOccupantPlayerId().equals(playerId)) {
				validCapture = true;
				break;
			}

			x += stepX;
			y += stepY;
		}

		if (validCapture) {
			return captures;
		} else {
			// could not embrace one or more opponent nodes
			captures.clear();
			return captures;
		}
	}

	/**
	 * Returns the captured nodes surrounding the specified empty node in all directions which is occupied by the
	 * opponent player.
	 *
	 * @param nodes list of all the captured nodes
	 * @param playerId the moving player id
	 * @param nodeId the empty node
	 *
	 * @return list of nodes to be captured surrounding the empty node
	 */
	List<Node> getNodesToCapture(List<Node> nodes, String playerId, String nodeId) {
		List<Node> captures = new ArrayList<Node>();

		final Node startNode = nodeFinder.getNodeFromId(nodes, nodeId);
		if (startNode == null || startNode.isMarked()) {
			return captures;
		}

		for (Node node : nodeFinder.getAdjacentOpponentNodes(nodes, playerId, startNode)) {
			captures.addAll(nodesToCaptureInDirection(nodes, playerId, startNode, node));
		}

		return captures;
	}

}
