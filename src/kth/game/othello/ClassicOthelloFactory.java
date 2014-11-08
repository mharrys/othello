package kth.game.othello;

import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

import java.util.UUID;

/**
 * A factory for producing classic othello games.
 *
 * @author Mattias Harrysson
 */
public class ClassicOthelloFactory implements OthelloFactory {

	@Override
	public Othello createComputerGame() {
		Player player1 = createComputerPlayer("Computer 1");
		Player player2 = createComputerPlayer("Computer 2");
		return new ClassicOthello(player1, player2);
	}

	@Override
	public Othello createHumanGame() {
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createHumanPlayer("Player 2");
		return new ClassicOthello(player1, player2);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Player player1 = createHumanPlayer("Player 1");
		Player player2 = createComputerPlayer("Computer 1");
		return new ClassicOthello(player1, player2);
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

}
