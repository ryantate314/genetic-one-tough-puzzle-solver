import java.util.ArrayList;
import java.util.List;

public class Column {
	private List<PuzzlePiece> pieces;
	
	public Column(int height) {
		pieces = new ArrayList<PuzzlePiece>(height);
		for (int i = 0; i < height; i++) {
			pieces.add(new PuzzlePiece());
		}
	}
	
	public void setPiece(int y, PuzzlePiece piece) {
		pieces.set(y, piece);
	}
	
	public PuzzlePiece getPiece(int y) {
		return pieces.get(y);
	}
	
	public int getHeight() {
		return pieces.size();
	}
}
