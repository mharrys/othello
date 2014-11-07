package kth.game.othello.player;

/**
 * Describes a computer controlled Othello player.
 */
public class ComputerPlayer implements Player {
	private String id;
	private String name;

	public ComputerPlayer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return Type.COMPUTER;
	}

}