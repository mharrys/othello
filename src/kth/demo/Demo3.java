package kth.demo;

import kth.game.AsciiGameFactory;
import kth.game.Game;
import kth.game.GameFactory;

/**
 * Setup and run a one player game of Othello between two humans.
 *
 * @author Mattias Harrysson
 */
public class Demo3 {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createTwoPlayerGame();
		game.run();
	}

}