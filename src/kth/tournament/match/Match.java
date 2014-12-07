package kth.tournament.match;

import java.util.List;
import java.util.Observer;

/**
 * The responsibility of this class is to control the match of competitors in a tournament. It must inform the outcome
 * of the match to all observers.
 *
 * @author Mattias Harrysson
 */
public interface Match {

	/**
	 * Adds an observer to the match. The observer will be notified of the outcome for a match played. In this case a
	 * {@link String} of the winning player id, or null if it was a draw.

	 * @param observer an observer to the game
	 */
	public void addObserver(Observer observer);

	public void start();

	public List<String> getCompetitorNames();

}
