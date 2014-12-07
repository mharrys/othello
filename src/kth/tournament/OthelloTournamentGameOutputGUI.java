package kth.tournament;

import kth.game.othello.Othello;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * The responsibility of this class is to output information about a othello game to a OthelloView.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournamentGameOutputGUI implements OthelloTournamentGameOutput {

	private OthelloView othelloView;

	@Override
	public void onStart(Othello othello) {
		othelloView = OthelloViewFactory.create(othello, 100, 100);
		othelloView.start(othello.getPlayerInTurn().getId());
	}

	@Override
	public void onDraw(Othello othello) {
		return;
	}

	@Override
	public void onEnd(Othello othello) {
		return;
	}

}
