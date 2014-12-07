package kth.tournament.match;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.view.swing.OthelloView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GuiOthelloMatch extends Observable implements Match, Observer {

	private Othello othello;
	private OthelloView othelloView;
	private String startingPlayerId;
	private List<String> playerNames;

	public GuiOthelloMatch(Othello othello, OthelloView othelloView, String startingPlayerId) {
		this.othello = othello;
		this.othello.addGameFinishedObserver(this);
		this.othelloView = othelloView;
		this.startingPlayerId = startingPlayerId;

		playerNames = new ArrayList<String>();
		for (Player player : othello.getPlayers()) {
			playerNames.add(player.getName());
		}
	}

	@Override
	public void start() {
		othelloView.start(startingPlayerId);
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
