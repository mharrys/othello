package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AggressiveMoveStrategyTest {

	private List<Node> createListOfNodes(int cols, int rows, String player1Id, String player2Id) {
		int middleRow = rows / 2;
		int middleCol = cols / 2;
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((i == (middleRow - 1) || i == middleRow) && (j == middleCol - 1)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(i == j ? player1Id : player2Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if (((i == (middleRow - 2)) && j == middleCol)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if ((i == (middleRow + 1) || (i == middleRow) || i == (middleRow - 1)) && j == middleCol) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player2Id);
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

		@SuppressWarnings("unchecked") // Suppress warning because we know it is mocked and not a real List<Node>
		List<Node> nodesToSwap1 = Mockito.mock(List.class);
		@SuppressWarnings("unchecked")
		List<Node> nodesToSwap2 = Mockito.mock(List.class);
		@SuppressWarnings("unchecked")
		List<Node> nodesToSwap3 = Mockito.mock(List.class);

		Mockito.when(nodesToSwap1.size()).thenReturn(1);
		Mockito.when(nodesToSwap2.size()).thenReturn(3);
		Mockito.when(nodesToSwap3.size()).thenReturn(1);

		MoveStrategy ms = new AggressiveMoveStrategy();
		Assert.assertNull(ms.move(player1Id, o));
		Mockito.when(o.hasValidMove(player1Id)).thenReturn(true);
		Mockito.when(o.hasValidMove(player2Id)).thenReturn(true);
		Assert.assertNull(ms.move(player2Id, o));
		Mockito.when(o.isMoveValid(player2Id, "5-5")).thenReturn(true);
		Mockito.when(o.isMoveValid(player2Id, "4-6")).thenReturn(true);
		Mockito.when(o.isMoveValid(player2Id, "5-3")).thenReturn(true);
		Mockito.when(o.getNodesToSwap(player2Id, "5-5")).thenReturn(nodesToSwap1);
		Mockito.when(o.getNodesToSwap(player2Id, "4-6")).thenReturn(nodesToSwap2);
		Mockito.when(o.getNodesToSwap(player2Id, "5-3")).thenReturn(nodesToSwap3);
		Assert.assertEquals("4-6", ms.move(player2Id, o).getId());
	}
}
