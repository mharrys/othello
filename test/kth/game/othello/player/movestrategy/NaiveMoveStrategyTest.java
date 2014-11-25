package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NaiveMoveStrategyTest {

	private List<Node> createListOfNodes(int cols, int rows, String player1Id, String player2Id) {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((i == ((rows / 2) - 1) || i == (rows / 2)) && (j == ((cols / 2) - 1) || j == (cols / 2))) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(i == j ? player1Id : player2Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else {
					Mockito.when(n.getOccupantPlayerId()).thenReturn("");
					Mockito.when(n.isMarked()).thenReturn(false);
				}
				Mockito.when(n.getId()).thenReturn(j + "-" + i);
				Mockito.when(n.getXCoordinate()).thenReturn(j);
				Mockito.when(n.getYCoordinate()).thenReturn(i);
				nodes.add(n);
			}
		}
		return nodes;
	}

	@Test
	public void testMove() {
		Board b = Mockito.mock(Board.class);

		String player1Id = "foo";
		String player2Id = "bar";
		List<Node> nodes = createListOfNodes(8, 8, player1Id, player2Id);
		Mockito.when(b.getNodes()).thenReturn(nodes);

		Othello o = Mockito.mock(Othello.class);
		Mockito.when(o.getBoard()).thenReturn(b);

		MoveStrategy ms = new NaiveMoveStrategy();
		Assert.assertNull(ms.move(player1Id, o));
		Mockito.when(o.hasValidMove(player1Id)).thenReturn(true);
		Assert.assertNull(ms.move(player1Id, o));
		Mockito.when(o.isMoveValid(player1Id, "4-2")).thenReturn(true);
		Assert.assertEquals("4-2", ms.move(player1Id, o).getId());

		Mockito.when(o.hasValidMove(player2Id)).thenReturn(true);
		Assert.assertNull(ms.move(player2Id, o));
		Assert.assertEquals("4-2", ms.move(player1Id, o).getId());
		Mockito.when(o.isMoveValid(player2Id, "3-2")).thenReturn(true);
		Mockito.when(o.isMoveValid(player1Id, "3-5")).thenReturn(true);
		Assert.assertEquals("3-2", ms.move(player2Id, o).getId());
		Assert.assertEquals("4-2", ms.move(player1Id, o).getId());
	}

}
