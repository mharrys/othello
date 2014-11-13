package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;

/**
 * A factory for producing classic boards.
 * 
 * @author Henrik Hygerth
 */
public class ClassicBoardFactory implements BoardFactory {
	private int rows = 0;
	private int cols = 0;
	public ClassicBoardFactory(int size) {
		this.rows = size;
		this.cols = size;
	}
	
	@Override
	public Board constructBoard(Player player1, Player player2) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 3 && j == 3) {
					nodes.add(new ClassicNode(j, i, player1.getId()));
				} else if (i == 3 && j == 4) {
					nodes.add(new ClassicNode(j, i, player2.getId()));
				} else if (i == 4 && j == 3) {
					nodes.add(new ClassicNode(j, i, player2.getId()));
				} else if (i == 4 && j == 4) {
					nodes.add(new ClassicNode(j, i, player1.getId()));
				} else {
					nodes.add(new ClassicNode(j, i));
				}
			}		
		}
		return new ClassicBoard(nodes);
	}

	@Override
	public Board constructBoard(List<Node> nodes, List<Node> nodesToSwap, String playerId) {
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodesToSwap.size(); j++) {
				if (nodes.get(i).getId() == nodesToSwap.get(j).getId()) {
					final int x = nodesToSwap.get(j).getXCoordinate();
					final int y = nodesToSwap.get(j).getYCoordinate();
					nodes.set(i, new ClassicNode(x, y, playerId));
				}
			}
		}
		
		return new ClassicBoard(nodes);
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public int getCols() {
		return cols;
	}

}
