package kth.game.othello.player.movestrategy;

import kth.game.othello.Othello;
import kth.game.othello.board.Node;

/**
 * This class is responsible for providing players with an naive move strategy
 * 
 * @author Henrik Hygerth
 *
 */
public class NaiveMoveStrategy implements MoveStrategy {

	private String name = "NaiveMoveStrategy";

	@Override
	public String getName() {
		return name;
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
