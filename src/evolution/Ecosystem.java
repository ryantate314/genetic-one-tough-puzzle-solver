package evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Ecosystem<T extends IOrganism> {
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
	
	public int getGeneration() {
		return generation;
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
		for (int i = n - 1; i >= 0; i--) {
			population.offer(result.get(i));
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
		
		int pool = totalWeight > 0 ? random.nextInt(totalWeight) : 0;
		
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
	
	@SuppressWarnings("unchecked")
	public void nextGeneration() {
		PriorityQueue<T> newPopulation = new PriorityQueue<T>(getPopulationSize(), new FitnessComparator());
	
		//Take best n and copy into new population
		newPopulation.addAll(getTopN(config.getNumElite()));
	
		int fitnessSum = getFitnessSum();
		
		while (newPopulation.size() < config.getPopulationSize()) {
			T mommy = getWeightedOrganism(fitnessSum);
			T daddy = getWeightedOrganism(fitnessSum);
			
			T offspring = mommy;
			if (shouldCrossover()) {
				offspring = (T) mommy.breed(daddy);
			}
			
			if (shouldMutate()) {
				offspring = (T) offspring.mutate();
			}
			newPopulation.offer(offspring);
		}
		
		generation++;
		population = newPopulation;
	}
}
