import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import evolution.Ecosystem;
import evolution.EvolutionConfig;

/**
 * Performs simulations in parallel to try various seeds to solve the puzzle.
 * 
 * @author ryan.tate
 *
 */
public class Solver implements Callable<PuzzleAdapter> {

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
		
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Set<Future<PuzzleAdapter>> set = new HashSet<Future<PuzzleAdapter>>();
		
		for (int i = 0; i < 100; i++) {
			EvolutionConfig config = new EvolutionConfig();
			config.setCrossoverRate(1);
			config.setMutationRate(0.01f);
			config.setNumElite(5);
			config.setPopulationSize(1000);
			config.setSeed(seed);
			
			Callable<PuzzleAdapter> callable = new Solver(config, configuration, 150);
			Future<PuzzleAdapter> future = pool.submit(callable);
			set.add(future);
			
			seed++;
		}
		
		for (Future<PuzzleAdapter> future : set) {
			try {
				if (future.get().solved()) {
					System.out.println("Solved Puzzle");
					System.out.println(future.get().toString());
					break;
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private EvolutionConfig config;
	private String puzzleConfig;
	private int numGenerations;
	
	public Solver(EvolutionConfig config, String configuration, int numGenerations) {
		this.config = config;
		this.puzzleConfig = configuration;
		this.numGenerations = numGenerations;
	}


	@Override
	public PuzzleAdapter call() throws Exception {
		PuzzleBuilder builder = new PuzzleBuilder(puzzleConfig, config.getSeed());
		Ecosystem<PuzzleAdapter> eco = new Ecosystem<PuzzleAdapter>(builder, config);
		while (!eco.getBest().solved() && eco.getGeneration() < numGenerations ) {
			eco.nextGeneration();
		}
		System.out.println("Thread with seed " + config.getSeed() + " finished fitness of " + eco.getBest().fitness() + " after " + eco.getGeneration() + " generations.");
		if (eco.getBest().solved()) {
			System.out.println(eco.getBest());
		}
		return eco.getBest();
	}

}
