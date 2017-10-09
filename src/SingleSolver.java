import evolution.Ecosystem;
import evolution.EvolutionConfig;

/**
 * Performs a single simulation using the provided configuration and seed.
 * @author ryan.tate
 *
 */
public class SingleSolver {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java Solver {configuration} [seed]");
			return;
		}
		
		String configuration = args[0];
		long seed = 58;
		if (args.length == 2) {
			seed = Long.parseLong(args[1]);
		}
		
		EvolutionConfig config = new EvolutionConfig();
		config.setCrossoverRate(1);//Fixes broken breed method
		config.setMutationRate(0.01f);
		config.setNumElite(5);
		config.setPopulationSize(1000);
		config.setSeed(seed);
		
		PuzzleBuilder builder = new PuzzleBuilder(configuration, config.getSeed());
		Ecosystem<PuzzleAdapter> eco = new Ecosystem<PuzzleAdapter>(builder, config);
		
		while (!eco.getBest().solved() && eco.getGeneration() < 150) {
			eco.nextGeneration();
			System.out.println("Generation: " + eco.getGeneration());
			System.out.println("   Best fitness: " + eco.getBest().fitness());
			System.out.println("   Average fitness: " + eco.averageFitness());
		}
		
		if (eco.getBest().solved()) {
			System.out.println("Success!");
		} else {
			System.out.println("So close...");
		}
		System.out.println(eco.getBest());
	}

}
