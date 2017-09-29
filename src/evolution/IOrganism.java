package evolution;

public interface IOrganism {
	public IOrganism breed(IOrganism mate);
	public IOrganism mutate();
	public int fitness();
}
