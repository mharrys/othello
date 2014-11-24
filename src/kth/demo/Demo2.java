package kth.demo;

import kth.game.AsciiGameFactory;
import kth.game.Game;
import kth.game.GameFactory;

/**
 * Setup and run a one player game of Othello between a human and a computer.
 *
 * @author Mattias Harrysson
 */
public class Demo2 {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createOnePlayerGame();
		game.run();
	}

}