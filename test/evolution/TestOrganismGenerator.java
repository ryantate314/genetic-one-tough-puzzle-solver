package evolution;

import java.util.Random;

public class TestOrganismGenerator implements IOrganismGenerator<TestOrganism> {
	
	private Random random;
	
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
	
	public TestOrganismGenerator(Random random) {
		this.random = random;
	}

	@Override
	public TestOrganism newOrganism() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < random.nextInt(20) + 2; i++) {
			builder.append(letters[random.nextInt(letters.length)]);
		}
		return new TestOrganism(builder.toString(), random);
	}
	
	
}
