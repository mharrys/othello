package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.Othello;
import kth.game.othello.board.Node;

/**
 * This move strategy will make random moves.
 *
 * @author Henrik Hygerth
 */
public class RandomMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "RandomMoveStrategy";
	}

	@Override
	public Node move(String playerId, Othello othello) {
		List<Node> possibleMoves = new ArrayList<Node>();
		if (othello.hasValidMove(playerId)) {
			for (Node node : othello.getBoard().getNodes()) {
				if (othello.isMoveValid(playerId, node.getId())) {
					possibleMoves.add(node);
				}
			}
		}
		Node result = null;
		if (!possibleMoves.isEmpty()) {
			Random rand = new Random(System.currentTimeMillis());
			int index = rand.nextInt(possibleMoves.size());
			result = possibleMoves.get(index);
		}
		return result;
	}

}
