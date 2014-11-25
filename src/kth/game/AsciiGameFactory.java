package kth.game;

import kth.game.othello.ClassicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.player.Player;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * A factory for producing classic ASCII othello games.
 *
 * @author Mattias Harrysson
 */
public class AsciiGameFactory implements GameFactory {

	@Override
	public Game createComputerGame() {
		return createGame(createFactory().createComputerGame());
	}

	@Override
	public Game createOnePlayerGame() {
		return createGame(createFactory().createHumanVersusComputerGame());
	}

	@Override
	public Game createTwoPlayerGame() {
		return createGame(createFactory().createHumanGame());
	}

	@Override
	public Game createGame(Set<NodeData> nodesData, List<Player> players) {
		return createGame(createFactory().createGame(nodesData, players));
	}

	private Game createGame(Othello othello) {
		Scanner reader = createInputReader();
		return new AsciiGame(othello, reader);
	}

	private OthelloFactory createFactory() {
		return new ClassicOthelloFactory();
	}

	private Scanner createInputReader() {
		return new Scanner(System.in);
	}

}
