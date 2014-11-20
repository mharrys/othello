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
				} else if ((i== 3 && j == 0) || (i== 4 && j == 1) || (i == 6 && j == 2) ||
							(i == 5 && j == 3) || (i == 6 && j == 3) || (i == 6 && j == 4) ||
							(i == 7 && j == 5) || (i == 5 && j == 7)) {
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
	
	private List<Player> generate2Players() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");
		
		return players;
	}
	


	@Test
	public void getNodesToSwap() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateOneMoveMade(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		Assert.assertEquals(1, othello.getNodesToSwap(p2.getId(), "5-4").size());
		Assert.assertEquals(1, othello.getNodesToSwap(p2.getId(), "5-2").size());
		Assert.assertEquals(0, othello.getNodesToSwap(p2.getId(), "5-0").size());
	}

	@Test
	public void getNodesToSwapSpecialCase() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateSpecialCase(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		Assert.assertEquals(2, othello.getNodesToSwap(p2.getId(), "1-7").size());
	}

	@Test
	public void isMoveValid() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateOneMoveMade(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		othello.start(p2.getId());
		Assert.assertFalse(othello.isMoveValid(p2.getId(), "5-0"));
		Assert.assertTrue(othello.isMoveValid(p2.getId(), "5-4"));
		Assert.assertTrue(othello.isMoveValid(p2.getId(), "5-2"));
	}

	@Test
	public void hasVaildMovesStateStart() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateStart(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		Assert.assertTrue(othello.hasValidMove(p1.getId()));
		Assert.assertTrue(othello.hasValidMove(p2.getId()));
	}

	@Test
	public void hasVaildMovesStateMidGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateMidGame(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		Assert.assertTrue(othello.hasValidMove(p1.getId()));
		Assert.assertTrue(othello.hasValidMove(p2.getId()));
	}

	@Test
	public void hasVaildMovesStateFullBoard() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateFullByOnePlayer(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		Assert.assertFalse(othello.hasValidMove(p1.getId()));
		Assert.assertFalse(othello.hasValidMove(p2.getId()));
	}

	@Test
	public void isGameActiveStateStart() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateStart(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		// Start of game
		Assert.assertTrue(othello.isActive());
	}
	
	@Test
	public void isGameActiveStateMidGame() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateMidGame(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		// Middle of a game
		Assert.assertTrue(othello.isActive());
	}

	@Test
	public void isGameActiveStateFullBoardByOnePlayer() {
		BoardFactory bf = Mockito.mock(BoardFactory.class);
		PlayerSwitcher ps = Mockito.mock(PlayerSwitcher.class);

		List<Player> players = generate2Players();
		Player p1 = players.get(0);
		Player p2 = players.get(1); 
		Mockito.when(ps.getPlayers()).thenReturn(players);

		Board b = getBoardStateFullByOnePlayer(8, 8, p1.getId(), p2.getId());
		Mockito.when(bf.constructBoard(p1, p2)).thenReturn(b);

		Othello othello = new ClassicOthello(bf, ps);
		// End of game, board full by one player
		Assert.assertFalse(othello.isActive());
	}

}