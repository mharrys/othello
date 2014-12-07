package kth.tournament;

import kth.game.othello.Othello;

/**
 * The responsibility of this class is to output information about a othello game.
 * 
 * @author Henrik Hygerth
 */
public interface OthelloTournamentGameOutput {

	/**
	 * Called when a game is started.
	 * @param othello the current game
	 */
	public void onStart(Othello othello);

	/**
	 * Called when a game needs to draw itself.
	 * @param othello the current game
	 */
	public void onDraw(Othello othello);

	/**
	 * Called when a game has ended.
	 * @param othello the current game
	 */
	public void onEnd(Othello othello);
}
