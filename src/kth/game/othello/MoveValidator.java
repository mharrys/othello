package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * This class is responsible for validating moves
 * 
 * @author Henrik Hygerth
 *
 */
public class MoveValidator {

	private NodeCapturer nodeCapturer;
	private NodeFinder nodeFinder;

	public MoveValidator(NodeCapturer nodeCapturer, NodeFinder nodeFinder) {
		this.nodeCapturer = nodeCapturer;
		this.nodeFinder = nodeFinder;
	}

	/**
	 * Determines if a player is allowed to make a move at the given node.
	 * 
	 * @param nodes list of all the nodes
	 * @param player the moving player
	 * @param node the node where the player wants to play
	 * @return true if the move is valid
	 */
	public boolean isMoveValid(List<Node> nodes, Player player, Node node) {
		// find the first direction which gives at least one capture
		List<Node> adjacentOpponentNodes = nodeFinder.getAdjacentOpponentNodes(nodes, player, node);
		for (Node n : adjacentOpponentNodes) {
			if (!nodeCapturer.nodesToCaptureInDirection(nodes, player, node, n).isEmpty()) {
				return true;
			}
		}

		// no captures could be made from specified player and node
		return false;
	}

	/**
	 * Determines if a player has any valid move.
	 * 
	 * @param nodes list of all the nodes
	 * @param player the player the check
	 * @return true if the player has a valid move
	 */
	public boolean hasValidMove(List<Node> nodes, Player player) {
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(nodes, player, node)) {
				return true;
			}
		}

		return false;
	}
}
