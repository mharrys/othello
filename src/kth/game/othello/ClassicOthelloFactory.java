package kth.game.othello;

import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
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

	@Override
	public Othello createComputerGame() {
		ClassicBoard board = createClassicBoard();
		Player player1 = createComputerPlayer("Computer 1");
		Player player2 = createComputerPlayer("Computer 2");
		board.reset(player1.getId(), player2.getId());
		return new ClassicOthello(board, player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		ClassicBoard board = createClassicBoard();
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createHumanPlayer("Player 2");
		board.reset(player1.getId(), player2.getId());
		return new ClassicOthello(board, player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		ClassicBoard board = createClassicBoard();
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createComputerPlayer("Computer 1");
		board.reset(player1.getId(), player2.getId());
		return new ClassicOthello(board, player1, player2);
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
	 * Creates a classic 8x8 Othello board
	 * 
	 * @return classic board
	 */
	private ClassicBoard createClassicBoard() {
		ArrayList<ClassicNode> nodes = new ArrayList<ClassicNode>();
		final int rows = 8;
		final int cols = 8;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				nodes.add(new ClassicNode(j, i));
			}		
		}
		return new ClassicBoard(nodes);
	}

}
