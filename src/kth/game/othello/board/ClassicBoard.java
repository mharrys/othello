package kth.game.othello.board;

import java.util.List;

/**
 * Describes a classic 8x8 Othello board.
 * 
 * @author Henrik Hygerth
 * @author Mattias Harrysson
 */
public class ClassicBoard implements Board {

	private List<Node> nodes;
	
	/**
	 * Construct a classic 8x8 Othello board.
	 * 
	 * @param nodes list of nodes for the board
	 */
	public ClassicBoard(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Node getNode(int x, int y) throws IllegalArgumentException {
		for (Node node : nodes) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y) {
				return node;
			}
		}

		throw new IllegalArgumentException("No node with coordinates " + x + "-" + y + ".");
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
