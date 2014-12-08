package kth.game;

import kth.game.othello.view.swing.OthelloView;

/**
 * The responsibility of this class is to run a Othello game with a graphical user interface.
 *
 * @author Mattias Harrysson
 */
public class GuiOthelloGame implements OthelloGame {

	private OthelloView othelloView;

	/**
	 * @param othelloView othello view to use
	 */
	public GuiOthelloGame(OthelloView othelloView) {
		this.othelloView = othelloView;
	}

	@Override
	public void start() {
		this.othelloView.start();
	}

	@Override
	public void start(String playerId) {
		this.othelloView.start(playerId);
	}

}
