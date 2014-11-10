package kth.game.othello.board;

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
	public List<Node> getNodes() {
		return nodes;
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
	public Node getNode(int x, int y) {
		if (x < 0 || x >= rows || y < 0 || y >= cols) {
			return null;
		}
		return nodes.get(cols * x + y);
	}

}
