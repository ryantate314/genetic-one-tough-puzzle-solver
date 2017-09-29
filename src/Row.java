import java.util.ArrayList;
import java.util.List;

public class Row {
	private List<PuzzlePiece> pieces;
	
	public Row(int width) {
		pieces = new ArrayList<PuzzlePiece>(width);
		for (int i = 0; i < width; i++) {
			pieces.add(new PuzzlePiece());
		}
	}
	
	public void setPiece(int x, PuzzlePiece piece) {
		pieces.set(x, piece);
	}
	
	public PuzzlePiece getPiece(int x) {
		return pieces.get(x);
	}
	
	public int getWidth() {
		return pieces.size();
	}
}
