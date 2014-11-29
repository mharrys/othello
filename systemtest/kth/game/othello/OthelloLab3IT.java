package kth.game.othello;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class OthelloLab3IT {

	private OthelloFactory getOthelloFactory() {
		return new OthelloFactoryImpl();
	}

	private void makeAHumanMove(Othello othello, Player human) {
		for (Node node : othello.getBoard().getNodes()) {
			if (othello.isMoveValid(human.getId(), node.getId())) {
				othello.move(human.getId(), node.getId());
				return;
			}
		}
		throw new IllegalStateException();
	}

	@Test
	public void studyTheBoardAfterUndoTest() {
		Othello othello = getOthelloFactory().createHumanGame();

		List<Player> players = othello.getPlayers();
		othello.start(players.get(0).getId());

		List<Node> startNodes = new LinkedList<Node>();
		for (Node node : othello.getBoard().getNodes()) {
			int x = node.getXCoordinate();
			int y = node.getYCoordinate();
			if ((x == 3 || x == 4) && (y == 3 || y == 4)) {
				startNodes.add(node);
			}
		}

		final int moves = 10;

		for (int i = 0; i < moves; i++) {
			Player player = players.get(i % 2);
			makeAHumanMove(othello, player);
		}

		for (int i = 0; i < moves; i++) {
			othello.undo();
		}

		List<Node> currentNodes = new LinkedList<Node>();
		for (Node node : othello.getBoard().getNodes()) {
			int x = node.getXCoordinate();
			int y = node.getYCoordinate();
			if (node.isMarked() && (x == 3 || x == 4) && (y == 3 || y == 4)) {
				Assert.assertNotNull(node.getOccupantPlayerId());
				currentNodes.add(node);
			} else {
				Assert.assertNull(node.getOccupantPlayerId());
			}
		}

		Assert.assertEquals(startNodes, currentNodes);
	}

	@Test
	public void studyTheScoreAfterUndoTest() {
		Othello othello = getOthelloFactory().createHumanGame();

		List<Player> players = othello.getPlayers();
		othello.start(players.get(0).getId());

		final int moves = 10;

		for (int i = 0; i < moves; i++) {
			Player player = players.get(i % 2);
			makeAHumanMove(othello, player);
		}

		for (int i = 0; i < moves; i++) {
			othello.undo();
		}

		Score score = othello.getScore();
		for (ScoreItem item : score.getPlayersScore()) {
			Assert.assertEquals(item.getScore(), players.size());
		}
	}

}