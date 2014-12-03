package kth.demo;

import kth.game.AsciiOthelloGameFactory;
import kth.game.OthelloGame;
import kth.game.OthelloGameFactory;

/**
 * Setup and run a game of Othello between a human and a computer.
 *
 * @author Mattias Harrysson
 */
public class Demo2 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		OthelloGameFactory factory = new AsciiOthelloGameFactory();
		OthelloGame othelloGame = factory.createOnePlayerGame();
		othelloGame.run();
	}

}