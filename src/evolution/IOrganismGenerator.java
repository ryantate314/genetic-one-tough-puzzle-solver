package evolution;

public interface IOrganismGenerator<T extends IOrganism> {
	/**
	 * Produces a new, random organism to become a part of the population.
	 * @return
	 */
	public T newOrganism();
}
