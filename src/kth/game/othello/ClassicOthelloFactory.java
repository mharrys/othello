package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.score.OthelloScore;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
		return createGame(Arrays.asList(player1, player2));
	}

	@Override
	public Othello createHumanGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createHumanPlayer(PLAYER2_NAME);
		return createGame(Arrays.asList(player1, player2));
	}

	@Override
	public Othello createHumanVersusComputerGame() {
		Player player1 = createHumanPlayer(PLAYER1_NAME);
		Player player2 = createComputerPlayer(PLAYER2_NAME);
		return createGame(Arrays.asList(player1, player2));
	}

	@Override
	public Othello createGame(Set<NodeData> nodesData, List<Player> players) {
		return createGame(createClassicNodes(nodesData), players);
	}

	/**
	 * Creates Othello game with a default board from given list of players.
	 * 
	 * @param players a list of players in the game
	 * @return othello game
	 */
	private Othello createGame(List<Player> players) {
		return createGame(createClassicNodes(players.get(0), players.get(1), 8, 8), players);
	}

	/**
	 * Creates Othello game.
	 *
	 * @param nodes a list of nodes to use as board
	 * @param players a list of players in the game
	 * @return othello game
	 */
	private Othello createGame(List<ClassicNode> nodes, List<Player> players) {
		Board board = new ClassicBoard((List<Node>) (Object) nodes);
		NodeFinder nodeFinder = new NodeFinder();
		NodeCapturer nodeCapturer = new NodeCapturer(nodeFinder);
		NodeSwapper nodeSwapper = new ClassicNodeSwapper(nodes);
		PlayerSwitcher playerSwitcher = new PlayerSwitcher(players);
		Score score = createScore(nodes, players);
		return new ClassicOthello(board, nodeCapturer, nodeSwapper, playerSwitcher, score);
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
	 * Creates list of classic nodes.
	 *
	 * @param nodesData the nodes to read from
	 * @return list of classic nodes
	 */
	private List<ClassicNode> createClassicNodes(Set<NodeData> nodesData) {
		List<ClassicNode> nodes = new ArrayList<ClassicNode>();
		for (NodeData nodeData : nodesData) {
			int x = nodeData.getXCoordinate();
			int y = nodeData.getYCoordinate();
			String occupantPlayerId = nodeData.getOccupantPlayerId();
			if (occupantPlayerId == null) {
				nodes.add(new ClassicNode(x, y));
			} else {
				nodes.add(new ClassicNode(x, y, occupantPlayerId));
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
	 * Creates score that observers to all specified nodes and tracks each specified player.
	 *
	 * @param nodes the nodes to observe
	 * @param players the players to track
	 * @return new score instance
	 */
	private Score createScore(List<ClassicNode> nodes, List<Player> players) {
		OthelloScore othelloScore = new OthelloScore(createScoreItems(players));
		for (ClassicNode node : nodes) {
			node.addObserver(othelloScore);
		}
		return othelloScore;
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
