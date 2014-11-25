package kth.game.othello.board;

import java.util.*;

/**
 * Describes a classic 8x8 Othello board.
 * 
 * @author Henrik Hygerth
 * @author Mattias Harrysson
 */
public class ClassicBoard implements Board {

	private List<Node> nodes;
	private HashMap<String, Character> colors;
	private int cols;
	
	/**
	 * Construct a classic 8x8 Othello board.
	 * 
	 * @param nodes list of nodes for the board
	 * @param colors hash map with player id mapped to colors
	 */
	public ClassicBoard(List<Node> nodes, HashMap<String, Character> colors) {
		this.nodes = nodes;
		this.colors = colors;
		computeNumCols();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("  ");
		for (int i = 0; i < cols; i++) {
			builder.append(i + " ");
		}

		int row = -1;
		int col = 0;
		for (Node node : nodes) {
			int x = node.getXCoordinate();
			int y = node.getYCoordinate();

			if (row != y) {
				row = y;
				col = 0;
				builder.append("\n" + row + " ");
			}

			while (x > col) {
				builder.append("  ");
				col++;
			}
			col++;

			if (!node.isMarked()) {
				builder.append('.');
			} else {
				builder.append(colors.get(node.getOccupantPlayerId()));
			}

			builder.append(' ');
		}

		return builder.toString();
	}

	private void computeNumCols() {
		for (Node node : nodes) {
			if (node.getXCoordinate() > cols) {
				cols = node.getXCoordinate();
			}
		}
		// adjust for zero based coordinates
		cols++;
	}

}
