package kth.game.othello.player.movestrategy;

import kth.game.othello.Othello;
import kth.game.othello.board.Node;

public class AggressiveMoveStrategy implements MoveStrategy {

	private String name = "AggressiveMoveStrategy";

	@Override
	public String getName() {
		return name;
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
