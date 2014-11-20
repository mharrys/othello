package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerSwitcherTest {


	@Test
	public void chosenPlayerStartsGame() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
	
		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		PlayerSwitcher ps = new PlayerSwitcher(players);

		ps.setStartingPlayer(player1.getId());
		Assert.assertEquals(ps.getPlayerInTurn().getId(), player1.getId());

		ps.setStartingPlayer(player2.getId());
		Assert.assertEquals(ps.getPlayerInTurn().getId(), player2.getId());
	}

	@Test
	public void randomPlayerStartsGame() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		PlayerSwitcher ps = new PlayerSwitcher(players);
		ps.setStartingPlayer();

		String playerInTurnId = ps.getPlayerInTurn().getId();
		assert(playerInTurnId.equals(player1.getId()) || playerInTurnId.equals(player2.getId()));
	}

}
