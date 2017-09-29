package evolution;

import java.util.Random;

class TestOrganism implements IOrganism {

	private String name;
	
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
	
	private Random random;
	
	public TestOrganism(String name, Random random) {
		this.name = name;
		this.random = random;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public TestOrganism breed(IOrganism mate) {
		if (!(mate instanceof TestOrganism)) {
			throw new UnsupportedOperationException("Cannot breed different species.");
		}
		TestOrganism castMate = (TestOrganism) mate;
		int crossover = random.nextInt(Math.min(name.length(), castMate.getName().length()));
		String newName = name.substring(0, crossover) + castMate.getName().substring(crossover);
		TestOrganism newOrg = new TestOrganism(newName, random);
		return newOrg;
	}

	//Add random letter
	@Override
	public IOrganism mutate() {
		String newName;
		if (random.nextFloat() < 0.3) {
			//Remove char
			int charToRemove = random.nextInt(name.length());
			newName = name.substring(0, charToRemove) + name.substring(charToRemove + 1);
		}
		else {
			//Append random char
			newName = name + letters[random.nextInt(letters.length)];
		}
		return new TestOrganism(newName, random);
	}

	@Override
	public int fitness() {
		int numAs = 0;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == 'a') {
				numAs++;
			}
		}
		return numAs;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
