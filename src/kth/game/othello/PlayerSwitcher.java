package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.player.Player;

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
	 * Returns all players in the game.
	 * 
	 * @return the list of players
	 */
	public List<Player> getPlayers() {
		return players;
	}


	/**
	 * Randomly selects a player in turn.
	 */
	public void setStartingPlayer() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		playerInTurn = random.nextInt(numPlayers + 1) % numPlayers;
	}

	/**
	 * Sets a specific player in turn.
	 * 
	 * @param playerId the id of the desired player
	 */
	public void setStartingPlayer(String playerId) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(playerId)) {
				playerInTurn = i;
			}
		}
	}

	/**
	 * Proceeds to the next player in turn.
	 */
	public void switchToNextPlayer() {
		playerInTurn = (playerInTurn + 1) % numPlayers;
	}

}
