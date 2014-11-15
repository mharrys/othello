import kth.game.othello.ClassicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

import java.util.List;

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
		return new AsciiGame(othello, formatter);
	}

	@Override
	public Game createOnePlayerGame() {
		OthelloFactory factory = new ClassicOthelloFactory();
		Othello othello = factory.createHumanVersusComputerGame();
		BoardFormatter formatter = createBoardFormatter(othello.getBoard(), othello.getPlayers());
		return new AsciiGame(othello, formatter);
	}

	@Override
	public Game createTwoPlayerGame() {
		OthelloFactory factory = new ClassicOthelloFactory();
		Othello othello = factory.createHumanGame();
		BoardFormatter formatter = createBoardFormatter(othello.getBoard(), othello.getPlayers());
		return new AsciiGame(othello, formatter);
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

}
