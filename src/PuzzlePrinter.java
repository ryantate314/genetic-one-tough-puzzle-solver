
public class PuzzlePrinter {
	public static String print(Puzzle puzzle) {
		StringBuilder out = new StringBuilder();
		for (int y = 0; y < puzzle.getHeight(); y++) {
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				char c = 'n';
				if (piece != null) {
					c = toChar(piece.getTop());
				}
				out.append(String.format(" %s  ", c));
			}
			out.append("\n");
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				char c1 = 'n';
				char c2 = 'n';
				char d = '-';
				if (piece != null) {
					c1 = toChar(piece.getLeft());
					d = (piece.getNumber() + "").charAt(0);
					c2 = toChar(piece.getRight());
				}
				out.append(String.format("%s%c%s ", c1, d, c2));
			}
			out.append("\n");
			for (int x = 0; x < puzzle.getWidth(); x++) {
				PuzzlePiece piece = puzzle.getPiece(x, y);
				char c = 'n';
				if (piece != null) {
					c = toChar(piece.getBottom());
				}
				out.append(String.format(" %s  ", c));
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
