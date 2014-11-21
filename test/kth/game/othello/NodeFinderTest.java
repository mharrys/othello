package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NodeFinderTest {

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
	public void getNodeFromId() {
		NodeFinder nf = new NodeFinder();
		String player1Id = "foo";
		String player2Id = "bar";
		List<Node> nodes = createListOfNodes(8, 8, player1Id, player2Id);
		Assert.assertEquals("4-4", nf.getNodeFromId(nodes, "4-4").getId());
		Assert.assertEquals("0-7", nf.getNodeFromId(nodes, "0-7").getId());
	}

	@Test
	public void getNodeFromGrid() {
		NodeFinder nf = new NodeFinder();
		String player1Id = "foo";
		String player2Id = "bar";
		List<Node> nodes = createListOfNodes(8, 8, player1Id, player2Id);
		Assert.assertEquals("4-4", nf.getNodeFromGrid(nodes, 4, 4).getId());
		Assert.assertEquals("0-7", nf.getNodeFromGrid(nodes, 0, 7).getId());
	}

	@Test
	public void getAdjacentMarkedNodes() {
		NodeFinder nf = new NodeFinder();
		String player1Id = "foo";
		String player2Id = "bar";
		List<Node> nodes = createListOfNodes(8, 8, player1Id, player2Id);

		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(3);
		Mockito.when(node.getYCoordinate()).thenReturn(2);
		Assert.assertEquals(2, nf.getAdjacentMarkedNodes(nodes, node).size());

		Mockito.when(node.getXCoordinate()).thenReturn(3);
		Mockito.when(node.getYCoordinate()).thenReturn(3);
		Assert.assertEquals(3, nf.getAdjacentMarkedNodes(nodes, node).size());
	}

	@Test
	public void getAdjacentOpponentNodes() {
		NodeFinder nf = new NodeFinder();
		String player1Id = "foo";
		String player2Id = "bar";
		List<Node> nodes = createListOfNodes(8, 8, player1Id, player2Id);

		Player player = Mockito.mock(Player.class);
		Mockito.when(player.getId()).thenReturn(player1Id);
		Node node = Mockito.mock(Node.class);
		Mockito.when(node.getXCoordinate()).thenReturn(3);
		Mockito.when(node.getYCoordinate()).thenReturn(2);
		Assert.assertEquals(1, nf.getAdjacentOpponentNodes(nodes, player, node).size());

		Mockito.when(node.getXCoordinate()).thenReturn(3);
		Mockito.when(node.getYCoordinate()).thenReturn(3);
		Assert.assertEquals(2, nf.getAdjacentOpponentNodes(nodes, player, node).size());
	}
}
