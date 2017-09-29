import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PuzzleTest extends TestCase {
	
	Puzzle puzzle;

	@Before
	public void setUp() throws Exception {
		PuzzleBuilder builder = new PuzzleBuilder("chCcccdHccsCchdcDDsHSchdDhccSCcHHccc", 1);
		puzzle = builder.getOriginal();
	}

	@Test
	public void testSwapPieces() {
		PuzzlePiece orig = puzzle.getPiece(0, 0);
		PuzzlePiece orig1 = puzzle.getPiece(1, 1);
		puzzle.swapPieces(0, 0, 1, 1);
		assertNotEquals(orig, orig1);
		assertEquals(orig, puzzle.getPiece(1, 1));
		assertEquals(orig1, puzzle.getPiece(0, 0));
	}

	@Test
	public void testClone() {
		Puzzle clone = puzzle.clone();
		assertNotEquals(puzzle, clone);
	}

}
