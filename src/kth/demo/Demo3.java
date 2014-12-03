package kth.demo;

import kth.game.AsciiOthelloGameFactory;
import kth.game.OthelloGame;
import kth.game.OthelloGameFactory;

/**
 * Setup and run a one player game of Othello between two humans.
 *
 * @author Mattias Harrysson
 */
public class Demo3 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		OthelloGameFactory factory = new AsciiOthelloGameFactory();
		OthelloGame othelloGame = factory.createTwoPlayerGame();
		othelloGame.run();
	}

}