package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCapturer;
import kth.game.othello.board.NodeFinder;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NodeCapturerTest {

	private List<Node> createListOfNodesStateOneMoveMade(int rows, int cols, String player1Id, String player2Id, Board board) {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((i == ((rows / 2) - 1) && j == ((cols / 2) - 1))    ||
						(i == (rows / 2) && j == (cols / 2))            ||
						(i == ((rows / 2) - 1) && j == (cols / 2))      ||
						((i == (rows / 2) - 2) && j == (cols / 2))) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if ((i == ((rows / 2)) && j == ((cols / 2) - 1))) {
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
				Mockito.when(board.getNode(n.getXCoordinate(), n.getYCoordinate())).thenReturn(n);
			}
		}
		return nodes;
	}

	@Test
	public void nodesToCaptureInDirection() {
		String p1Id = "foo";
		String p2Id = "bar";
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn(p1Id);
		Mockito.when(player2.getId()).thenReturn(p2Id);

		Board board = Mockito.mock(Board.class);
		List<Node> nodes = createListOfNodesStateOneMoveMade(8, 8, player1.getId(), player2.getId(), board);
		Mockito.when(board.getNodes()).thenReturn(nodes);

		NodeFinder nf = new NodeFinder();
		NodeCapturer nc = new NodeCapturer(nf);

		Node from = Mockito.mock(Node.class);
		Mockito.when(from.getXCoordinate()).thenReturn(5);
		Mockito.when(from.getYCoordinate()).thenReturn(4);

		Node direction = Mockito.mock(Node.class);
		Mockito.when(direction.getOccupantPlayerId()).thenReturn(p1Id);
		Mockito.when(direction.getXCoordinate()).thenReturn(4);
		Mockito.when(direction.getYCoordinate()).thenReturn(4);
		Assert.assertEquals(1, nc.getNodesToCaptureInDirection(board, p2Id, from, direction).size());
		
		Mockito.when(direction.getXCoordinate()).thenReturn(4);
		Mockito.when(direction.getYCoordinate()).thenReturn(3);
		Assert.assertEquals(0, nc.getNodesToCaptureInDirection(board, p2Id, from, direction).size());

		Mockito.when(from.getXCoordinate()).thenReturn(3);
		Mockito.when(from.getYCoordinate()).thenReturn(2);

		Mockito.when(direction.getXCoordinate()).thenReturn(3);
		Mockito.when(direction.getYCoordinate()).thenReturn(3);
		Assert.assertEquals(1, nc.getNodesToCaptureInDirection(board, p2Id, from, direction).size());
		
		Mockito.when(direction.getXCoordinate()).thenReturn(4);
		Mockito.when(direction.getYCoordinate()).thenReturn(3);
		Assert.assertEquals(0, nc.getNodesToCaptureInDirection(board, p2Id, from, direction).size());
	}

}
