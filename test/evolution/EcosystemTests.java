package evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class EcosystemTests extends TestCase{

	EvolutionConfig config;
	private Ecosystem<TestOrganism> eco;

	@Before
	public void setUp() throws Exception {
		config = new EvolutionConfig();
		Random random = new Random(1);
		TestOrganismGenerator generator = new TestOrganismGenerator(random);
		eco = new Ecosystem<TestOrganism>(generator, config);
	}

	@Test
	public void testGetPopulationSize() {
		assertEquals(config.getPopulationSize(), eco.getPopulationSize());
	}

	@Test
	public void testNextGeneration() {
		System.out.println(eco.getTopN(10));
		System.out.println("Avg fitness: " + eco.averageFitness());
		eco.nextGeneration();
		assertEquals(config.getPopulationSize(), eco.getPopulationSize());
		System.out.println("Avg fitness: " + eco.averageFitness());
	}
	
	@Test
	public void testGetAverageFitness() {
		int avg = eco.averageFitness();
		System.out.println("Average fitness: " + avg);
	}

}
