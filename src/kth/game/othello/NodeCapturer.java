package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This class is responsible for calculating nodes to capture
 * 
 * @author Henrik Hygerth
 */
public class NodeCapturer {

	private NodeFinder nodeFinder;

	/**
	 * Constructs node capturer from specified node finder.
	 *
	 * @param nodeFinder the node finder
	 */
	public NodeCapturer(NodeFinder nodeFinder) {
		this.nodeFinder = nodeFinder;
	}

	/**
	 * Returns the captured nodes that a player can make from the specified empty node in the specified direction which
	 * is occupied by the opponent player.
	 *
	 * @param board the board with nodes
	 * @param playerId the moving player id
	 * @param from the empty node
	 * @param direction the adjacent node occupied by the moving player opponent
	 * @return list of nodes to be captured in this direction by the empty node
	 */
	public List<Node> getNodesToCaptureInDirection(Board board, String playerId, Node from, Node direction) {
		List<Node> captures = new ArrayList<Node>();

		int rows = 0;
		int cols = 0;
		for (Node node : board.getNodes()) {
			if (node.getXCoordinate() > cols) {
				cols = node.getXCoordinate();
			}

			if (node.getYCoordinate() > rows) {
				rows = node.getYCoordinate();
			}
		}
		// adjust for zero based coordinates
		rows++;
		cols++;

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
			Node n = board.getNode(x, y);

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
	 * @param board the board with nodes
	 * @param playerId the moving player id
	 * @param nodeId the empty node
	 *
	 * @return list of nodes to be captured surrounding the empty node
	 */
	List<Node> getNodesToCapture(Board board, String playerId, String nodeId) {
		List<Node> captures = new ArrayList<Node>();

		final Node startNode = nodeFinder.getNodeFromId(board.getNodes(), nodeId);
		if (startNode == null || startNode.isMarked()) {
			return captures;
		}

		for (Node node : nodeFinder.getAdjacentOpponentNodes(board.getNodes(), playerId, startNode)) {
			captures.addAll(getNodesToCaptureInDirection(board, playerId, startNode, node));
		}

		return captures;
	}

}
