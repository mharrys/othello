package kth.tournament.presenter;

import kth.tournament.score.Score;

/**
 * The responsibility of this class is to present the given tournament result.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public interface ResultPresenter {

	public void present(Score score);

}
