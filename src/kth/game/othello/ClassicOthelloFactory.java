package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.Node;
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
		Board newBoard = createClassicBoard();
		Player player1 = createComputerPlayer("Computer 1");
		Player player2 = createComputerPlayer("Computer 2");
		return new ClassicOthello(newBoard, player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		Board newBoard = createClassicBoard();
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createHumanPlayer("Player 2");
		return new ClassicOthello(newBoard, player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Board newBoard = createClassicBoard();
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createComputerPlayer("Computer 1");
		return new ClassicOthello(newBoard, player1, player2);
	}

	/**
	 * Creates a human player with specified name.
	 *
	 * @param name the name of the player
	 * @return human player
	 */
	private HumanPlayer createHumanPlayer(String name) {
		return new HumanPlayer(generatePlayerId(), name);
	}

	/**
	 * Creates a computer player with specified name.
	 *
	 * @param name the name of the player
	 * @return computer player
	 */
	private ComputerPlayer createComputerPlayer(String name) {
		return new ComputerPlayer(generatePlayerId(), name);
	}

	/**
	 * Generates a unique player id.
	 *
	 * @return unique player id
	 */
	private String generatePlayerId() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Creates a classic 8x8 Othello board
	 * 
	 * @return classic board
	 */
	private Board createClassicBoard() {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Node node = new ClassicNode(generatePlayerId(), i, j);
				nodes.add(node);
			}		
		}
		return new ClassicBoard(nodes);
	}

}
