package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeSwapper;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.rules.Rules;
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
	private Rules rules;
	private NodeSwapper nodeSwapper;
	private PlayerSwitcher playerSwitcher;
	private Score score;
	private MoveHistory moveHistory;
	private List<Player> players;
	private List<Observer> gameFinishedObservers;
	private List<Observer> moveObservers;

	public OthelloImpl(
			String id,
			Board board,
			Rules rules,
			NodeSwapper nodeSwapper,
			Score score,
			MoveHistory moveHistory,
			List<Player> players) {
		this.id = id;
		this.board = board;
		this.rules = rules;
		this.nodeSwapper = nodeSwapper;
		this.score = score;
		this.moveHistory = moveHistory;
		this.players = players;
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
		return rules.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerSwitcher.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return this.players;
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return rules.hasValidMove(playerId);
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
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() throws IllegalStateException {
		Player player = playerSwitcher.getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Computer is not in turn.");
		}

		MoveStrategy moveStrategy = player.getMoveStrategy();
		Node node = moveStrategy.move(player.getId(), rules, board);
		if (node != null) {
			return move(player.getId(), node.getId());
		}

		List<Node> nodesToSwap = new ArrayList<Node>();
		// even though no swaps could be made we still count this as a move
		registerMove(nodesToSwap, null, null);

		return nodesToSwap;
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

		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);
		registerMove(nodesToSwap, playerId, nodeId);

		return nodesToSwap;
	}

	@Override
	public void start() {
		playerSwitcher = new PlayerSwitcher(getPlayers(), rules);
	}

	@Override
	public void start(String playerId) {
		playerSwitcher = new PlayerSwitcher(getPlayers(), rules, playerId);
	}

	@Override
	public void undo() {
		if (moveHistory.hasMoves()) {
			nodeSwapper.copy(moveHistory.popLastMoves());
			// loop until we hit the player before this move (in place if we are more than two players)
			for (int i = 0; i < getPlayers().size() - 1; i++) {
				playerSwitcher.switchToNextPlayer();
			}
		}
	}

	/**
	 * Registers a move and notifies all observers that the move has occurred and if the game was ended after this move
	 * the game observers will also be notified that the game has ended.
	 *
	 * @param nodesToSwap the nodes swapped for this move
	 * @param playerId the moving player
	 * @param nodeId the new move
	 */
	private void registerMove(List<Node> nodesToSwap, String playerId, String nodeId) {
		moveHistory.pushNewMoves(nodesToSwap);
		playerSwitcher.switchToNextPlayer();
		nodeSwapper.swap(nodesToSwap, playerId, nodeId);

		notifyMoveObservers(nodesToSwap);

		if (!isActive()) {
			notifyGameFinishedObservers();
		}
	}

	private void notifyMoveObservers(List<Node> nodesToSwap) {
		for (Observer observer : moveObservers) {
			observer.update(null, nodesToSwap);
		}
	}

	private void notifyGameFinishedObservers() {
		for (Observer observer : gameFinishedObservers) {
			observer.update(null, null);
		}
	}

}