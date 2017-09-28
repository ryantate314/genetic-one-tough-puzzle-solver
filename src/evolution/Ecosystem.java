package evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Ecosystem<T extends IOrganism<T>> {
	private PriorityQueue<T> population;
	private EvolutionConfig config;
	
	private int generation;
	
	private Random random;
	
	public Ecosystem(IOrganismGenerator<T> generator, EvolutionConfig config) {
		this.config = config;
		population = new PriorityQueue<T>(config.getPopulationSize(), new FitnessComparator());
		for (int i = 0; i < config.getPopulationSize(); i++) {
			population.offer(generator.newOrganism());
		}
		
		generation = 1;
		
		random = new Random(config.getSeed());
	}
	
	public int getPopulationSize() {
		return population.size();
	}
	
	public float getMutationRate() {
		return config.getMutationRate();
	}
	
	public float getCrossoverRate() {
		return config.getCrossoverRate();
	}
	
	public List<T> getTopN(int n) {
		if (n > getPopulationSize()) {
			throw new IndexOutOfBoundsException();
		}
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < n; i++) {
			result.add(population.poll());
		}
		//Add back to priority queue
		for (T organism : result) {
			population.offer(organism);
		}
		return result;
	}
	
	public T getBest() {
		return population.peek();
	}
	
	public int averageFitness() {
		int sum = 0;
		for (T organism : population) {
			sum += organism.fitness();
		}
		return sum / population.size();
	}
	
	private T getWeightedOrganism(int totalWeight) {
		
		int pool = random.nextInt(totalWeight);
		
		for (T organism : population) {
			pool -= organism.fitness();
			if (pool <= 0) {
				return organism;
			}
		}
		//Should never happen
		return population.peek();
	}
	
	private int getFitnessSum() {
		int fitnessSum = 0;
		for (T organism : population) {
			fitnessSum += organism.fitness();
		}
		return fitnessSum;
	}
	
	private boolean shouldCrossover() {
		return random.nextFloat() < getCrossoverRate();
	}
	
	private boolean shouldMutate() {
		return random.nextFloat() < getMutationRate();
	}
	
	public void nextGeneration() {
		PriorityQueue<T> newPopulation = new PriorityQueue<T>(getPopulationSize(), new FitnessComparator());
	
		int fitnessSum = getFitnessSum();
		
		for (int i = 0; i < getPopulationSize(); i++) {
			T mommy = getWeightedOrganism(fitnessSum);
			T daddy = getWeightedOrganism(fitnessSum);
			
			T offspring = mommy;
			if (shouldCrossover()) {
				offspring = mommy.breed(daddy);
			}
			
			if (shouldMutate()) {
				offspring.mutate();
			}
			newPopulation.add(offspring);
		}
		
		generation++;
		population = newPopulation;
	}
}