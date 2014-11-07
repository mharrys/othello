package kth.game.othello.board;

/**
 * Describes a node in a classic 8x8 Othello board.
 */
public class ClassicNode implements Node {

	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getOccupantPlayerId() {
		return null;
	}

	@Override
	public int getXCoordinate() {
		return 0;
	}

	@Override
	public int getYCoordinate() {
		return 0;
	}

	@Override
	public boolean isMarked() {
		return false;
	}

}