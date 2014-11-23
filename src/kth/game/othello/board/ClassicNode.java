package kth.game.othello.board;

import java.util.Observable;

/**
 * Describes a node in a classic 8x8 Othello board. It will inform all observers when a new player is assigned to this
 * node.
 * 
 * @author Henrik Hygerth
 * @author Mattias Harrysson
 */
public class ClassicNode extends Observable implements Node {
	
	private String id;
	private String occupantId;
	private int x;
	private int y;
	private boolean marked;
	
	/**
	 * Construct a node in a classic Othello board
	 * 
	 * @param x the x-coordinate on the board
	 * @param y the y-coordinate on the board
	 */
	public ClassicNode(int x, int y) {
		this.id = x + "-" + y;
		this.x = x;
		this.y = y;
		unmark();
	}
	
	public ClassicNode(int x, int y, String playerId) {
		this.id = x + "-" + y;
		this.x = x;
		this.y = y;
		mark(playerId);
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

	/**
	 * Sets node state to marked by specified playerId.
	 *
	 * @param playerId the player to occupy this node
	 */
	public void mark(String playerId) {
		notifyObservers(occupantId);
		occupantId = playerId;
		marked = true;
	}

	/**
	 * Sets node state to unmarked.
	 */
	public void unmark() {
		occupantId = null;
		marked = false;
	}

}