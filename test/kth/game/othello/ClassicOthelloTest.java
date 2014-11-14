package kth.game.othello;

import kth.game.othello.board.BoardFactory;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicOthelloTest {

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

}