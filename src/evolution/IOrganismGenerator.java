package evolution;

public interface IOrganismGenerator<T extends IOrganism> {
	public T newOrganism();
}
