package evolution;

public interface IOrganism<T> {
	public T breed(T mate);
	public void mutate();
	public int fitness();
}
