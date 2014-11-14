package kth.game.othello;

import kth.game.othello.player.Player;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicOthelloTest {

	@Test
	public void chosenPlayerStartsGame() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Othello othello = new ClassicOthello(null, player1, player2);

		othello.start(player1.getId());
		Assert.assertEquals(othello.getPlayerInTurn().getId(), player1.getId());

		othello.start(player2.getId());
		Assert.assertEquals(othello.getPlayerInTurn().getId(), player2.getId());
	}

	@Test
	public void randomPlayerStartsGame() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		Othello othello = new ClassicOthello(null, player1, player2);
		othello.start();

		String playerInTurnId = othello.getPlayerInTurn().getId();
		assert(playerInTurnId.equals(player1.getId()) || playerInTurnId.equals(player2.getId()));
	}

}