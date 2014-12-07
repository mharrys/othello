package kth.tournament.score;

/**
 * An instance of this class contains the score of a player.
 *
 * @author Tomas Ekholm
 * @author Mattias Harrysson
 */
public class ScoreItem implements Comparable<ScoreItem> {

	private String playerId;
	private String playerName;
	private int score;

	public ScoreItem(String playerId, String playerName, int score) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.score = score;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(ScoreItem scoreItem) {
		if (score == scoreItem.score) {
			return 0;
		} else if (score > scoreItem.score) {
			return 1;
		} else {
			return -1;
		}
	}

}
