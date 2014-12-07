package kth.demo;

import java.util.Arrays;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.AggressiveMoveStrategy;
import kth.game.othello.player.movestrategy.NaiveMoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.tournament.AsciiOthelloTournamentFactory;
import kth.tournament.GuiOthelloTournamentFactory;
import kth.tournament.Tournament;
import kth.tournament.TournamentFactory;

/**
 * Starts an Othello game tournament between four different computers on a classic board using either a GUI or console.
 *
 * @author Henrik Hygerth
 * @author Mattias Harrysson
 */
public class Demo9 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		Type type = Type.COMPUTER;
		Player naivePlayer = new PlayerImpl("p1", "Naive Player", type, new NaiveMoveStrategy());
		Player randomPlayer = new PlayerImpl("p2", "Random Player", type, new RandomMoveStrategy());
		Player aggressivePlayer = new PlayerImpl("p3", "Aggressive Player", type, new AggressiveMoveStrategy());
		// TODO: Fourth player implementation
		List<Player> players = Arrays.asList(naivePlayer, randomPlayer, aggressivePlayer);

		TournamentFactory factory = new AsciiOthelloTournamentFactory();
		Tournament tournament = factory.createTournament(players);
		tournament.start();
	}

}
