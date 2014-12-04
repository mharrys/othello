package kth.demo;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * Starts Othello game between a human versus a computer on a classic board using a GUI.
 *
 * @author Mattias Harrysson
 */
public class Demo8 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		Othello othello = new OthelloFactoryImpl().createHumanVersusComputerGame();
		OthelloView othelloView = OthelloViewFactory.create(othello, 500, 1500);
		othelloView.start();
	}

}
