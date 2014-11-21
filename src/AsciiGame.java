import kth.game.othello.Othello;
import kth.game.othello.board.Node;

import java.util.Scanner;

/**
 * This class represents the game loop for running a ASCII Othello game.
 *
 * @author Mattias Harrysson
 */
public class AsciiGame extends Game {

	private BoardFormatter formatter;
	private Scanner reader;

	/**
	 * Constructs a ASCII game loop from specified Othello game, board formatter and input reader.
	 *
	 * @param othello the Othello game
	 * @param formatter the board formatter to use
	 * @param reader the human input reader
	 */
	public AsciiGame(Othello othello, BoardFormatter formatter, Scanner reader) {
		super(othello);
		this.formatter = formatter;
		this.reader = reader;
	}

	@Override
	protected void onStart() {
		final String n1 = players.get(0).getName();
		final String n2 = players.get(1).getName();
		System.out.println("**** Othello: Game start ****");
		System.out.println(n1 + " vs " + n2);
		System.out.println();
		System.out.println(n1 + ": w (white)");
		System.out.println(n2 + ": b (black)");
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

		reader.nextLine(); // consume \n

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
		formatter.format(othello.getBoard());
	}

	@Override
	protected void onEnd() {
		System.out.println("**** Othello: Game Ended ****");

		int p1Score = 0;
		int p2Score = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				if (node.getOccupantPlayerId() == players.get(0).getId()) {
					p1Score++;
				} else {
					p2Score++;
				}
			}
		}

		System.out.print("Result: ");
		if (p1Score == p2Score) {
			System.out.println("It is a draw!");
		} else if (p1Score > p2Score) {
			System.out.println(players.get(0).getName() + " wins with " + p1Score + " over " + p2Score);
		} else {
			System.out.println(players.get(1).getName() + " wins with " + p2Score + " over " + p1Score);
		}
	}

	/**
	 * Returns next integer from input reader.
	 *
	 * @return next input integer, or -1 if invalid input
	 */
	private int getNextInputInt() {
		if (reader.hasNextInt()) {
			return reader.nextInt();
		} else {
			reader.next(); // skip
			return -1;
		}
	}

}