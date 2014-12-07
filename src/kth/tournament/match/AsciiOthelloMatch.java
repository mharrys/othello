package kth.tournament.match;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AsciiOthelloMatch extends Observable implements Match, Observer {

	private Othello othello;
	private String startingPlayerId;
	private List<String> playerNames;

	public AsciiOthelloMatch(Othello othello, String startingPlayerId) {
		this.othello = othello;
		this.othello.addGameFinishedObserver(this);
		this.startingPlayerId = startingPlayerId;

		playerNames = new ArrayList<String>();
		for (Player player : othello.getPlayers()) {
			playerNames.add(player.getName());
		}
	}

	@Override
	public void start() {
		othello.start(startingPlayerId);
		while (othello.isActive()) {
			othello.move();
			System.out.println();
			System.out.println(othello.getBoard());
		}
	}

	@Override
	public List<String> getCompetitorNames() {
		return playerNames;
	}

	@Override
	public void update(Observable observable, Object object) {
		String winningPlayerId = othello.getScore().getPlayersScore().get(0).getPlayerId();
		setChanged();
		notifyObservers(winningPlayerId);
	}

}
