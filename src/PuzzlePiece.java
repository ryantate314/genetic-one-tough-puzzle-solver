public class PuzzlePiece {
	private Connector top;
	private Connector right;
	private Connector bottom;
	private Connector left;
	private int number;
	
	public PuzzlePiece() {
		top = Connector.defaultConnector();
		right = Connector.defaultConnector();
		bottom = Connector.defaultConnector();
		left = Connector.defaultConnector();
		number = -1;
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
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void rotateRight() {
		Connector temp = getTop();
		top = left;
		left = bottom;
		bottom = right;
		right = temp;
	}
	
	public void rotateLeft() {
		Connector temp = getTop();
		top = right;
		right = bottom;
		bottom = left;
		left = temp;
	}
	
	public PuzzlePiece clone() {
		PuzzlePiece clone = new PuzzlePiece();
		clone.top = (Connector) top.clone();
		clone.right = (Connector) right.clone();
		clone.bottom = (Connector) bottom.clone();
		clone.left = (Connector) left.clone();
		clone.number = this.number;
		return clone;
	}
	
	@Override
	public boolean equals(Object b) {
		if (b == null) return false;
		if (!(b instanceof PuzzlePiece)) return false;
		boolean match = true;
		PuzzlePiece castB = (PuzzlePiece) b;
		match &= getTop().equals(castB.getTop());
		match &= getRight().equals(castB.getRight());
		match &= getBottom().equals(castB.getBottom());
		match &= getLeft().equals(castB.getLeft());
		return match;
	}
	
}
