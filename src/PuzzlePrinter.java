
public class PuzzlePrinter {
	public static String print(Puzzle puzzle) {
		StringBuilder out = new StringBuilder();
		for (int y = 0; y < puzzle.getHeight(); y++) {
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				out.append(String.format(" %s  ", toChar(piece.getTop())));
			}
			out.append("\n");
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				out.append(String.format("%s %s ", toChar(piece.getLeft()), toChar(piece.getRight())));
			}
			out.append("\n");
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				out.append(String.format(" %s  ", toChar(piece.getBottom())));
			}
			out.append("\n");
		}
		return out.toString();
	}
	
	private static char toChar(Connector con) {
		char out = 'C';
		switch(con.getConnectionType()) {
		case Club:
			out = 'C';
			break;
		case Diamond:
			out = 'D';
			break;
		case Heart:
			out = 'H';
			break;
		case Spade:
			out = 'S';
			break;
		}
		if (con.getGender() == Gender.Female) {
			out = Character.toLowerCase(out);
		}
		return out;
	}
}
