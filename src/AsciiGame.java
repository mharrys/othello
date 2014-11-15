import kth.game.othello.Othello;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.List;

/**
 * This class represents the game loop for running a ASCII Othello game.
 *
 * @author Mattias Harrysson
 */
public class AsciiGame implements Game {

	private Othello othello;
	private List<Player> players;
	private BoardFormatter formatter;

	/**
	 * Constructs a ASCII game loop from specified Othello game and board formatter.
	 *
	 * @param othello the Othello game
	 * @param formatter the board formatter to use
	 */
	public AsciiGame(Othello othello, BoardFormatter formatter) {
		this.othello = othello;
		this.formatter = formatter;
		players = othello.getPlayers();
	}

	@Override
	public void start() {
		printStart();
		othello.start(players.get(1).getId());
		while (othello.isActive()) {
			formatter.present();
			othello.move();
		}
		formatter.present();
		printEnd();
	}

	/**
	 * Prints starting information.
	 */
	private void printStart() {
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

	/**
	 * Prints end game information.
	 */
	private void printEnd() {
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
			System.out.println(players.get(0).getName() + " wins with " + p2Score + " over " + p1Score);
		}
	}


}