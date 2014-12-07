package kth.tournament;

import kth.tournament.announcer.MatchAnnouncer;
import kth.tournament.match.Match;
import kth.tournament.presenter.ResultPresenter;
import kth.tournament.score.Score;

import java.util.List;

/**
 * The responsibility of this class is to host a Othello tournament.
 *
 * @author Mattias Harrysson
 */
public class OthelloTournament implements Tournament {

	private Score score;
	private List<Match> matches;
	private ResultPresenter resultPresenter;
	private MatchAnnouncer matchAnnouncer;

	public OthelloTournament(Score score, List<Match> matches, MatchAnnouncer matchAnnouncer, ResultPresenter resultPresenter) {
		this.score = score;
		this.matches = matches;
		this.matchAnnouncer = matchAnnouncer;
		this.resultPresenter = resultPresenter;
	}

	@Override
	public void start() {
		for (Match match : matches) {
			matchAnnouncer.announceStart(match.getCompetitorNames());
			match.start();
		}
		resultPresenter.present(score);
	}

}
