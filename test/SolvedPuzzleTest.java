import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SolvedPuzzleTest {
	
	Puzzle puzzle;
	PuzzleBuilder builder;

	@Before
	public void setUp() throws Exception {
		puzzle = new Puzzle();
		builder = new PuzzleBuilder(1);
		builder.arrange(puzzle, "chCcccdHccsCchdcDDsHSchdDhccSCcHHccc");
		System.out.println(PuzzlePrinter.print(puzzle));
	}

	@Test
	public void scoreTest() {
		assertEquals(puzzle.numFit(), 12);
	}
	
	@Test
	public void solvedTest() {
		assertTrue(puzzle.solved());
	}

}
