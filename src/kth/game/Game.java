package kth.game;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;

import java.util.List;

/**
 * This class enforces a Othello game loop.
 *
 * @author Mattias Harrysson
 */
abstract public class Game {

	protected Othello othello;
	protected List<Player> players;

	/**
	 * Constructs a Othello game.
	 *
	 * @param othello the Othello game to use
	 */
	public Game(Othello othello) {
		this.othello = othello;
		players = othello.getPlayers();
	}

	/**
	 * Start and runs the game until no more moves can be made.
	 */
	public void run() {
		othello.start(players.get(1).getId());

		onStart();
		while (othello.isActive()) {
			onDraw();

			Player movingPlayer = othello.getPlayerInTurn();
			if (movingPlayer.getType() == Player.Type.COMPUTER) {
				othello.move();
			} else if (othello.hasValidMove(movingPlayer.getId())) {
				try {
					othello.move(movingPlayer.getId(), onHumanMove());
				} catch (IllegalArgumentException e) {
					onError(e.getMessage());
				}
			}
		}

		onDraw();
		onEnd();
	}

	/**
	 * Called when game is started.
	 */
	abstract protected void onStart();

	/**
	 * Called when a human player needs to make a move.
	 *
	 * @return node id
	 */
	abstract protected String onHumanMove();

	/**
	 * Called when a error has occurred.
	 *
	 * @param message the error message
	 */
	abstract protected void onError(String message);

	/**
	 * Called when the game needs to draw itself.
	 */
	abstract protected void onDraw();

	/**
	 * Called when the game has ended.
	 */
	abstract protected void onEnd();

}
