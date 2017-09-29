import java.util.Random;

import evolution.IOrganismGenerator;

public class PuzzleBuilder implements IOrganismGenerator<PuzzleAdapter> {

	private Random random;
	private static char[] CHARS = new char[] {'c', 'C', 's', 'S', 'h', 'H', 'd', 'D' };
	
	public String config;
	
	public PuzzleBuilder(String config) {
		this(config, System.currentTimeMillis());
	}
	
	public PuzzleBuilder(String config, long seed) {
		random = new Random(seed);
		this.config = config;
	}
	
	public Puzzle getOriginal() throws Exception {
		return arrange(config);
	}
	
	protected Puzzle arrange(String arrangement) throws Exception {
		return arrange(new Puzzle(), arrangement);
	}
	
	protected Puzzle arrange(Puzzle puzzle, String arrangement) throws Exception {
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
	
	private void randomize(Puzzle puzzle) {
		
	}

	@Override
	public PuzzleAdapter newOrganism() {
		try {
			Puzzle puzzle = getOriginal();
		} catch (Exception ex) {
			return null;
		}
		
		return null;
	}
	
	
	
}
