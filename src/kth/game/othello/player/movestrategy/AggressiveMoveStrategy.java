package kth.game.othello.player.movestrategy;

import kth.game.othello.Othello;
import kth.game.othello.board.Node;

/**
 * This move strategy will make moves that captures as many opponent nodes as possible in one move.
 *
 * @author Henrik Hygerth
 */
public class AggressiveMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "AggressiveMoveStrategy";
	}

	@Override
	public Node move(String playerId, Othello othello) {
		int max = 0;
		Node result = null;

		if (othello.hasValidMove(playerId)) {
			for (Node node : othello.getBoard().getNodes()) {
				if (othello.isMoveValid(playerId, node.getId())) {
					int tmp = othello.getNodesToSwap(playerId, node.getId()).size();
					if (max <= tmp) {
						max = tmp;
						result = node;
					}
				}
			}
		}

		return result;
	}

}
