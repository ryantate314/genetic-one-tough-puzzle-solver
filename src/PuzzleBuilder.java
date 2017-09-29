import java.util.Random;

import evolution.IOrganismGenerator;

public class PuzzleBuilder implements IOrganismGenerator<PuzzleAdapter> {

	private Random random;
	private static char[] CHARS = new char[] {'c', 'C', 's', 'S', 'h', 'H', 'd', 'D' };
	
	public String config;
	private long seed;
	
	public PuzzleBuilder(String config) {
		this(config, System.currentTimeMillis());
	}
	
	public PuzzleBuilder(String config, long seed) {
		random = new Random(seed);
		this.config = config;
		this.seed = seed;
	}
	
	public PuzzleAdapter getOriginal() throws Exception {
		return arrange(config);
	}
	
	protected PuzzleAdapter arrange(String arrangement) throws Exception {
		return arrange(new PuzzleAdapter(seed), arrangement);
	}
	
	protected PuzzleAdapter arrange(PuzzleAdapter puzzle, String arrangement) throws Exception {
		//TODO Validate the provided arrangement is a possible permutation of config
		if (arrangement.length() != puzzle.getNumPieces() * 4) {
			throw new Exception("Invalid arrangement.");
		}
		
		for (int pieceIndex = 0; pieceIndex < puzzle.getNumPieces(); pieceIndex++) {
			PuzzlePiece piece = puzzle.getPiece(pieceIndex);
			setPiece(piece, arrangement.substring(pieceIndex * 4, pieceIndex * 4 + 4));
		}
		return puzzle;
	}
	
	private void setPiece(PuzzlePiece piece, String sides) {
		if (sides.length() != 4) {
			throw new IndexOutOfBoundsException();
		}
		
		setConnector(piece.getTop(), sides.charAt(0));
		setConnector(piece.getRight(), sides.charAt(1));
		setConnector(piece.getBottom(), sides.charAt(2));
		setConnector(piece.getLeft(), sides.charAt(3));
	}
	
	private void setConnector(Connector connector, char c) {
		switch (c) {
		case 'C':
			connector.setGender(Gender.Male);
			connector.setConnectionType(ConnectionType.Club);
			break;
		case 'c':
			connector.setGender(Gender.Female);
			connector.setConnectionType(ConnectionType.Club);
			break;
		case 'H':
			connector.setGender(Gender.Male);
			connector.setConnectionType(ConnectionType.Heart);
			break;
		case 'h':
			connector.setGender(Gender.Female);
			connector.setConnectionType(ConnectionType.Heart);
			break;
		case 'D':
			connector.setGender(Gender.Male);
			connector.setConnectionType(ConnectionType.Diamond);
			break;
		case 'd':
			connector.setGender(Gender.Female);
			connector.setConnectionType(ConnectionType.Diamond);
			break;
		case 'S':
			connector.setGender(Gender.Male);
			connector.setConnectionType(ConnectionType.Spade);
			break;
		case 's':
			connector.setGender(Gender.Female);
			connector.setConnectionType(ConnectionType.Spade);
			break;
		}
	}
	
	public void randomize(Puzzle puzzle) {
		//Swap random number of pieces
		for (int i = 0; i < random.nextInt(100); i++) {
			int x = random.nextInt(puzzle.getWidth());
			int y = random.nextInt(puzzle.getHeight());
			int x2 = random.nextInt(puzzle.getWidth());
			int y2;
			do {
				y2 = random.nextInt(puzzle.getHeight());
			} while (y2 == y);
			puzzle.swapPieces(x, y, x2, y2);
		}
		//Rotate each piece a random number of times
		for (int y = 0; y < puzzle.getHeight(); y++) {
			for (int x = 0; x < puzzle.getWidth(); x++) {
				for (int i = 0; i < random.nextInt(4); i++) {
					puzzle.getPiece(x, y).rotateRight();
				}
			}
		}
	}

	@Override
	public PuzzleAdapter newOrganism() {
		PuzzleAdapter puzzle;
		try {
			puzzle = getOriginal();
			randomize(puzzle);
		} catch (Exception ex) {
			return null;
		}
		
		return puzzle;
	}
	
	
	
}
