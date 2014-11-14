package kth.game.othello;

import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.ClassicBoardFactory;
import kth.game.othello.player.Player;

import java.util.UUID;

/**
 * A factory for producing classic othello games.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class ClassicOthelloFactory implements OthelloFactory {

	private static final String PLAYER1_NAME = "Player 1";
	private static final String PLAYER2_NAME = "Player 2";

	@Override
	public Othello createComputerGame() {
		Player player1 = createComputerPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createHumanPlayer(PLAYER2_NAME);
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		BoardFactory boardFactory = createClassicBoardFactory();
		return new ClassicOthello(boardFactory, player1, player2);
	}

	/**
	 * Creates a human player with specified name.
	 *
	 * @param name the name of the player
	 * @return human player
	 */
	private OthelloPlayer createHumanPlayer(String name) {
		return new OthelloPlayer(generateId(), name, Player.Type.HUMAN);
	}

	/**
	 * Creates a computer player with specified name.
	 *
	 * @param name the name of the player
	 * @return computer player
	 */
	private OthelloPlayer createComputerPlayer(String name) {
		return new OthelloPlayer(generateId(), name, Player.Type.COMPUTER);
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
