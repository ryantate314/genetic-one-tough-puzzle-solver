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

	@Override
	public IOrganism breed(IOrganism mate) {
		if (!(mate instanceof PuzzleAdapter)) {
			throw new UnsupportedOperationException("Cannot breed different species.");
		}
		
		PuzzleAdapter castMate = (PuzzleAdapter) mate;
		PuzzleAdapter child = clone();
		
		if (random.nextBoolean()) {
			//Take rows
			int row = random.nextInt(getWidth());
			for (int y = 0; y < getHeight(); y++) {
				for (int x = row; x < getWidth(); x++) {
					child.setPiece(x, y, castMate.getPiece(x, y).clone());
				}
			}
		} else {
			//Take columns
			int col = random.nextInt(getWidth());
			for (int x = 0; x < getWidth(); x++) {
				for (int y = col; y < getHeight(); y++) {
					child.setPiece(x, y, castMate.getPiece(x, y).clone());
				}
			}
		}
		
		return child;
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