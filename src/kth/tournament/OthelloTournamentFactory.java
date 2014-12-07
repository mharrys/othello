package kth.tournament;

import java.util.HashMap;
import java.util.List;

import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.player.Player;

/**
 * A factory for producing tournaments in othello.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournamentFactory {

	/**
	 * Creates an othello tournament which prints the games to the console.
	 * @param players the players to contest the tournament
	 * @return the tournament
	 */
	public OthelloTournament createOthelloTournamentConsole(List<Player> players) {
		OthelloFactory othelloFactory = createOthelloFactory();
		HashMap<String, Integer> tournamentScores = initTournamentScores(players);
		OthelloTournamentPresenter othelloTournamentPresenter = createOthelloTournamentPresenter(tournamentScores, players);
		OthelloTournamentGameOutput othelloTournamentGameOutput = new OthelloTournamentGameOutputConsole();
		return new OthelloTournament(tournamentScores, players, othelloFactory, othelloTournamentPresenter, othelloTournamentGameOutput);
	}

	/**
	 * Creates an othello tournament which prints the games to a GUI view.
	 * @param players the players to contest the tournament
	 * @return the tournament
	 */
	public OthelloTournament createOthelloTournamentGUI(List<Player> players) {
		OthelloFactory othelloFactory = createOthelloFactory();
		HashMap<String, Integer> tournamentScores = initTournamentScores(players);
		OthelloTournamentPresenter othelloTournamentPresenter = createOthelloTournamentPresenter(tournamentScores, players);
		OthelloTournamentGameOutput othelloTournamentGameOutput = new OthelloTournamentGameOutputGUI();
		return new OthelloTournament(tournamentScores, players, othelloFactory, othelloTournamentPresenter, othelloTournamentGameOutput);
	}

	/**
	 * Creates a othello factory.
	 * @return the othello factory
	 */
	private OthelloFactory createOthelloFactory() {
		return new OthelloFactoryImpl();
	}

	/**
	 * Initialize the tournament scores.
	 * @param players the players to contest the tournament
	 * @return the initialized scores
	 */
	private HashMap<String, Integer> initTournamentScores(List<Player> players) {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();
		for (Player player : players) {
			scores.put(player.getId(), 0);
		}

		return scores;
	}

	/**
	 * Creates a tournament presenter.
	 * @param tournamentScores the scores of the tournament
	 * @param players the players that contest the tournament
	 * @return the tournament presenter
	 */
	private OthelloTournamentPresenter createOthelloTournamentPresenter(HashMap<String, Integer> tournamentScores, List<Player> players) {
		return new OthelloTournamentPresenter(tournamentScores, players);
	}
}
