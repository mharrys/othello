package kth.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * The responsibility of this class is to output information about a othello game to the console.
 * 
 * @author Henrik Hygerth
 */
public class OthelloTournamentGameOutputConsole implements OthelloTournamentGameOutput {

	@Override
	public void onStart(Othello othello) {
		System.out.println();
		System.out.println(othello.getPlayers().get(0).getName() + " vs " + othello.getPlayers().get(1).getName());
		System.out.println();
	}

	@Override
	public void onDraw(Othello othello) {
		System.out.println(othello.getBoard().toString());
		System.out.println();
	}

	@Override
	public void onEnd(Othello othello) {
		System.out.println(printScoreOfGame(othello.getScore(), othello.getPlayers()));
	}

	/**
	 * Creates a string describing the score of a game.
	 * @param score the score object of a game
	 * @param players the players of the game
	 * @return the string
	 */
	private String printScoreOfGame(Score score, List<Player> players) {
		final int p1Points = score.getPoints(players.get(0).getId());
		final int p2Points = score.getPoints(players.get(1).getId());
		final String p1Score = players.get(0).getName() + " (" + p1Points + ")";
		final String p2Score = players.get(1).getName() + " (" + p2Points + ")";
		String result = ("Score: " + (p1Points > p2Points ? p1Score + " " + p2Score : p2Score + " " + p1Score));
		return result;
	}
}
