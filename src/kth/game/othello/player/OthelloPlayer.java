package kth.game.othello.player;

/**
 * Describes a Othello player.
 *
 * @author Mattias Harrysson
 */
public class OthelloPlayer implements Player {

	private String id;
	private String name;
	private Type type;

	/**
	 * Constructs a Othello player with specified id, name and type.
	 *
	 * @param id the player id
	 * @param name the player name
	 * @param type the type of player
	 */
	public OthelloPlayer(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
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
		return type;
	}

}
