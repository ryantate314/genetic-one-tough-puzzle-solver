import evolution.IOrganism;

public class PuzzleAdapter extends Puzzle implements IOrganism {
	public PuzzleAdapter(String config) throws Exception {
		super();
		
		PuzzleBuilder builder = new PuzzleBuilder(config, 1);
		
	}

	@Override
	public IOrganism breed(IOrganism mate) {
		if (!(mate instanceof PuzzleAdapter)) {
			throw new UnsupportedOperationException("Cannot breed different species.");
		}
		
		return null;
	}

	@Override
	public IOrganism mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fitness() {
		// TODO Auto-generated method stub
		return 0;
	}
}