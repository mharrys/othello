package kth.tournament;

import java.util.HashMap;
import java.util.List;

import kth.game.othello.player.Player;

/**
 * This class is responsible for presenting information about the tournament to the console.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournamentPresenter {

	private HashMap<String, Integer> tournamentScores;
	private List<Player> players;

	/**
	 * @param tournamentScores the scores of the tournament
	 * @param players the players that contest the tournament
	 */
	public OthelloTournamentPresenter(HashMap<String, Integer> tournamentScores, List<Player> players) {
		this.tournamentScores = tournamentScores;
		this.players = players;
	}

	/**
	 * Prints the tournament scores to the console.
	 */
	public void print() {
		System.out.println("\nTournament result:");
		for (Player player : players) {
			final int score = tournamentScores.get(player.getId());
			System.out.println(player.getName() + " : " + score + " points");
		}
	}
}
