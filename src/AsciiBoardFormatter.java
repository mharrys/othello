import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import java.util.List;

/**
 * Presents the board state using the ASCII character set.
 *
 * @author Mattias Harrysson
 */
public class AsciiBoardFormatter implements BoardFormatter {

	private Board board;
	private String startingPlayerId;
	private int rows;
	private int cols;

	/**
	 * Construct a ASCII board formatter from specified board and starting player id (black player).
	 *
	 * @param board the board to read from
	 * @param startingPlayerId the starting player id
	 */
	public AsciiBoardFormatter(Board board, String startingPlayerId) {
		this.board = board;
		this.startingPlayerId = startingPlayerId;

		rows = 0;
		cols = 0;
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
	}

	@Override
	public void present() {
		List<Node> nodes = board.getNodes();

		System.out.print("  ");
		for (int i = 0; i < cols; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		int row = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if (i % rows == 0) {
				System.out.print(row + " ");
				row++;
			}

			Node node = nodes.get(i);
			if (!node.isMarked()) {
				System.out.print('.');
			} else {
				System.out.print(node.getOccupantPlayerId() == startingPlayerId ? 'b' : 'w');
			}

			if ((i + 1) % cols == 0) {
				System.out.print('\n');
			} else {
				System.out.print(' ');
			}
		}
		System.out.print('\n');
	}

}
