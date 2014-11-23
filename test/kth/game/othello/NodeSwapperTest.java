package kth.game.othello;
/*
import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.ClassicBoardFactory;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NodeSwapperTest {

	private Board createBoard() {
		List<Node> nodes = new ArrayList<Node>();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Node node = Mockito.mock(Node.class);
				Mockito.when(node.getId()).thenReturn(x + "-" + y);
				Mockito.when(node.getXCoordinate()).thenReturn(x);
				Mockito.when(node.getYCoordinate()).thenReturn(y);
				Mockito.when(node.getOccupantPlayerId()).thenReturn("");
			}
		}
		Board board = Mockito.mock(Board.class);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		return board;
	}

	private List<Node> createNodesToSwap() {
		List<Node> nodesToSwap = new ArrayList<Node>();

		Node swap1 = Mockito.mock(Node.class);
		Node swap2 = Mockito.mock(Node.class);
		Node swap3 = Mockito.mock(Node.class);
		
		Mockito.when(swap1.getId()).thenReturn("1-1");
		Mockito.when(swap1.getXCoordinate()).thenReturn(1);
		Mockito.when(swap1.getYCoordinate()).thenReturn(1);

		Mockito.when(swap2.getId()).thenReturn("1-2");
		Mockito.when(swap2.getXCoordinate()).thenReturn(1);
		Mockito.when(swap2.getYCoordinate()).thenReturn(2);

		Mockito.when(swap3.getId()).thenReturn("1-3");
		Mockito.when(swap3.getXCoordinate()).thenReturn(1);
		Mockito.when(swap3.getYCoordinate()).thenReturn(3);

		nodesToSwap.add(swap1);
		nodesToSwap.add(swap2);
		nodesToSwap.add(swap3);

		return nodesToSwap;
	}
	@Test
	public void testSwapList() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Board board = createBoard();
		List<Node> nodesToSwap = createNodesToSwap();

		Node placed = Mockito.mock(Node.class);
		Mockito.when(placed.getId()).thenReturn("1-0");
		Mockito.when(placed.getXCoordinate()).thenReturn(1);
		Mockito.when(placed.getYCoordinate()).thenReturn(0);

		NodeFinder nf = new NodeFinder();
		NodeSwapper ns = new NodeSwapper(nf);

		Assert.assertEquals(4, ns.swap(bf, board, nodesToSwap, "foo", placed.getId()).size());
	}

	@Test
	public void testSwapBoard() {
		BoardFactory bf = new ClassicBoardFactory(8, 8);
		Board board = createBoard();
		List<Node> nodesToSwap = createNodesToSwap();

		Node placed = Mockito.mock(Node.class);
		Mockito.when(placed.getId()).thenReturn("1-0");
		Mockito.when(placed.getXCoordinate()).thenReturn(1);
		Mockito.when(placed.getYCoordinate()).thenReturn(0);

		NodeFinder nf = new NodeFinder();
		NodeSwapper ns = new NodeSwapper(nf);

		String playerId = "foo";
		ns.swap(bf, board, nodesToSwap, playerId, placed.getId());

		List<Integer> yCoor = new ArrayList<Integer>();
		yCoor.add(0);
		yCoor.add(1);
		yCoor.add(2);
		yCoor.add(3);
		for (int i = 0; i < board.getNodes().size(); i++) {
			Node n = board.getNodes().get(i);
			if (n.getXCoordinate() == 1 && yCoor.contains(n.getYCoordinate())) {
				Assert.assertEquals(playerId, n.getOccupantPlayerId());
			} else {
				Assert.assertEquals("", n.getOccupantPlayerId());
			}
		}
	}
}
*/
