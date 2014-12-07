package kth.tournament;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.board.factory.Square;
import kth.game.othello.player.Player;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;
import kth.tournament.announcer.AsciiMatchAnnouncer;
import kth.tournament.announcer.MatchAnnouncer;
import kth.tournament.match.GuiOthelloMatch;
import kth.tournament.match.Match;
import kth.tournament.presenter.AsciiResultPresenter;
import kth.tournament.presenter.ResultPresenter;
import kth.tournament.score.OthelloScore;
import kth.tournament.score.Score;
import kth.tournament.score.ScoreItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GuiOthelloTournamentFactory implements TournamentFactory {

	@Override
	public OthelloTournament createTournament(List<Player> players) {
		OthelloFactory factory = new OthelloFactoryImpl();
		OthelloViewFactory viewFactory = new OthelloViewFactory();
		List<Match> matches = createGames(factory, viewFactory, players);
		Score score = createScore(matches, players);
		MatchAnnouncer matchAnnouncer = new AsciiMatchAnnouncer();
		ResultPresenter resultPresenter = new AsciiResultPresenter();
		return new OthelloTournament(score, matches, matchAnnouncer, resultPresenter);
	}

	private List<Match> createGames(OthelloFactory factory, OthelloViewFactory viewFactory, List<Player> players) {
		List<Match> matches = new ArrayList<Match>();
		for (Player playerA : players) {
			for (Player playerB : players) {
				if (playerA.getId().equals(playerB.getId())) {
					continue;
				}
				Othello othello = createClassicOthello(factory, playerA, playerB);
				OthelloView othelloView = createOthelloView(viewFactory, othello);
				Match match = new GuiOthelloMatch(othello, othelloView, playerA.getId());
				matches.add(match);
			}
		}
		return matches;
	}

	private OthelloView createOthelloView(OthelloViewFactory factory, Othello othello) {
		return factory.create(othello, 0, 0);
	}

	/**
	 * Creates classic Othello game between two players.
	 *
	 * @param factory the Othello factory to use to construct the Othello game
	 * @param playerA the first player
	 * @param PlayerB the second player
	 * @return classic Othello game
	 */
	private Othello createClassicOthello(OthelloFactory factory, Player playerA, Player PlayerB) {
		List<Player> players = Arrays.asList(playerA, PlayerB);
		return factory.createGame(createNodesForSquareBoard(8, players), players);
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
	 * Creates score that observes all list of upcoming game matches, the score tracks the score for each participating
	 * player.
	 *
	 * @param matches the games to observe
	 * @param players the participating players
	 * @return new score instance
	 */
	private Score createScore(List<Match> matches, List<Player> players) {
		OthelloScore score = new OthelloScore(createScoreItems(players));
		for (Match match : matches) {
			match.addObserver(score);
		}
		return score;
	}

	/**
	 * Creates a list of score items from specified list of players.
	 *
	 * @param players a list of players for the game
	 * @return the list of score items
	 */
	private List<ScoreItem> createScoreItems(List<Player> players) {
		List<ScoreItem> items = new ArrayList<ScoreItem>();
		for (Player player : players) {
			ScoreItem item = new ScoreItem(player.getId(), player.getName(), 0);
			items.add(item);
		}
		return items;
	}

}
