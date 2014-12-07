package kth.tournament.score;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The responsibility of this class is to control the score for the Othello players. It must inform all observers when
 * a score has changed.
 *
 * @author Mattias Harrysson
 */
public class OthelloScore extends Observable implements Score, Observer {

	private List<ScoreItem> scores;

	/**
	 * @param scores the list of score items to track
	 */
	public OthelloScore(List<ScoreItem> scores) {
		this.scores = scores;
		updateOrder();
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
		updateScore((String) object);

		setChanged();
		notifyObservers();
	}

	/**
	 * Updates the score for a winning player.
	 *
	 * @param playerId the winning player id
	 */
	private void updateScore(String playerId) {
		for (int i = 0; i <  scores.size(); i++) {
			ScoreItem item = scores.get(i);
			if (item.getPlayerId().equals(playerId)) {
				scores.set(i, new ScoreItem(item.getPlayerId(), item.getPlayerName(), item.getScore() + 1));
				break;
			}
		}
	}

	/**
	 * Sorts score list in descending order.
	 */
	private void updateOrder() {
		Collections.sort(scores, Collections.reverseOrder());
	}

}