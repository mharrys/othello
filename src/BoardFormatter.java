import kth.game.othello.board.Board;

/**
 * This class handles the visual presentation of the board.
 *
 * @author Mattias Harrysson
 */
public interface BoardFormatter {

	/**
	 * Presents the visualization of the board.
	 *
	 * @param board the board to visualize
	 */
	public void format(Board board);

}
