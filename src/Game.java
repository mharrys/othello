import kth.game.othello.Othello;
import kth.game.othello.player.Player;

import java.util.List;

/**
 * This class provides a game entry point.
 *
 * @author Mattias Harrysson
 */
abstract public class Game {

	protected Othello othello;
	protected List<Player> players;
	private BoardFormatter formatter;

	public Game(Othello othello, BoardFormatter formatter) {
		this.othello = othello;
		this.formatter = formatter;
		players = othello.getPlayers();
	}

	/**
	 * Start and runs the game until no more moves can be made.
	 */
	public void run() {
		onStart();
		othello.start(players.get(1).getId());
		while (othello.isActive()) {
			formatter.present();
			Player movingPlayer = othello.getPlayerInTurn();
			if (movingPlayer.getType() == Player.Type.COMPUTER) {
				othello.move();
			} else {
				String movingPlayerId = movingPlayer.getId();
				while (othello.hasValidMove(movingPlayerId) && movingPlayerId == othello.getPlayerInTurn().getId()) {
					try {
						othello.move(movingPlayer.getId(), onHumanMove());
					} catch (IllegalArgumentException e) {
						onBadHumanMove(e.getMessage());
					}
				}
			}
		}
		formatter.present();
		onEnd();
	}

	abstract protected void onStart();

	abstract protected String onHumanMove();

	abstract protected void onBadHumanMove(String message);

	abstract protected void onEnd();

}
