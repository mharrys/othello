package kth.game.othello;

import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.Node;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;

import java.util.ArrayList;
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
		ClassicBoard board = createClassicBoard();
		Player player1 = createComputerPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		return new ClassicOthello(board, player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		ClassicBoard board = createClassicBoard();
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createHumanPlayer(PLAYER2_NAME);
		return new ClassicOthello(board, player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		ClassicBoard board = createClassicBoard();
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		return new ClassicOthello(board, player1, player2);
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
	 * Creates a classic 8x8 Othello board
	 * 
	 * @return classic board
	 */
	private ClassicBoard createClassicBoard() {
		ArrayList<Node> nodes = new ArrayList<Node>();
		final int rows = 8;
		final int cols = 8;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node node = new ClassicNode(j, i);
				nodes.add(node);
			}		
		}
		return new ClassicBoard(nodes);
	}

}
