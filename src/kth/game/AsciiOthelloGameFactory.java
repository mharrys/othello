package kth.game;

import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.player.Player;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * A factory for producing ASCII Othello games.
 *
 * @author Mattias Harrysson
 */
public class AsciiOthelloGameFactory implements OthelloGameFactory {

	@Override
	public OthelloGame createComputerGame() {
		return createGame(createFactory().createComputerGame());
	}

	@Override
	public OthelloGame createOnePlayerGame() {
		return createGame(createFactory().createHumanVersusComputerGame());
	}

	@Override
	public OthelloGame createTwoPlayerGame() {
		return createGame(createFactory().createHumanGame());
	}

	@Override
	public OthelloGame createGame(Set<NodeData> nodesData, List<Player> players) {
		return createGame(createFactory().createGame(nodesData, players));
	}

	private OthelloGame createGame(Othello othello) {
		Scanner scanner = createInputScanner();
		return new AsciiOthelloGame(othello, scanner);
	}

	private OthelloFactory createFactory() {
		return new OthelloFactoryImpl();
	}

	private Scanner createInputScanner() {
		return new Scanner(System.in);
	}

}
