import java.util.ArrayList;
import java.util.List;

public class Puzzle {
	
	private static final int WIDTH = 3;
	public int getWidth() {
		return WIDTH;
	}
	private static final int HEIGHT = 3;
	public int getHeight() {
		return HEIGHT;
	}
	private static final int NUM_PIECES = WIDTH * HEIGHT;
	public int getNumPieces() {
		return NUM_PIECES;
	}
			
	private List<PuzzlePiece> pieces;
	
	public Puzzle() {
		pieces = new ArrayList<PuzzlePiece>(NUM_PIECES);
		for (int i = 0; i < NUM_PIECES; i++) {
			pieces.add(new PuzzlePiece());
		}
	}
	
	private int coordsToIndex(int x, int y) {
		int index = x + y * WIDTH;
		if (index >= NUM_PIECES) {
			throw new IndexOutOfBoundsException("X: " + x + ", Y: " + y);
		}
		return index;
	}
	
	public PuzzlePiece getPiece(int x, int y) {
		return pieces.get(coordsToIndex(x, y));
	}
	
	public PuzzlePiece getPiece(int index) {
		return pieces.get(index);
	}
	
	private void setPiece(int x, int y, PuzzlePiece piece) {
		pieces.set(coordsToIndex(x, y), piece);
	}
	
	public void swapPieces(int x1, int y1, int x2, int y2) {
		PuzzlePiece temp = getPiece(x1, y1);
		setPiece(x1, y1, getPiece(x2, y2));
		setPiece(x2, y2, temp);
	}
	
	public int numFit() {
		int score = 0;
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				//Check if piece fits piece to its right
				if (x < WIDTH - 1) {
					if (getPiece(x, y).getRight().fits(getPiece(x + 1, y).getLeft())) {
						score++;
					}
				}
				//Check if piece fits with piece below
				if (y < HEIGHT - 1) {
					if (getPiece(x, y).getBottom().fits(getPiece(x, y + 1).getTop())) {
						score++;
					}
				}
			}
		}
		return score;
	}
	
	public boolean solved() {
		return numFit() == totalFittingConnections();
	}
	
	//Num possible connections in solved puzzle
	private int totalFittingConnections() {
		return 2 * WIDTH * HEIGHT - WIDTH - HEIGHT;
	}
}
