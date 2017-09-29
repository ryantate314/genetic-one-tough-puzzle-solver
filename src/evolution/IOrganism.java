package evolution;

public interface IOrganism {
	public IOrganism breed(IOrganism mate);
	public void mutate();
	public int fitness();
}
