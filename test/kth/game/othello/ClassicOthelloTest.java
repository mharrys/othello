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
		final int midRow = rows / 2;
		final int midCol = cols / 2;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((i == midRow - 2 && j == midCol - 3)        ||
						(i == midRow - 1 && j == midCol - 2)    ||
						(i == midRow - 2 && j == midRow - 1)    ||
						(i == midRow && j == midCol - 1)        ||
						(i == midRow - 1 && j == midCol)        ||
						(i == midRow + 1 && j == midCol)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if ((i == midRow - 3 && j == midCol - 3) ||
						(i == midRow - 2 && j == midCol - 2)    ||
						(i == midRow && j == midCol - 2)        ||
						(i == midRow - 1 && j == midCol - 1)    ||
						(i == midRow && j == midCol)            ||
						(i == midRow + 1 && j == midCol + 1)) {
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

	private Board getBoardStateOneMoveMade(int rows, int cols, String player1Id, String player2Id) {
		Board b = Mockito.mock(Board.class);
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

	private Board getBoardStateSpecialCase(int rows, int cols, String player1Id, String player2Id) {
		Board b = Mockito.mock(Board.class);
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Node n = Mockito.mock(Node.class);
				if ((j == 1 && i == 7) || (j == 4 && i == 7)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn("");
					Mockito.when(n.isMarked()).thenReturn(false);
				} else if ((j == 0 && i== 3) || (j == 1 && i== 4) || (j == 2 && i == 6) ||
							(j == 3 && i == 5) || (j == 3 && i == 6) || (j == 4 && i == 6) ||
							(j == 5 && i == 7) || (j == 7 && i == 5)) {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player2Id);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(player1Id);
					Mockito.when(n.isMarked()).thenReturn(true);
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
	

	@Test
	public void chosenPlayerStartsGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Board b = Mockito.mock(Board.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);
		Mockito.when(b.getNodes()).thenReturn(new ArrayList<Node>());
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
		Board b = Mockito.mock(Board.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);
		Mockito.when(b.getNodes()).thenReturn(new ArrayList<Node>());
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Othello othello = new ClassicOthello(bf, player1, player2);
		othello.start();

		String playerInTurnId = othello.getPlayerInTurn().getId();
		assert(playerInTurnId.equals(player1.getId()) || playerInTurnId.equals(player2.getId()));
	}

	@Test
	public void getNodesToSwap() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateOneMoveMade(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		Assert.assertEquals(1, othello.getNodesToSwap(player2.getId(), "5-4").size());
		Assert.assertEquals(1, othello.getNodesToSwap(player2.getId(), "5-2").size());
		Assert.assertEquals(0, othello.getNodesToSwap(player2.getId(), "5-0").size());
	}

	@Test
	public void getNodesToSwapSpecialCase() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateSpecialCase(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		Assert.assertEquals(2, othello.getNodesToSwap(player2.getId(), "1-7").size());
	}

	@Test
	public void isMoveValid() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateOneMoveMade(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		othello.start(player2.getId());
		Assert.assertFalse(othello.isMoveValid(player2.getId(), "5-0"));
		Assert.assertTrue(othello.isMoveValid(player2.getId(), "5-4"));
		Assert.assertTrue(othello.isMoveValid(player2.getId(), "5-2"));
	}

	@Test
	public void hasVaildMovesStateStart() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateStart(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		Assert.assertTrue(othello.hasValidMove(player1.getId()));
		Assert.assertTrue(othello.hasValidMove(player2.getId()));
	}

	@Test
	public void hasVaildMovesStateMidGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateMidGame(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		Assert.assertTrue(othello.hasValidMove(player1.getId()));
		Assert.assertTrue(othello.hasValidMove(player2.getId()));
	}

	@Test
	public void hasVaildMovesStateFullBoard() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Board b = getBoardStateFullByOnePlayer(8, 8, player1.getId(), player2.getId());
		Mockito.when(bf.constructBoard(player1, player2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, player1, player2);
		Assert.assertFalse(othello.hasValidMove(player1.getId()));
		Assert.assertFalse(othello.hasValidMove(player2.getId()));
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