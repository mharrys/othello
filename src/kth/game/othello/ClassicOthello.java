package kth.game.othello;

import kth.game.othello.board.Board;
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

	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;

	private Board board;
	private List<Player> players;
	private int playerInTurn;

	/**
	 * Construct a classic Othello with two players.
	 *
	 * @param newBoard the board
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public ClassicOthello(Board newBoard, Player player1, Player player2) {
		board = newBoard;
		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		playerInTurn = PLAYER1;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return null;
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
		nextPlayerInTurn();
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		nextPlayerInTurn();
		return null;
	}

	@Override
	public void start() {
		playerInTurn = PLAYER1;
	}

	@Override
	public void start(String playerId) {
		playerInTurn = (players.get(PLAYER1).getId() == playerId) ? PLAYER1 : PLAYER2;
	}

	/**
	 * Proceeds to the next player in turn.
	 */
	private void nextPlayerInTurn() {
		playerInTurn = (playerInTurn + 1) % 2;
	}
}