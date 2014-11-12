package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a classic 8x8 Othello board.
 * 
 * @author Henrik Hygerth
 * @author Mattias Harrysson
 */
public class ClassicBoard implements Board {

	private final int rows = 8;
	private final int cols = 8;
	private List<ClassicNode> nodes;
	
	/**
	 * Construct a classic 8x8 Othello board.
	 * 
	 * @param nodes list of nodes for the board
	 */
	public ClassicBoard(List<ClassicNode> nodes) {
		this.nodes = nodes;
	}
	
	@Override
	public List<Node> getNodes() {
		return (List<Node>) (Object) nodes;
	}

	/**
	 * Resets this board to starting state with four discs placed in the middle of the board which belongs to specified
	 * players.
	 *
	 * @param playerId1 the id of player 1
	 * @param playerId2 the id of player 2
	 */
	public void reset(String playerId1, String playerId2) {
		for (ClassicNode n : nodes) {
			n.unmark();
		}

		// place four discs in the middle of the board
		getNode(3, 3).mark(playerId1);
		getNode(4, 3).mark(playerId2);
		getNode(3, 4).mark(playerId2);
		getNode(4, 4).mark(playerId1);
	}

	/**
	 * Swaps node in specified list to specified playerId.
	 *
	 * @param nodesToSwap the list of nodes to swap
	 * @param playerId the player id to mark with
	 */
	public void swapNodes(List<Node> nodesToSwap, String playerId) {
		for (Node n : nodesToSwap) {
			int x = n.getXCoordinate();
			int y = n.getYCoordinate();
			getNode(x, y).mark(playerId);
		}
	}

	/**
	 * Returns the number of rows in the board.
	 *
	 * @return number of rows
	 */
	public int getNumRows() {
		return rows;
	}

	/**
	 * Returns the number of columns in the board.
	 *
	 * @return number of columns
	 */
	public int getNumCols() {
		return cols;
	}

	/**
	 * Returns node on the specified coordinates in the grid.
	 *
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return node on the specified coordinates, or null if coordinates are invalid
	 */
	public ClassicNode getNode(int x, int y) {
		if (x < 0 || x >= cols || y < 0 || y >= rows) {
			return null;
		}
		return nodes.get(rows * y + x);
	}

	/**
	 * Returns node from specified id.
	 *
	 * @param nodeId the node id
	 * @return the node with specified id, or null if not found
	 */
	public Node getNodeFromId(String nodeId) {
		Node result = null;

		for (Node node : nodes) {
			if (node.getId() == nodeId) {
				result = node;
				break;
			}
		}

		return result;
	}

	/**
	 * Returns list of marked adjacent nodes to specified node.
	 *
	 * @param node the pivot node
	 * @return list of adjacent nodes to the pivot node that are marked
	 */
	public List<Node> getAdjacentMarkedNodes(Node node) {
		List<Node> nodes = new ArrayList<Node>();

		final int x = node.getXCoordinate();
		final int y = node.getYCoordinate();

		for (Node n : nodes) {
			if (n.isMarked()) {
				final int adjX = n.getXCoordinate();
				final int adjY = n.getYCoordinate();
				if (adjX == x + 1 || adjX == x - 1 || adjY == y + 1 || adjY == y - 1) {
					nodes.add(n);
				}
			}
		}

		return nodes;
	}

}
