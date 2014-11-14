package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.ClassicBoardFactory;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

import java.util.UUID;

/**
 * A factory for producing classic othello games.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class ClassicOthelloFactory implements OthelloFactory {
	
	public ClassicOthelloFactory() {
	}
	@Override
	public Othello createComputerGame() {
		Player player1 = createComputerPlayer("Computer 1");
		Player player2 = createComputerPlayer("Computer 2");
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createHumanPlayer("Player 2");
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createComputerPlayer("Computer 1");
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	/**
	 * Creates a human player with specified name.
	 *
	 * @param name the name of the player
	 * @return human player
	 */
	private HumanPlayer createHumanPlayer(String name) {
		return new HumanPlayer(generateId(), name);
	}

	/**
	 * Creates a computer player with specified name.
	 *
	 * @param name the name of the player
	 * @return computer player
	 */
	private ComputerPlayer createComputerPlayer(String name) {
		return new ComputerPlayer(generateId(), name);
	}

	/**
	 * Generates a unique id.
	 *
	 * @return unique id
	 */
	private String generateId() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Creates a classic board factory
	 * 
	 * @return classic board factory
	 */
	private ClassicBoardFactory createClassicBoardFactory() {
		return new ClassicBoardFactory(8, 8);
	}

}
