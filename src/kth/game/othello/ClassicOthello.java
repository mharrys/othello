package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This class represents an classic Othello game.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class ClassicOthello implements Othello {

	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	
	private BoardFactory boardFactory;
	private Board board;
	private List<Player> players;
	private int playerInTurn;

	/**
	 * Construct a classic Othello with two players.
	 *
	 * @param boardFactory the board factory to use for constructing boards
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public ClassicOthello(BoardFactory boardFactory, Player player1, Player player2) {
		this.boardFactory = boardFactory;
		this.board = boardFactory.constructBoard(player1, player2);

		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> nodesToSwap = new ArrayList<Node>();

		final Player player = getPlayerFromId(playerId);
		final Node startNode = getNodeFromId(nodeId);

		if (startNode == null || player == null) {
			return nodesToSwap;
		}

		for (Node node : getAdjacentOpponentNodes(player, startNode)) {
			nodesToSwap.addAll(numberOfCaptures(player, startNode, node));
		}

		return nodesToSwap;
	}

	@Override
	public Player getPlayerInTurn() {
		return players.get(playerInTurn);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node n : getBoard().getNodes()) {
			if (isMoveValid(playerId, n.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		for (Player player : getPlayers()) {
			if (hasValidMove(player.getId())) {
				return true;
			}
		}
	
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		final Player player = getPlayerFromId(playerId);
		final Node node = getNodeFromId(nodeId);

		if (player == null || node == null) {
			return false;
		}

		if (node.isMarked()) {
			// this node is already occupied
			return false;
		}

		// find the first direction which gives at least one capture
		List<Node> adjacentOpponentNodes = getAdjacentOpponentNodes(player, node);
		for (Node n : adjacentOpponentNodes) {
			if (!numberOfCaptures(player, node, n).isEmpty()) {
				return true;
			}
		}

		// no captures could be made from specified player and node
		return false;
	}

	@Override
	public List<Node> move() throws IllegalStateException {
		Player player = getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Computer is not in turn.");
		}

		String nodeId = "";
		for (Node n : getBoard().getNodes()) {
			if (isMoveValid(player.getId(), n.getId())) {
				nodeId = n.getId();
				return move(player.getId(), nodeId);
			}
		}

		nextPlayerInTurn();
		return new ArrayList<Node>();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		Player player = getPlayerInTurn();

		if (!(player.getId().equals(playerId))) {
			throw new IllegalArgumentException("Player '" + playerId + "' is not in turn.");
		}

		if (!isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Invalid move.");
		}

		return makeMove(playerId, nodeId);
	}

	@Override
	public void start() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int min = PLAYER1;
		int max = PLAYER2;
		playerInTurn = random.nextInt((max - min) + 1) + min;
	}

	@Override
	public void start(String playerId) {
		playerInTurn = (players.get(PLAYER1).getId().equals(playerId)) ? PLAYER1 : PLAYER2;
	}

	/**
	 * Make move by specified player to specified node.
	 *
	 * @param playerId the moving player id
	 * @param nodeId the node in which the player is moving to
	 * @return
	 */
	private List<Node> makeMove(String playerId, String nodeId) {
		List<Node> nodes = getNodesToSwap(playerId, nodeId);
		// include the node where the player made the move to be updated and returned
		nodes.add(getNodeFromId(nodeId));
		
		this.board = boardFactory.constructBoard(board.getNodes(), nodes, playerId);
		
		nextPlayerInTurn();

		return nodes;
	}

	/**
	 * Returns the captured nodes that a player can make from the specified empty node in the specified direction which
	 * is occupied by the opponent player.
	 *
	 * @param player the moving player
	 * @param from the empty node
	 * @param direction the adjacent node occupied by the moving player opponent
	 * @return list of captured nodes
	 */
	private List<Node> numberOfCaptures(Player player, Node from, Node direction) {
		List<Node> captures = new ArrayList<Node>();
		List<Node> nodes = board.getNodes();

		final int x = from.getXCoordinate();
		final int y = from.getYCoordinate();
		final int adjX = direction.getXCoordinate();
		final int adjY = direction.getYCoordinate();

		int rows = 0;
		int cols = 0;
		for (Node n : nodes) {
			if (n.getXCoordinate() > cols) {
				cols = n.getXCoordinate();
			}

			if (n.getYCoordinate() > rows) {
				rows = n.getYCoordinate();
			}
		}
		// increment to account for zero based coordinates
		rows++;
		cols++;

		int start = rows * y + x;
		int step = 0;
		int size = 0;
		if (x != adjX && y != adjY) {
			// diagonal search
			step = (rows * adjY + adjX) - start;
			size = rows * cols;
		} else if (x != adjX) {
			// horizontal search
			step = adjX - x;
			size = rows * y + cols;
		} else if (y != adjY) {
			// vertical search
			step = (rows * adjY + x) - start;
			size = rows * rows + x;
		}

		boolean validCapture = false;
		for (int i = start + step; i < size && i >= 0; i += step) {
			Node n = nodes.get(i);
			if (!n.isMarked()) {
				// we hit a unmarked node before finding a node which was occupied by one of the moving players
				break;
			} else if (n.getOccupantPlayerId().equals(direction.getOccupantPlayerId())) {
				captures.add(n);
			} else if (n.getOccupantPlayerId().equals(player.getId())) {
				validCapture = true;
				break;
			}
		}

		if (validCapture) {
			return captures;
		} else {
			// could not embrace one or more opponent nodes
			captures.clear();
			return captures;
		}
	}

	/**
	 * Returns player from specified id.
	 *
	 * @param playerId the player id
	 * @return the player with specified id, or null if not found
	 */
	private Player getPlayerFromId(String playerId) {
		Player result = null;

		for (Player player : getPlayers()) {
			if (player.getId().equals(playerId)) {
				result = player;
				break;
			}
		}

		return result;
	}

	/**
	 * Returns list of nodes around specified node that are the opposing player to the specified player.
	 *
	 * @param player the playing player
	 * @param node the pivot node
	 * @return list of adjacent nodes to the pivot node that are occupied by the opponent player
	 */
	private List<Node> getAdjacentOpponentNodes(Player player, Node node) {
		List<Node> nodes = getAdjacentMarkedNodes(node);

		Iterator<Node> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			Node n = iterator.next();
			if (n.getOccupantPlayerId().equals(player.getId())) {
				iterator.remove();
			}
		}

		return nodes;
	}
	
	/**
	 * Returns node from specified id.
	 *
	 * @param nodeId the node id
	 * @return the node with specified id, or null if not found
	 */
	private Node getNodeFromId(String nodeId) {
		Node result = null;

		for (Node node : getBoard().getNodes()) {
			if (node.getId().equals(nodeId)) {
				result = node;
				break;
			}
		}

		return result;
	}

	/**
	 * Returns list of marked adjacent nodes to specified node.
	 *
	 * @param node the pivot node
	 * @return list of adjacent nodes to the pivot node that are marked
	 */
	private List<Node> getAdjacentMarkedNodes(Node node) {
		List<Node> nodes = new ArrayList<Node>();

		final int x = node.getXCoordinate();
		final int y = node.getYCoordinate();

		for (Node n : getBoard().getNodes()) {
			if (n.isMarked()) {
				final int adjX = n.getXCoordinate();
				final int adjY = n.getYCoordinate();
				final boolean deltaH = (adjX == x + 1 || adjX == x - 1);
				final boolean deltaV = (adjY == y + 1 || adjY == y - 1);
				final boolean adjHorizontal = deltaH && (adjY == y);
				final boolean adjVertical = deltaV && (adjX == x);
				final boolean adjDiagonal = deltaH && deltaV;
				if (adjDiagonal || adjHorizontal || adjVertical) {
					nodes.add(n);
				}
			}
		}

		return nodes;
	}

	/**
	 * Proceeds to the next player in turn.
	 */
	private void nextPlayerInTurn() {
		playerInTurn = (playerInTurn + 1) % 2;
	}

}