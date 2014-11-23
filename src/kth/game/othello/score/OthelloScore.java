package kth.game.othello.score;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Collections;

/**
 * The responsibility of this class is to control the score for the players in a Othello game. It will notify all
 * observers when a score is changed.
 *
 * @author Mattias Harrysson
 */
public class OthelloScore extends Observable implements Score, Observer {

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

	@Override
	public void update(Observable observable, Object object) {
		if (object == null) {
			return;
		}

		List<String> changes = (List<String>) object;
		String prevPlayerId = changes.get(0);
		String nextPlayerId = changes.get(1);
		updateScore(prevPlayerId, nextPlayerId);

		if (prevPlayerId == null) {
			notifyObservers(Arrays.asList(nextPlayerId));
		} else {
			notifyObservers(Arrays.asList(prevPlayerId, nextPlayerId));
		}

		// highest score first
		Collections.sort(scores, Collections.reverseOrder());
	}

	private void updateScore(String prevPlayerId, String nextPlayerId) {
		for (int i = 0; i <  scores.size(); i++) {
			ScoreItem item = scores.get(i);
			if (item.getPlayerId().equals(prevPlayerId) && item.getScore() > 0) {
				scores.set(i, new ScoreItem(item.getPlayerId(), item.getScore() - 1));
				setChanged();
			} else if (item.getPlayerId().equals(nextPlayerId)) {
				scores.set(i, new ScoreItem(item.getPlayerId(), item.getScore() + 1));
				setChanged();
			}
		}
	}

}
