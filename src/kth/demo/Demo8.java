package kth.demo;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * Starts an Othello game between two human players on a classic board using a GUI-view.
 */
public class Demo8 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		Othello othello = new OthelloFactoryImpl().createHumanGame();
		OthelloView othelloView = OthelloViewFactory.create(othello, 500, 1500);
		othelloView.start();
	}

}
