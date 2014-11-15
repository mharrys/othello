import kth.game.othello.ClassicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.List;

/**
 * Setup and run a game of Othello between two computers.
 *
 * @author Mattias Harrysson
 */
public class ComputerGame {

	private Othello othello;
	private List<Player> players;
	private int cols;

	public ComputerGame(OthelloFactory factory) {
		othello = factory.createComputerGame();
		players = othello.getPlayers();

		cols = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.getXCoordinate() > cols) {
				cols = node.getXCoordinate();
			}
		}
		// adjust for zero based coordinates
		cols++;
	}

	/**
	 * Start and runs the game until no more moves can be made.
	 */
	public void start() {
		printStart();
		othello.start();
		while (othello.isActive()) {
			printBoard();
			othello.move();
		}
		printBoard();
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

	/**
	 * Prints board state with ASCII.
	 */
	private void printBoard() {
		final String id1 = players.get(0).getId();

		List<Node> nodes = othello.getBoard().getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if (!node.isMarked()) {
				System.out.print('.');
			} else {
				System.out.print(node.getOccupantPlayerId() == id1 ? 'w' : 'b');
			}

			if ((i + 1) % cols == 0) {
				System.out.print('\n');
			} else {
				System.out.print(' ');
			}
		}
		System.out.println();
	}

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		ComputerGame game = new ComputerGame(new ClassicOthelloFactory());
		game.start();
	}

}