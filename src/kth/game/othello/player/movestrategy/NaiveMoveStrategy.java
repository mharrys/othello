package kth.game.othello.player.movestrategy;

import kth.game.othello.Othello;
import kth.game.othello.board.Node;

/**
 * This move strategy will make the first valid move it can find for a player.
 * 
 * @author Henrik Hygerth
 *
 */
public class NaiveMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "NaiveMoveStrategy";
	}

	@Override
	public Node move(String playerId, Othello othello) {
		if (othello.hasValidMove(playerId)) {
			for (Node node : othello.getBoard().getNodes()) {
				if (othello.isMoveValid(playerId, node.getId())) {
					return node;
				}
			}
		}

		return null;
	}

}
