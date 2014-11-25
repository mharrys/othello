package kth.game;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreItem;

import java.util.Scanner;

/**
 * This class represents the game loop for running a ASCII Othello game.
 *
 * @author Mattias Harrysson
 */
public class AsciiGame extends Game {

	private Scanner scanner;

	/**
	 * Constructs a Othello game loop using ASCII for the visual presentation.
	 *
	 * @param othello the Othello game
	 * @param scanner the human input scanner
	 */
	public AsciiGame(Othello othello, Scanner scanner) {
		super(othello);
		this.scanner = scanner;
	}

	@Override
	protected void onStart() {
		System.out.println("**** Othello: Game start ****");
		System.out.println();
		System.out.println(othello.getPlayerInTurn().getName() + " is first to move.");
		System.out.println();
	}

	@Override
	protected String onHumanMove() {
		System.out.println("Enter move");
		System.out.print("> ");

		int x = getNextInputInt();
		int y = getNextInputInt();

		scanner.nextLine(); // consume \n
		System.out.println();

		return "" + x + "-" + y;
	}

	@Override
	protected void onError(String message) {
		System.out.println(message);
		System.out.println();
	}

	@Override
	protected void onDraw() {
		System.out.println();
		printScore();
		System.out.print("\n\n");

		System.out.println(othello.getBoard());
	}

	@Override
	protected void onEnd() {
		System.out.println();
		System.out.println("**** Othello: Game Ended ****");
	}

	/**
	 * Returns next integer from input scanner.
	 *
	 * @return next input integer, or -1 if invalid input
	 */
	private int getNextInputInt() {
		if (scanner.hasNextInt()) {
			return scanner.nextInt();
		} else {
			scanner.next(); // skip
			return -1;
		}
	}

	/**
	 * Prints current score for all participating players.
	 */
	private void printScore() {
		System.out.print("Score: ");
		for (ScoreItem item : othello.getScore().getPlayersScore()) {
			System.out.print(getPlayerNameFromId(item.getPlayerId()) + " (" + item.getScore() + ") ");
		}
	}

	/**
	 * Returns player name from specified player id.
	 *
	 * @param playerId the player id to search after
	 * @return the player name
	 */
	private String getPlayerNameFromId(String playerId) {
		String result = null;
		for (Player player : othello.getPlayers()) {
			if (player.getId() == playerId) {
				result = player.getName();
				break;
			}
		}
		return result;
	}

}