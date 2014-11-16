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

	/**
	 * Constructs a ASCII game loop from specified Othello game and board formatter.
	 *
	 * @param othello the Othello game
	 * @param formatter the board formatter to use
	 */
	public AsciiGame(Othello othello, BoardFormatter formatter) {
		super(othello);
		this.formatter = formatter;
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
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter move");
		System.out.print("> ");
		int x = reader.nextInt();
		int y = reader.nextInt();
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
		formatter.present();
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

}