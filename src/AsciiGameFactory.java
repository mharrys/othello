import kth.game.othello.ClassicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

import java.util.List;
import java.util.Scanner;

/**
 * A factory for producing classic ASCII othello games.
 *
 * @author Mattias Harrysson
 */
public class AsciiGameFactory implements GameFactory {

	@Override
	public Game createComputerGame() {
		OthelloFactory factory = new ClassicOthelloFactory();
		Othello othello = factory.createComputerGame();
		BoardFormatter formatter = createBoardFormatter(othello.getBoard(), othello.getPlayers());
		Scanner reader = createInputReader();
		return new AsciiGame(othello, formatter, reader);
	}

	@Override
	public Game createOnePlayerGame() {
		OthelloFactory factory = new ClassicOthelloFactory();
		Othello othello = factory.createHumanVersusComputerGame();
		BoardFormatter formatter = createBoardFormatter(othello.getBoard(), othello.getPlayers());
		Scanner reader = createInputReader();
		return new AsciiGame(othello, formatter, reader);
	}

	@Override
	public Game createTwoPlayerGame() {
		OthelloFactory factory = new ClassicOthelloFactory();
		Othello othello = factory.createHumanGame();
		BoardFormatter formatter = createBoardFormatter(othello.getBoard(), othello.getPlayers());
		Scanner reader = createInputReader();
		return new AsciiGame(othello, formatter, reader);
	}

	/**
	 * Creates ASCII board formatter with the specified board and a selected starting player from specified players.
	 *
	 * @param board the board to delegate
	 * @param players the players to select from
	 * @return ascii board formatter
	 */
	private BoardFormatter createBoardFormatter(Board board, List<Player> players) {
		return new AsciiBoardFormatter(board, players.get(1).getId());
	}

	/**
	 * Creates input reader.
	 *
	 * @return scanner reading from stdin
	 */
	private Scanner createInputReader() {
		return new Scanner(System.in);
	}

}
