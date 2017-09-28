package evolution;

public class EvolutionConfig {
	private int populationSize;
	private float mutationRate;
	private long seed;
	private float crossoverRate;
	
	public EvolutionConfig() {
		populationSize = 500;
		mutationRate = 0.1f;
		seed = System.currentTimeMillis();
		crossoverRate = 0.1f;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public float getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(float mutationRate) {
		this.mutationRate = mutationRate;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public float getCrossoverRate() {
		return crossoverRate;
	}

	public void setCrossoverRate(float crossoverRate) {
		this.crossoverRate = crossoverRate;
	}
	
	
}
