package kth.game;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import java.util.List;

/**
 * Presents the board state using the ASCII character set.
 *
 * @author Mattias Harrysson
 */
public class AsciiBoardFormatter implements BoardFormatter {

	private String startingPlayerId;

	/**
	 * Construct a ASCII board formatter with specified starting player id (black player).
	 *
	 * @param startingPlayerId the starting player id
	 */
	public AsciiBoardFormatter(String startingPlayerId) {
		this.startingPlayerId = startingPlayerId;
	}

	@Override
	public void format(Board board) {
		List<Node> nodes = board.getNodes();

		int cols = 0;
		for (Node node : nodes) {
			if (node.getXCoordinate() > cols) {
				cols = node.getXCoordinate();
			}
		}
		// adjust for zero based coordinates
		cols++;

		System.out.print("  ");
		for (int i = 0; i < cols; i++) {
			System.out.print(i + " ");
		}

		int row = -1;
		int col = 0;
		for (Node node : nodes) {
			int x = node.getXCoordinate();
			int y = node.getYCoordinate();

			if (row != y) {
				row = y;
				col = 0;
				System.out.println();
				System.out.print(row + " ");
			}

			while (x > col) {
				System.out.print("  ");
				col++;
			}
			col++;

			if (!node.isMarked()) {
				System.out.print('.');
			} else {
				// TODO: arbitrary number of player colors
				System.out.print(node.getOccupantPlayerId().equals(startingPlayerId) ? 'b' : 'w');
			}

			System.out.print(' ');
		}
		System.out.println();
	}

}
