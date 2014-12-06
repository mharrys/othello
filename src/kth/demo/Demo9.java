package kth.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kth.game.OthelloTournament;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerImpl;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.AggressiveMoveStrategy;
import kth.game.othello.player.movestrategy.NaiveMoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

/**
 * Starts an Othello game tournament between four different computers on a classic board using either a GUI or console.
 *
 * @author Henrik Hygerth
 */
public class Demo9 {

	/**
	 * Demo entry point.
	 */
	public static void main(String[] args) {
		List<Player> players = new ArrayList<Player>();
		Player naivePlayer = new PlayerImpl("naivePlayer", "Naive Player", Type.COMPUTER, new NaiveMoveStrategy());
		Player randomPlayer = new PlayerImpl("randomPlayer", "Random Player", Type.COMPUTER, new RandomMoveStrategy());
		Player aggressivePlayer = new PlayerImpl("aggressivePlayer", "Aggressive Player", Type.COMPUTER, new AggressiveMoveStrategy());
		
		players.add(naivePlayer);
		players.add(randomPlayer);
		players.add(aggressivePlayer);
		OthelloTournament t = new OthelloTournament(players);

		Scanner scanner = new Scanner(System.in);
		System.out.println("View games in GUI view? (y or n)");
		System.out.print("> ");
		char c = scanner.next().charAt(0);

		if (c == 'y') {
			t.run(100, 100);
		} else {
			t.run();
		}
		scanner.close();
	}

}
