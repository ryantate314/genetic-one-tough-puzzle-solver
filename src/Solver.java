
public class Solver {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java Solver <configuration>");
			return;
		}
		
		String configuration = args[0];
		
		Puzzle puzzle = new Puzzle();
		PuzzleBuilder builder = new PuzzleBuilder();
		try {
			builder.arrange(configuration);
		} catch (Exception e) {
			System.err.println("Invalid configuration.");
			return;
		}
		
		System.out.println(PuzzlePrinter.print(puzzle));
	}

}
