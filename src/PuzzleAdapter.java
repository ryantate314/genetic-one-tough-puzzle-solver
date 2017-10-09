import java.util.Random;

import evolution.IOrganism;

public class PuzzleAdapter extends Puzzle implements IOrganism {
	
	Random random;
	private long seed;
	
	public PuzzleAdapter(long seed) throws Exception {
		super();
		
		this.random = new Random(seed);
		this.seed = seed;
	}
	
	/**
	 * Note: Not guaranteed to work if more than {width} pieces are removed.
	 * Postcondition: If (width * n) pieces were removed, then there are no holes in the
	 * top rows of the puzzle.
	 */
	protected void bubbleUp() {
		for (int y = 1; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (getPiece(x, y) == null) continue;
				//Check if hole above
				for (int x2 = 0; x2 < getWidth(); x2++) {
					if (getPiece(x2, y - 1) == null) {
						swapPieces(x, y, x2, y - 1);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Note: Not guaranteed to work if more than {height} pieces are removed.
	 * Postcondition: If (height * n) pieces were removed, then there are no holes in the
	 * left columns of the puzzle.
	 */
	protected void bubbleLeft() {
		for (int x = 1; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (getPiece(x, y) == null) continue;
				//Check if hole to left
				for (int y2 = 0; y2 < getHeight(); y2++) {
					if (getPiece(x - 1, y2) == null) {
						swapPieces(x, y, x - 1, y2);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public IOrganism breed(IOrganism mate) {
		if (!(mate instanceof PuzzleAdapter)) {
			throw new UnsupportedOperationException("Cannot breed different species.");
		}
		
		PuzzleAdapter castMate = (PuzzleAdapter) mate;
		PuzzleAdapter child = clone();
		//System.out.println("Breed start...");
		//System.out.println(child);
		try {
			if (random.nextBoolean()) {
				//Take row
				int row = random.nextInt(getWidth());
				//Remove pieces from child before merging.
				for (int x = 0; x < getWidth(); x++) {
					child.removePiece(castMate.getPiece(x, row));
				}
				child.bubbleUp();
				//Tack row onto bottom of child
				for (int x = 0; x < getWidth(); x++) {
					child.setPiece(x, getHeight() - 1, castMate.getPiece(x, row));
				}
				//System.out.println(child);
				if (child.holes()) {
					System.out.println(child);
					System.out.println("Algorithm error");
				}
			} else {
				//Take column
				int col = random.nextInt(getHeight());
				//Remove pieces from child before merging.
				for (int y = 0; y < getHeight(); y++) {
					child.removePiece(castMate.getPiece(col, y));
				}
				child.bubbleLeft();
				//System.out.println(child);
				//Tack row onto right of child
				for (int y = 0; y < getHeight(); y++) {
					child.setPiece(getWidth() - 1, y, castMate.getPiece(col, y));
				}
				if (child.holes()) {
					System.out.println(child);
					System.out.println("Algorithm error");
				}
			}
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
			if (!PuzzleBuilderTest.fullAndUnique(child)) {
				System.out.println("Problem.");
			}
			
		return child;
	}
	
	private boolean holes() {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (getPiece(x, y) == null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public IOrganism mutate() {
		PuzzleAdapter mutant = this.clone();
		int mutation = random.nextInt(4);
		switch (mutation) {
		case 0:
			//Swap piece
			mutant.swapPieces(random.nextInt(getWidth()), random.nextInt(getHeight()), random.nextInt(getWidth()), random.nextInt(getHeight()));
			break;
		case 1:
			//Rotate piece
			mutant.rotatePieceRight(random.nextInt(getWidth()), random.nextInt(getHeight()));
			break;
			//TODO implement swap column and rows
		}
		return mutant;
	}

	@Override
	public int fitness() {
		return numFit();
	}
	
	@Override
	public PuzzleAdapter clone() {
		PuzzleAdapter puzzle;
		try {
			puzzle = new PuzzleAdapter(seed);
		
			for (int y = 0; y < puzzle.getHeight(); y++) {
				for (int x = 0; x < puzzle.getWidth(); x++) {
					puzzle.setPiece(x, y, (PuzzlePiece)getPiece(x, y).clone());
				}
			}
		} catch (Exception e) {
			return null;
		}
		return puzzle;
	}
	
}