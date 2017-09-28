package evolution;

import java.util.Random;

class TestOrganism implements IOrganism<TestOrganism> {

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
	public TestOrganism breed(TestOrganism mate) {
		int crossover = random.nextInt(Math.min(name.length(), mate.getName().length()));
		String newName = name.substring(0, crossover) + mate.getName().substring(crossover + 1);
		TestOrganism newOrg = new TestOrganism(newName, random);
		return newOrg;
	}

	//Add random letter
	@Override
	public void mutate() {
		name = name + letters[random.nextInt(letters.length)];
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
