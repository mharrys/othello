package kth.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.board.factory.Square;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * The responsibility of this class is to perform an othello tournament.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournament {

	private HashMap<String, Integer> tournamentScores;
	private List<Player> players;
	private OthelloFactory othelloFactory;
	private OthelloTournamentPresenter othelloTournamentPresenter;
	private OthelloTournamentGameOutput othelloTournamentGameOutput;

	/**
	 * @param tournamentScores the scores of the tournament
	 * @param players the players that contest the tournament
	 * @param othelloFactory the factory for producing the boards
	 * @param othelloTournamentPresenter the presenter for the tournament
	 * @param othelloTournamentGameOutput the output for presenting game information
	 */
	public OthelloTournament(HashMap<String, Integer> tournamentScores, List<Player> players, OthelloFactory othelloFactory, OthelloTournamentPresenter othelloTournamentPresenter, OthelloTournamentGameOutput othelloTournamentGameOutput) {
		this.othelloFactory = othelloFactory;
		this.tournamentScores = tournamentScores;
		this.players = players;
		this.othelloTournamentPresenter = othelloTournamentPresenter;
		this.othelloTournamentGameOutput = othelloTournamentGameOutput;
	}

	/**
	 *  Runs the tournament until all players have met every other player two times.
	 *  Games are printed in the specified output.
	 */
	public void run() {
		for (int i = 0; i < players.size(); i++) {
			Player startPlayer = players.get(i);
			for (Player opponent : players) {
				if (startPlayer.getId().equals(opponent.getId())) {
					continue;
				}
				List<Player> currentGamePlayers = createGamePlayerList(startPlayer, opponent);
				Othello othello = othelloFactory.createGame(createNodesForSquareBoard(8, currentGamePlayers), currentGamePlayers);
				othello.start(startPlayer.getId());
				othelloTournamentGameOutput.onStart(othello);
				while(othello.isActive()) {
					othello.move();
					othelloTournamentGameOutput.onDraw(othello);
				}
				othelloTournamentGameOutput.onEnd(othello);
				updateTournamentScores(othello.getScore(), currentGamePlayers);
			}
		}
		
		othelloTournamentPresenter.print();
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

}
