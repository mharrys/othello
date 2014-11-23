package kth.game.othello;

import kth.game.othello.score.OthelloScore;
import kth.game.othello.score.ScoreItem;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OthelloScoreTest {

	@Test
	public void updatesScore() {
		List<ScoreItem> scores = new LinkedList<ScoreItem>();

		// can not mock ScoreItems because we want to observe that they are updated, we also make use of their
		// Comparable function
		scores.add(new ScoreItem("p1", 3)); // 2
		scores.add(new ScoreItem("p2", 5)); // 6
		scores.add(new ScoreItem("p3", 2)); // 3

		OthelloScore score = new OthelloScore(scores);

		score.update(null, Arrays.asList(null, "p2"));
		score.update(null, Arrays.asList("p1", "p3"));

		List<ScoreItem> currentScores = score.getPlayersScore();

		Assert.assertEquals("p2", currentScores.get(0).getPlayerId());
		Assert.assertEquals("p3", currentScores.get(1).getPlayerId());
		Assert.assertEquals("p1", currentScores.get(2).getPlayerId());

		Assert.assertEquals(6, currentScores.get(0).getScore());
		Assert.assertEquals(3, currentScores.get(1).getScore());
		Assert.assertEquals(2, currentScores.get(2).getScore());
	}

}
