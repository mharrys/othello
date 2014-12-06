package kth.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.board.factory.Square;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * The responsibility of this class is to perform an othello tournament.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournament {

	private HashMap<String, Integer> tournamentScores;
	private List<Player> players;
	private OthelloFactory othelloFactory;

	/**
	 * @param players the players in the tournament.
	 */
	public OthelloTournament(List<Player> players) {
		this.othelloFactory = createFactory();
		tournamentScores = initTournamentScores(players);
		this.players = players;
	}

	/**
	 *  Runs the tournament until all players have met every other player two times.
	 *  Games are printed in the console.
	 */
	public void run() {
		for (int i = 0; i < players.size(); i++) {
			Player startPlayer = players.get(i);
			for (Player opponent : players) {
				if (startPlayer.getId().equals(opponent.getId())) {
					continue;
				}
				List<Player> currentGamePlayers = createGamePlayerList(startPlayer, opponent);
				Othello game = othelloFactory.createGame(createNodesForSquareBoard(8, currentGamePlayers), currentGamePlayers);
				runGameInConsole(game, startPlayer, opponent);
				printScoreOfGame(game.getScore(), currentGamePlayers);
				updateTournamentScores(game.getScore(), currentGamePlayers);
			}
		}
		
		printTournamentResult(players);
	}
	
	/**
	 *  Runs the tournament until all players have met every other player two times.
	 *  Games are printed in a GUI.
	 */
	public void run(int timeBetweenSwaps, int timeBetweenMoves) {
		for (int i = 0; i < players.size(); i++) {
			Player startPlayer = players.get(i);
			for (Player opponent : players) {
				if (startPlayer.getId().equals(opponent.getId())) {
					continue;
				}
				List<Player> currentGamePlayers = createGamePlayerList(startPlayer, opponent);
				Othello game = othelloFactory.createGame(createNodesForSquareBoard(8, currentGamePlayers), currentGamePlayers);
				OthelloView othelloView = OthelloViewFactory.create(game, timeBetweenSwaps, timeBetweenMoves);
				othelloView.start(startPlayer.getId());
				while (game.isActive()) {
					
				}
				updateTournamentScores(game.getScore(), currentGamePlayers);
			}
		}
		
		printTournamentResult(players);
	}

	/**
	 * Creates a othello factory
	 * @return an othello factory
	 */
	private OthelloFactory createFactory() {
		return new OthelloFactoryImpl();
	}

	/**
	 * Creates nodes for a square board.
	 * @param size the size of the board
	 * @param players the players on the board
	 * @return the nodes
	 */
	private Set<NodeData> createNodesForSquareBoard(int size, List<Player> players) {
		Square square = new Square();
		return square.getNodes(size, players);
	}

	/**
	 * Creates a list of the players that plays a game.
	 * @param player1 the first player of the game
	 * @param player2 the second player of the game
	 * @return a list of the players
	 */
	private List<Player> createGamePlayerList(Player player1, Player player2) {
		List<Player> currentGamePlayers = new ArrayList<Player>();
		currentGamePlayers.add(player1);
		currentGamePlayers.add(player2);
		return currentGamePlayers;
	}

	/**
	 * Runs a game of othello in the console.
	 * @param game the othello game
	 * @param startPlayer the starting player
	 * @param opponent the opponent player
	 */
	private void runGameInConsole(Othello game, Player startPlayer, Player opponent) {
		System.out.println();
		System.out.println(startPlayer.getName() + " vs " + opponent.getName());
		game.start(startPlayer.getId());
		while(game.isActive()) {
			game.move();
			System.out.println();
			System.out.println(game.getBoard().toString());
		}
		System.out.println();
	}
	/**
	 * Prints the score of a game to console.
	 * @param score the score object of a game
	 * @param players the players of the game
	 */
	private void printScoreOfGame(Score score, List<Player> players) {
		final int p1Points = score.getPoints(players.get(0).getId());
		final int p2Points = score.getPoints(players.get(1).getId());
		final String p1Score = players.get(0).getName() + " (" + p1Points + ")";
		final String p2Score = players.get(1).getName() + " (" + p2Points + ")";
		System.out.println("Score: " + (p1Points > p2Points ? p1Score + " " + p2Score : p2Score + " " + p1Score));
	}

	/**
	 * Initialize the tournament scores.
	 * @param players the players in the tournament
	 * @return the tournament scores
	 */
	private HashMap<String, Integer> initTournamentScores(List<Player> players) {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();
		for (Player player : players) {
			scores.put(player.getId(), 0);
		}
		
		return scores;
	}

	/**
	 * Updates the tournament scores.
	 * @param score the score object of a game
	 * @param players the players of a game
	 */
	private void updateTournamentScores(Score score, List<Player> players) {
		for (Player player : players) {
			final int previousScore = tournamentScores.get(player.getId());
			final int newScore = score.getPoints(player.getId());
			tournamentScores.put(player.getId(), previousScore + newScore);
		}
	}

	/**
	 * Prints the result of the tournament to console.
	 * @param players the players of the tournament
	 */
	private void printTournamentResult(List<Player> players) {
		System.out.println("\nTournament result:");
		for (Player player : players) {
			final int score = tournamentScores.get(player.getId());
			System.out.println(player.getName() + " : " + score + " points");
		}
	}
}
