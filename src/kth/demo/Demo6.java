package kth.demo;

import kth.game.AsciiGameFactory;
import kth.game.Game;
import kth.game.GameFactory;
import kth.game.othello.board.factory.Diamond;
import kth.game.othello.board.factory.NodeData;
import kth.game.othello.player.OthelloPlayer;
import kth.game.othello.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Setup and run a game between three computers on a diamond board.
 *
 * @author Mattias Harrysson
 */
public class Demo6 {

	/**
	 * Game entry point.
	 */
	public static void main(String[] args) {
		Player player1 = new OthelloPlayer("p1", "Player 1", Player.Type.COMPUTER);
		Player player2 = new OthelloPlayer("p2", "Player 2", Player.Type.COMPUTER);
		Player player3 = new OthelloPlayer("p3", "Player 3", Player.Type.COMPUTER);
		List<Player> players = Arrays.asList(player1, player2, player3);

		Diamond diamond = new Diamond();
		Set<NodeData> nodesData = diamond.getNodes(9, players);

		GameFactory factory = new AsciiGameFactory();
		Game game = factory.createGame(nodesData, players);
		game.run();
	}

}