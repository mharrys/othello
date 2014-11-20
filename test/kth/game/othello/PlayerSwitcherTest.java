package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerSwitcherTest {

	private List<Player> create2Players() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");

		return players;
	}

	private List<Player> create3Players() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Player player3 = Mockito.mock(Player.class);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		Mockito.when(player1.getId()).thenReturn("foo");
		Mockito.when(player2.getId()).thenReturn("bar");
		Mockito.when(player3.getId()).thenReturn("foobar");

		return players;
	}

	@Test
	public void switchToNextPlayer3Players() {
		List<Player> players = create3Players();

		Player p1 = players.get(0);
		Player p2 = players.get(1);
		Player p3 = players.get(2);

		PlayerSwitcher ps = new PlayerSwitcher(players);
		ps.setStartingPlayer(p1.getId());
		Assert.assertEquals(p1, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p2, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p3, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p1, ps.getPlayerInTurn());
	}

	@Test
	public void switchToNextPlayer2Players() {
		List<Player> players = create2Players();

		Player p1 = players.get(0);
		Player p2 = players.get(1);
		
		PlayerSwitcher ps = new PlayerSwitcher(players);
		ps.setStartingPlayer(p1.getId());
		Assert.assertEquals(p1, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p2, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p1, ps.getPlayerInTurn());
		ps.switchToNextPlayer();
		Assert.assertEquals(p2, ps.getPlayerInTurn());
	}

	@Test
	public void chosenPlayerStartsGame() {
		List<Player> players = create2Players();

		Player p1 = players.get(0);
		Player p2 = players.get(1);

		PlayerSwitcher ps = new PlayerSwitcher(players);

		ps.setStartingPlayer(p1.getId());
		Assert.assertEquals(p1, ps.getPlayerInTurn());

		ps.setStartingPlayer(p2.getId());
		Assert.assertEquals(p2, ps.getPlayerInTurn());
	}

	@Test
	public void randomPlayerStartsGame() {
		List<Player> players = create2Players();

		Player p1 = players.get(0);
		Player p2 = players.get(1);

		PlayerSwitcher ps = new PlayerSwitcher(players);
		ps.setStartingPlayer();

		Player playerInTurnId = ps.getPlayerInTurn();
		assert(playerInTurnId.equals(p1) || playerInTurnId.equals(p2));
	}

}
