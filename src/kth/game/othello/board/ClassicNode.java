package kth.game.othello.board;

/**
 * Describes a node in a classic 8x8 Othello board.
 */
public class ClassicNode implements Node {
	
	private String id;
	private String occupantId;
	private int x;
	private int y;
	private boolean marked;
	
	/**
	 * Construct a node in a classic Othello board
	 * 
	 * @param id the id for the node
	 * @param x the x-coordinate on the board
	 * @param y the y-coordinate on the board
	 */
	public ClassicNode(String id, int x, int y) {
		this.id = id;
		this.occupantId = "";
		this.x = x;
		this.y = y;
		this.marked = false;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantId;
	}

	@Override
	public int getXCoordinate() {
		return x;
	}

	@Override
	public int getYCoordinate() {
		return y;
	}

	@Override
	public boolean isMarked() {
		return marked;
	}

}