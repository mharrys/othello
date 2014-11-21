package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.ArrayList;
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
	private NodeCapturer nodeCapturer;
	private NodeFinder nodeFinder;
	private PlayerSwitcher playerSwitcher;

	/**
	 * Construct a classic Othello with two players.
	 *
	 * @param boardFactory the board factory to use for constructing boards
	 * @param playerSwitcher the object that holds the responsibility over the players
	 */
	public ClassicOthello(BoardFactory boardFactory, NodeCapturer nodeCapturer, NodeFinder nodeFinder, PlayerSwitcher playerSwitcher) {
		this.boardFactory = boardFactory;
		this.nodeCapturer = nodeCapturer;
		this.nodeFinder = nodeFinder;
		this.playerSwitcher = playerSwitcher;
		List<Player> players = this.playerSwitcher.getPlayers();
		this.board = boardFactory.constructBoard(players.get(0), players.get(1));
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return nodeCapturer.getNodesToCapture(getBoard().getNodes(), playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		if (isActive()) {
			return playerSwitcher.getPlayerInTurn();
		} else {
			return null;
		}
	}

	@Override
	public List<Player> getPlayers() {
		return playerSwitcher.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : getBoard().getNodes()) {
			if (!node.isMarked() && isMoveValid(playerId, node.getId())) {
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
		return !nodeCapturer.getNodesToCapture(getBoard().getNodes(), playerId, nodeId).isEmpty();
	}

	@Override
	public List<Node> move() throws IllegalStateException {
		Player player = playerSwitcher.getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Computer is not in turn.");
		}

		if (hasValidMove(player.getId())) {
			String nodeId;
			for (Node n : getBoard().getNodes()) {
				if (isMoveValid(player.getId(), n.getId())) {
					nodeId = n.getId();
					return move(player.getId(), nodeId);
				}
			}
		}

		playerSwitcher.switchToNextPlayer();

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
	 * Make move by specified player to specified node.
	 *
	 * @param playerId the moving player id
	 * @param nodeId the node in which the player is moving to
	 * @return
	 */
	private List<Node> makeMove(String playerId, String nodeId) {
		List<Node> nodes = getNodesToSwap(playerId, nodeId);
		// include the node where the player made the move to be updated and returned
		nodes.add(nodeFinder.getNodeFromId(getBoard().getNodes(), nodeId));

		this.board = boardFactory.constructBoard(board.getNodes(), nodes, playerId);
		playerSwitcher.switchToNextPlayer();

		return nodes;
	}

}