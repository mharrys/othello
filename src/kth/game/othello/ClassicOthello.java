package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import java.util.List;

/**
 * Describes a classic game of Othello.
 */
public class ClassicOthello implements Othello {

	@Override
	public Board getBoard() {
		return null;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		return null;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return false;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return false;
	}

	@Override
	public List<Node> move() {
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		return null;
	}

	@Override
	public void start() {

	}

	@Override
	public void start(String playerId) {

	}

}