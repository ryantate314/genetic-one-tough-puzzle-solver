import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PuzzleAdapterTest {

	PuzzleBuilder builder;
	PuzzleAdapter puzzle;
	PuzzleAdapter puzzle2;
	
	@Before
	public void setUp() throws Exception {
		builder = new PuzzleBuilder("cCHdhSDsSShcHDdhhCHscHDcCcdDSDhdcHSs", 1);
		puzzle = builder.newOrganism();
		puzzle2 = builder.newOrganism();
	}

	@Test
	public void removePieceTest() {
		try {
			puzzle.removePiece(puzzle.getPiece(1, 1));
			assertEquals(null, puzzle.getPiece(1, 1));
			//System.out.println(puzzle.toString());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void bubbleUpTest() {
		removePieceTest();
		PuzzlePiece oldBottomLeft = puzzle.getPiece(0, 2);
		puzzle.bubbleUp();
		//System.out.println("After bubble:");
		//System.out.println(puzzle);
		assertEquals(oldBottomLeft, puzzle.getPiece(1, 1));
		assertEquals(null, puzzle.getPiece(0, 2));
	}
	
	@Test
	public void bubbleUpDiagonalTest() {
		PuzzlePiece oldMiddleLeft = puzzle.getPiece(0, 1);
		PuzzlePiece oldMiddleBottom = puzzle.getPiece(1, 2);
		try {
			puzzle.removePiece(1, 0);
			puzzle.removePiece(1, 1);
		} catch (Exception ex) {
			fail();
		}
		puzzle.bubbleUp();
		assertEquals(oldMiddleLeft, puzzle.getPiece(1, 0));
		assertEquals(oldMiddleBottom, puzzle.getPiece(1, 1));
		//System.out.println(puzzle.toString());
	}

	
	@Test
	public void breedTest() {
		PuzzleAdapter child = (PuzzleAdapter) puzzle.breed(puzzle2);
		System.out.println(child);
		for (int y = 0; y < child.getHeight(); y++) {
			for (int x = 0; x < child.getWidth(); x++) {
				assertNotEquals(null, child.getPiece(x, y));
			}
		}
	}
}
