package evolution;

import java.util.Comparator;

class FitnessComparator implements Comparator<IOrganism>{
	
	public FitnessComparator() {
	}
	
	@Override
	public int compare(IOrganism arg0, IOrganism arg1) {
		return arg1.fitness() - arg0.fitness();
	}

}
