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
	
	/**
	 * Instantiates the current object and initializes a population using the provided
	 * organism generator.
	 * @param generator
	 * @param config
	 */
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
	
	/**
	 * Returns the best N organisms of the current population. Note: Due to
	 * the underlying PriorityQueue used to store the organisms, this action
	 * must remove the elements from the collection in order to traverse it. These
	 * are added back as not to alter the overall population, but it is not guaranteed
	 * to produce the same results each time due to ties in fitness level.
	 * @param n The number of elites to retrieve.
	 * @return A list of elites from the population.
	 */
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

	/**
	 * Calculates the average fitness level of the entire population.
	 * @return
	 */
	public int averageFitness() {
		return getFitnessSum() / population.size();
	}
	
	/**
	 * Selected a weighted random organism from the population based on fitness.
	 * Selection is made using the discrete cumulative density function of the list.
	 * @param totalWeight Sum of all the weights in the array.
	 * @return
	 */
	/*
	 * I am assuming adding up the total fitness of the population is an expensive task,
	 * that is why it is being passed into the function rather than calculated each time.
	 */
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
	
	/**
	 * Produces a decision based on the crossover probability defined in the config object.
	 * @return
	 */
	private boolean shouldCrossover() {
		return random.nextFloat() < getCrossoverRate();
	}
	
	/**
	 * Produces a decision based on the mutation probability defined in the config object.
	 * @return
	 */
	private boolean shouldMutate() {
		return random.nextFloat() < getMutationRate();
	}
	
	/**
	 * Advances the current population to the next generation. All objects are cloned during
	 * the process.
	 */
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
