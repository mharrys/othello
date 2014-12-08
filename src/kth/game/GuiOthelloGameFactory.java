package kth.game;

import kth.game.othello.Othello;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * A factory for producing Othello games with a graphical user interface.
 *
 * @author Mattias Harrysson
 */
public class GuiOthelloGameFactory implements OthelloGameFactory {

	@Override
	public OthelloGame createGame(Othello othello) {
		OthelloView othelloView = OthelloViewFactory.create(othello, 500, 1500);
		return new GuiOthelloGame(othelloView);
	}

}
