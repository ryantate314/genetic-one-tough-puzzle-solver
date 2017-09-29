import java.io.IOException;

import evolution.Ecosystem;
import evolution.EvolutionConfig;

public class Solver {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java Solver <configuration>");
			return;
		}
		
		String configuration = args[0];
		
		PuzzleBuilder builder = new PuzzleBuilder(configuration, 2);
		EvolutionConfig config = new EvolutionConfig();
		Ecosystem<PuzzleAdapter> eco = new Ecosystem<PuzzleAdapter>(builder, config);
		while (!eco.getBest().solved() && eco.getGeneration() <= 500) {
			System.out.println("Generation: " + eco.getGeneration());
			System.out.println("Average fitness: " + eco.averageFitness());
			System.out.println("Best: ( " + eco.getBest().fitness() + ")");
			System.out.println(eco.getBest().toString());
			
			eco.nextGeneration();
//			try {
//				//System.in.read();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

}
