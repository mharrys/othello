package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.player.Player;
import kth.game.othello.rules.Rules;

/**
 * This class is responsible for tracking the player in turn for a turn-based game.
 * 
 * @author Henrik Hygerth
 */
public class PlayerSwitcher {

	private List<Player> players;
	private int numPlayers;
	private int playerInTurn;

	/**
	 * @param players the list of players included in the game
	 */
	public PlayerSwitcher(List<Player> players) {
		this.players = players;
		numPlayers = players.size();
		setStartingPlayer();
	}

	/**
	 * @param players the list of players included in the game
	 * @param playerId the id of the player that should make the first move
	 */
	public PlayerSwitcher(List<Player> players, String playerId) {
		this.players = players;
		numPlayers = players.size();
		setStartingPlayer(playerId);
	}

	/**
	 * Returns the player in turn.
	 * 
	 * @return the player in turn
	 */
	public Player getPlayerInTurn() {
		return players.get(playerInTurn);
	}

	/**
	 * Proceeds to the next player that can make a valid move.
	 * 
	 * @param rules the rules of a game
	 */
	public void switchToNextPlayer(Rules rules) {
		for (int i = 1; i < players.size(); i++) {
			String playerId = players.get((playerInTurn + i) % numPlayers).getId();
			if (rules.hasValidMove(playerId)) {
				playerInTurn = (playerInTurn + 1) % numPlayers;
				return;
			}
		}
	}

	/**
	 * Randomly selects a player in turn.
	 */
	private void setStartingPlayer() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		playerInTurn = random.nextInt(numPlayers + 1) % numPlayers;
	}

	/**
	 * Sets a specific player in turn.
	 * 
	 * @param playerId the id of the desired player
	 */
	private void setStartingPlayer(String playerId) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(playerId)) {
				playerInTurn = i;
			}
		}
	}

}
