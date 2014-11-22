package kth.game.othello.score;

import java.util.List;
import java.util.Observable;

/**
 * The responsibility of this class is to control the score for the players in a Othello game. It will notify all
 * observers when a score is changed.
 *
 * @author Mattias Harrysson
 */
public class OthelloScore extends Observable implements Score {

	private List<ScoreItem> scores;

	public OthelloScore(List<ScoreItem> scores) {
		this.scores = scores;
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return scores;
	}

	@Override
	public int getPoints(String playerId) {
		for (ScoreItem item : scores) {
			if (item.getPlayerId().equals(playerId)) {
				return item.getScore();
			}
		}
		return 0;
	}

}
