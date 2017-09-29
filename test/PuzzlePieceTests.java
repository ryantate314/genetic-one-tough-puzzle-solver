import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PuzzlePieceTests extends TestCase {
	
	PuzzlePiece piece;

	@Before
	public void setUp() throws Exception {
		piece = new PuzzlePiece();
		piece.getTop().setConnectionType(ConnectionType.Club);
		piece.getRight().setConnectionType(ConnectionType.Spade);
		piece.getBottom().setConnectionType(ConnectionType.Heart);
		piece.getLeft().setConnectionType(ConnectionType.Diamond);
	}

	@Test
	public void testRotateRight() {
		assertEquals(ConnectionType.Club, piece.getTop().getConnectionType());
		assertEquals(ConnectionType.Spade, piece.getRight().getConnectionType());
		assertEquals(ConnectionType.Heart, piece.getBottom().getConnectionType());
		assertEquals(ConnectionType.Diamond, piece.getLeft().getConnectionType());
		piece.rotateRight();
		assertEquals(ConnectionType.Diamond, piece.getTop().getConnectionType());
		assertEquals(ConnectionType.Club, piece.getRight().getConnectionType());
		assertEquals(ConnectionType.Spade, piece.getBottom().getConnectionType());
		assertEquals(ConnectionType.Heart, piece.getLeft().getConnectionType());
	}

	@Test
	public void testRotateLeft() {
		assertEquals(ConnectionType.Club, piece.getTop().getConnectionType());
		assertEquals(ConnectionType.Spade, piece.getRight().getConnectionType());
		assertEquals(ConnectionType.Heart, piece.getBottom().getConnectionType());
		assertEquals(ConnectionType.Diamond, piece.getLeft().getConnectionType());
		piece.rotateLeft();
		assertEquals(ConnectionType.Spade, piece.getTop().getConnectionType());
		assertEquals(ConnectionType.Heart, piece.getRight().getConnectionType());
		assertEquals(ConnectionType.Diamond, piece.getBottom().getConnectionType());
		assertEquals(ConnectionType.Club, piece.getLeft().getConnectionType());
	}

}
