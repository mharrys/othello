package kth.tournament.presenter;

import kth.tournament.score.Score;
import kth.tournament.score.ScoreItem;

/**
 * The responsibility of this class is to present the given tournament result to standard output.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class AsciiResultPresenter implements ResultPresenter {

	public void present(Score score) {
		System.out.println();
		System.out.print("Tournament result: ");
		for (ScoreItem item : score.getPlayersScore()) {
			System.out.print(item.getPlayerName() + " (" + item.getScore() + ") ");
		}
		System.out.println();
	}

}
