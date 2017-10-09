import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PuzzleBuilderTest {
	
	PuzzleBuilder builder;

	@Before
	public void setUp() throws Exception {
		builder = new PuzzleBuilder("cCHdhSDsSShcHDdhhCHscHDcCcdDSDhdcHSs", 1);
	}

	/**
	 * Ensure the puzzle pieces are given the correct numbering when constructed.
	 */
	@Test
	public void testGetOriginal() {
		try {
			PuzzleAdapter original = builder.getOriginal();
			int number = 1;
			for (int y = 0; y < original.getHeight(); y++) {
				for (int x = 0; x < original.getWidth(); x++) {
					assertEquals(number, original.getPiece(x, y).getNumber());
					number++;
				}
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testRandomize() {
		try {
			PuzzleAdapter puzzle = builder.newOrganism();
			assertTrue(fullAndUnique(puzzle));
		} catch (Exception ex) {
			fail();
		}
	}
	
	public static boolean fullAndUnique(Puzzle puzzle) {
		for (int i = 1; i <= puzzle.getNumPieces(); i++) {
			if (puzzle.findPiece(i) == null) {
				return false;
			}
		}
		return true;
	}

}
