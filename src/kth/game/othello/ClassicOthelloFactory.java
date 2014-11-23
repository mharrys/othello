package kth.game.othello;

import kth.game.othello.board.*;
import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.score.OthelloScore;
import kth.game.othello.score.ScoreItem;

import java.util.*;

/**
 * A factory for producing classic othello games.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class ClassicOthelloFactory implements OthelloFactory {

	private static final String PLAYER1_NAME = "Player 1";
	private static final String PLAYER2_NAME = "Player 2";

	@Override
	public Othello createComputerGame() {
		Player player1 = createComputerPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		return createGame(players);
	}

	@Override
	public Othello createHumanGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createHumanPlayer(PLAYER2_NAME);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		return createGame(players);
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		return createGame(players);
	}

	/**
	 * Creates Othello game with given players
	 * 
	 * @param players a list of players for the game
	 * @return othello game
	 */
	private Othello createGame(List<Player> players) {
		List<ClassicNode> nodes = createClassicNodes(players.get(0), players.get(1), 8, 8);
		Board board = new ClassicBoard((List<Node>) (Object) nodes);
		NodeFinder nodeFinder = new NodeFinder();
		NodeCapturer nodeCapturer = new NodeCapturer(nodeFinder);
		NodeSwapper nodeSwapper = new ClassicNodeSwapper(nodes);
		PlayerSwitcher playerSwitcher = new PlayerSwitcher(players);
		OthelloScore othelloScore = new OthelloScore(createScoreItems(players));
		for (ClassicNode node : nodes) {
			node.addObserver(othelloScore);
		}
		return new ClassicOthello(board, nodeCapturer, nodeSwapper, playerSwitcher, othelloScore);
	}

	/**
	 * Creates a human player with specified name.
	 *
	 * @param name the name of the player
	 * @return human player
	 */
	private OthelloPlayer createHumanPlayer(String name) {
		return new OthelloPlayer(generateId(), name, Player.Type.HUMAN);
	}

	/**
	 * Creates a computer player with specified name.
	 *
	 * @param name the name of the player
	 * @return computer player
	 */
	private OthelloPlayer createComputerPlayer(String name) {
		return new OthelloPlayer(generateId(), name, Player.Type.COMPUTER);
	}


	/**
	 * Creates list of classic nodes.
	 *
	 * @param player1 the first player
	 * @param player2 the second player
	 * @param rows the number of rows
	 * @param cols the number of columns
	 * @return list of classic nodes
	 */
	private List<ClassicNode> createClassicNodes(Player player1, Player player2, int rows, int cols) {
		List<ClassicNode> nodes = new ArrayList<ClassicNode>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 3 && j == 3) {
					nodes.add(new ClassicNode(j, i, player1.getId()));
				} else if (i == 3 && j == 4) {
					nodes.add(new ClassicNode(j, i, player2.getId()));
				} else if (i == 4 && j == 3) {
					nodes.add(new ClassicNode(j, i, player2.getId()));
				} else if (i == 4 && j == 4) {
					nodes.add(new ClassicNode(j, i, player1.getId()));
				} else {
					nodes.add(new ClassicNode(j, i));
				}
			}
		}
		return nodes;
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
			ScoreItem item = new ScoreItem(player.getId(), 2);
			items.add(item);
		}

		return items;
	}

	/**
	 * Generates a unique id.
	 *
	 * @return unique id
	 */
	private String generateId() {
		return UUID.randomUUID().toString();
	}

}
