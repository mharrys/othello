package kth.demo;

import kth.game.AsciiOthelloGameFactory;
import kth.game.OthelloGame;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactoryImpl;

/**
 * Setup and run a game of Othello between two computers.
 *
 * @author Mattias Harrysson
 */
public class Demo1 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		Othello othello = new OthelloFactoryImpl().createComputerGame();
		OthelloGame othelloGame = new AsciiOthelloGameFactory().createGame(othello);
		othelloGame.start();
	}

}
