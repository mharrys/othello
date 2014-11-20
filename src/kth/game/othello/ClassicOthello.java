package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents an classic Othello game.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class ClassicOthello implements Othello {
	
	private BoardFactory boardFactory;
	private Board board;
	private PlayerSwitcher playerSwitcher;
	private int rows;
	private int cols;

	/**
	 * Construct a classic Othello with two players.
	 *
	 * @param boardFactory the board factory to use for constructing boards
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public ClassicOthello(BoardFactory boardFactory, PlayerSwitcher playerSwitcher) {
		this.boardFactory = boardFactory;
		this.playerSwitcher = playerSwitcher;
		List<Player> players = this.playerSwitcher.getPlayers();
		this.board = boardFactory.constructBoard(players.get(0), players.get(1));

		rows = 0;
		cols = 0;
		for (Node n : board.getNodes()) {
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
		// TODO Return null if no player can move, as according to the interface
		return playerSwitcher.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerSwitcher.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		if (hasMoves(playerId)) {
			return true;
		}
		playerSwitcher.switchToNextPlayer();
		return false;
	}

	@Override
	public boolean isActive() {
		for (Player player : getPlayers()) {
			if (hasMoves(player.getId())) {
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
		Player player = playerSwitcher.getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Computer is not in turn.");
		}

		if (hasValidMove(player.getId())) {
			String nodeId = "";
			for (Node n : getBoard().getNodes()) {
				if (isMoveValid(player.getId(), n.getId())) {
					nodeId = n.getId();
					return move(player.getId(), nodeId);
				}
			}
		}

		return new ArrayList<Node>();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		Player player = playerSwitcher.getPlayerInTurn();

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
		playerSwitcher.setStartingPlayer();
	}

	@Override
	public void start(String playerId) {
		playerSwitcher.setStartingPlayer(playerId);
	}

	/**
	 * Checks whether a player has valid moves
	 * 
	 * @param playerId the id of the player to checks if it has moves
	 * @return
	 */
	private Boolean hasMoves(String playerId) {
		for (Node n : getBoard().getNodes()) {
			if (isMoveValid(playerId, n.getId())) {
				return true;
			}
		}
		return false;
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
		
		playerSwitcher.switchToNextPlayer();

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

		int x = from.getXCoordinate();
		int y = from.getYCoordinate();
		int adjX = direction.getXCoordinate();
		int adjY = direction.getYCoordinate();

		int stepX = adjX - x;
		int stepY = adjY - y;
		// start looking on the next adjacent node
		x += stepX;
		y += stepY;

		boolean validCapture = false;
		while (x >= 0 && y >= 0 && x < cols && y < rows) {
			Node n = getNodeFromGrid(x, y);

			if (!n.isMarked()) {
				// we hit a unmarked node before finding a node which was occupied by one of the moving players
				break;
			} else if (n.getOccupantPlayerId().equals(direction.getOccupantPlayerId())) {
				captures.add(n);
			} else if (n.getOccupantPlayerId().equals(player.getId())) {
				validCapture = true;
				break;
			}

			x += stepX;
			y += stepY;
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
	 * Returns node from specified coordinates.
	 *
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return the node with on specified coordinates, or null if not found
	 */
	private Node getNodeFromGrid(int x, int y) {
		int index = rows * y + x;
		if (index >= 0 && index < (rows * cols)) {
			return getBoard().getNodes().get(index);
		} else {
			return null;
		}
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



}