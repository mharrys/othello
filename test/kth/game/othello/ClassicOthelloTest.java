package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicOthelloTest {

	private Board getBoardStateStart(int rows, int cols, String player1Id, String player2Id) {
		Board b = Mockito.mock(Board.class);
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
		Mockito.when(b.getNodes()).thenReturn(nodes);
		return b;
	}

	private Board getBoardStateMidGame(int rows, int cols, String player1Id, String player2Id) {
		Board b = Mockito.mock(Board.class);
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((i == 2 && j == 1) || (i == 3 && j == 2) || (i == 2 && j == 3) || (i == 4 && j == 3) || (i == 3 && j == 4) || (i == 5 && j == 4)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if ((i == 1 && j == 1) || (i == 2 && j == 2) || (i == 4 && j == 2) || (i == 3 && j == 3) || (i == 4 && j == 4) || (i == 5 && j == 5)) {
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
		Mockito.when(b.getNodes()).thenReturn(nodes);
		return b;
	}

	private Board getBoardStateFullByOnePlayer(int rows, int cols, String player1Id, String player2Id) {
		Board b = Mockito.mock(Board.class);
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
				Mockito.when(n.isMarked()).thenReturn(true);
				Mockito.when(n.getId()).thenReturn(j + "-" + i);
				Mockito.when(n.getXCoordinate()).thenReturn(j);
				Mockito.when(n.getYCoordinate()).thenReturn(i);
				nodes.add(n);
			}
		}
		Mockito.when(b.getNodes()).thenReturn(nodes);
		return b;
	}

	@Test
	public void chosenPlayerStartsGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(null);
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Othello othello = new ClassicOthello(bf, player1, player2);

		othello.start(player1.getId());
		Assert.assertEquals(othello.getPlayerInTurn().getId(), player1.getId());

		othello.start(player2.getId());
		Assert.assertEquals(othello.getPlayerInTurn().getId(), player2.getId());
	}

	@Test
	public void randomPlayerStartsGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(null);
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Othello othello = new ClassicOthello(bf, player1, player2);
		othello.start();

		String playerInTurnId = othello.getPlayerInTurn().getId();
		assert(playerInTurnId.equals(player1.getId()) || playerInTurnId.equals(player2.getId()));
	}

	@Test
	public void isGameActiveStateStart() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateStart(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		// Start of game
		Assert.assertTrue(othello.isActive());
	}
	
	@Test
	public void isGameActiveStateMidGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateMidGame(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		// Middle of a game
		Assert.assertTrue(othello.isActive());
	}

	@Test
	public void isGameActiveStateFullBoardByOnePlayer() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateFullByOnePlayer(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		// End of game, board full by one player
		Assert.assertFalse(othello.isActive());
	}

}