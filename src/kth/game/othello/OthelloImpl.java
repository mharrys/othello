package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCapturer;
import kth.game.othello.board.NodeSwapper;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.Score;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 * This class represents an classic Othello game.
 *
 * @author Mattias Harrysson
 * @author Henrik Hygerth
 */
public class OthelloImpl implements Othello {

	private String id;
	private Board board;
	private NodeCapturer nodeCapturer;
	private NodeSwapper nodeSwapper;
	private PlayerSwitcher playerSwitcher;
	private Score score;
	private List<Observer> gameFinishedObservers;
	private List<Observer> moveObservers;

	public OthelloImpl(
			String id,
			Board board,
			NodeCapturer nodeCapturer,
			NodeSwapper nodeSwapper,
			PlayerSwitcher playerSwitcher,
			Score score) {
		this.id = id;
		this.board = board;
		this.nodeCapturer = nodeCapturer;
		this.nodeSwapper = nodeSwapper;
		this.playerSwitcher = playerSwitcher;
		this.score = score;
		gameFinishedObservers = new LinkedList<Observer>();
		moveObservers = new LinkedList<Observer>();
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		gameFinishedObservers.add(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		moveObservers.add(observer);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return nodeCapturer.getNodesToCapture(board, playerId, nodeId, false);
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
	public Score getScore() {
		return score;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : board.getNodes()) {
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
		return !nodeCapturer.getNodesToCapture(board, playerId, nodeId, false).isEmpty();
	}

	@Override
	public List<Node> move() throws IllegalStateException {
		Player player = playerSwitcher.getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Computer is not in turn.");
		}

		MoveStrategy moveStrategy = player.getMoveStrategy();
		Node node = moveStrategy.move(player.getId(), this);
		if (node != null) {
			return move(player.getId(), node.getId());
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

		List<Node> nodesToSwap = nodeCapturer.getNodesToCapture(board, playerId, nodeId, true);
		nodeSwapper.swap(nodesToSwap, playerId, nodeId);
		playerSwitcher.switchToNextPlayer();

		return nodesToSwap;
	}

	@Override
	public void start() {
		playerSwitcher.setStartingPlayer();
	}

	@Override
	public void start(String playerId) {
		playerSwitcher.setStartingPlayer(playerId);
	}

	@Override
	public void undo() {

	}

}