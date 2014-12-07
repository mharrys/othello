package kth.tournament.announcer;

import java.util.List;

public class AsciiMatchAnnouncer implements MatchAnnouncer {

	@Override
	public void announceStart(List<String> competitors) {
		System.out.println();
		System.out.print("New match: ");
		int vs = competitors.size() - 1;
		for (String competitor : competitors) {
			System.out.print(competitor + (vs > 0 ? " vs " : ""));
			vs--;
		}
		System.out.println();
	}

}
