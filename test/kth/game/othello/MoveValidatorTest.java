package kth.game.othello;


import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MoveValidatorTest {

	private List<Node> createListOfNodesStateOneMoveMade(int rows, int cols, String player1Id, String player2Id) {
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
			}
		}
		return nodes;
	}

	private List<Node> createListOfNodesStateFakeWithNoMovesForPlayer2(int rows, int cols, String player1Id, String player2Id) {
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
	public void isMoveValid() {
		String p1Id = "foo";
		String p2Id = "bar";
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn(p1Id);
		Mockito.when(player2.getId()).thenReturn(p2Id);
		List<Node> nodes = createListOfNodesStateOneMoveMade(8, 8, player1.getId(), player2.getId());

		NodeFinder nf = new NodeFinder();
		NodeCapturer nc = new NodeCapturer(nf);
		MoveValidator mv = new MoveValidator(nc, nf);

		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(5);
		Mockito.when(node.getYCoordinate()).thenReturn(4);
		Assert.assertTrue(mv.isMoveValid(nodes, player2, node));

		Mockito.when(node.getXCoordinate()).thenReturn(3);
		Mockito.when(node.getYCoordinate()).thenReturn(2);
		Assert.assertTrue(mv.isMoveValid(nodes, player2, node));
		Assert.assertFalse(mv.isMoveValid(nodes, player1, node));
	}

	@Test
	public void hasValidMove() {
		String p1Id = "foo";
		String p2Id = "bar";
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn(p1Id);
		Mockito.when(player2.getId()).thenReturn(p2Id);
		List<Node> nodes = createListOfNodesStateOneMoveMade(8, 8, player1.getId(), player2.getId());

		NodeFinder nf = new NodeFinder();
		NodeCapturer nc = new NodeCapturer(nf);
		MoveValidator mv = new MoveValidator(nc, nf);

		Assert.assertTrue(mv.hasValidMove(nodes, player1));
		Assert.assertTrue(mv.hasValidMove(nodes, player2));

		nodes = createListOfNodesStateFakeWithNoMovesForPlayer2(8, 8, player1.getId(), player2.getId());
		Assert.assertFalse(mv.hasValidMove(nodes, player1));
		Assert.assertFalse(mv.hasValidMove(nodes, player2));

	}
}
