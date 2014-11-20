package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.player.Player;

/**
 * This class is responsible for the players in the game
 * 
 * @author Henrik Hygerth
 *
 */
public class PlayerSwitcher {

	private List<Player> players;
	private int nrPlayers;
	private int playerInTurn;

	public PlayerSwitcher(List<Player> players) {
		this.players = players;
		nrPlayers = players.size();

	}

	/**
	 * Get the player in turn
	 * 
	 * @return the player in turn
	 */
	public Player getPlayerInTurn() {
		return players.get(playerInTurn);
	}

	/**
	 * Get all the players in the game
	 * 
	 * @return the list of players
	 */
	public List<Player> getPlayers() {
		return players;
	}


	/**
	 * Randomly selects a player in turn
	 */
	public void setStartingPlayer() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		playerInTurn = random.nextInt((getNumberOfPlayers()) + 1) % nrPlayers;
	}

	/**
	 * Sets a specific player in turn
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
		playerInTurn = (playerInTurn + 1) % nrPlayers;
	}
	

	/**
	 * Get the number of players in the game
	 * @return
	 */
	private int getNumberOfPlayers() {
		return nrPlayers;
	}

}
