import java.util.ArrayList;
import java.util.List;

public class PuzzlePiece {
	private Connector top;
	private Connector right;
	private Connector bottom;
	private Connector left;
	
	public PuzzlePiece() {
		top = Connector.defaultConnector();
		right = Connector.defaultConnector();
		bottom = Connector.defaultConnector();
		left = Connector.defaultConnector();
	}
	

	public Connector getTop() {
		return top;
	}

	public void setTop(Connector top) {
		this.top = top;
	}

	public Connector getRight() {
		return right;
	}

	public void setRight(Connector right) {
		this.right = right;
	}

	public Connector getBottom() {
		return bottom;
	}

	public void setBottom(Connector bottom) {
		this.bottom = bottom;
	}

	public Connector getLeft() {
		return left;
	}

	public void setLeft(Connector left) {
		this.left = left;
	}
	
}
