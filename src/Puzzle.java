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
			PuzzlePiece piece = new PuzzlePiece();
			piece.setNumber(i + 1);
			pieces.add(piece);
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
	
	protected void setPiece(int x, int y, PuzzlePiece piece) {
		pieces.set(coordsToIndex(x, y), piece);
	}
	
	protected void removePiece(int x, int y) {
		setPiece(x, y, null);
	}
	
	protected void removePiece(PuzzlePiece piece) throws Exception {
		setPiece(piece, null);
	}
	
	protected void setPiece(PuzzlePiece currentPiece, PuzzlePiece newPiece) throws Exception {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i) != null && pieces.get(i).getNumber() == currentPiece.getNumber()) {
				pieces.set(i, newPiece);
				return;
			}
		}
		throw new Exception("Piece not found.");
	}
	
	public void swapPieces(int x1, int y1, int x2, int y2) {
		PuzzlePiece temp = getPiece(x1, y1);
		setPiece(x1, y1, getPiece(x2, y2));
		setPiece(x2, y2, temp);
	}
	
	public void rotatePieceRight(int x, int y) {
		PuzzlePiece piece = getPiece(x, y);
		piece.rotateRight();
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
	
	public Puzzle clone() {
		Puzzle puzzle = new Puzzle();
		for (int y = 0; y < puzzle.getHeight(); y++) {
			for (int x = 0; x < puzzle.getWidth(); x++) {
				puzzle.setPiece(x, y, (PuzzlePiece)getPiece(x, y).clone());
			}
		}
		return puzzle;
	}
	
	protected PuzzlePiece findPiece(int number) {
		for (PuzzlePiece piece : pieces) {
			if (piece.getNumber() == number) {
				return piece;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return PuzzlePrinter.print(this);
	}
}
