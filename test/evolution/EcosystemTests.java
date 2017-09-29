package evolution;

import java.util.List;
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
		for (int i = 0; i < 10; i++) {
			eco.nextGeneration();
			assertEquals(config.getPopulationSize(), eco.getPopulationSize());
			System.out.println("Avg fitness: " + eco.averageFitness());
		}
		System.out.println(eco.getTopN(10));
	}
	
	@Test
	public void testGetAverageFitness() {
		int avg = eco.averageFitness();
		System.out.println("Average fitness: " + avg);
	}
	
	@Test
	public void testGetBestZero() {
		List<TestOrganism> result = eco.getTopN(0);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testGetBestN() {
		List<TestOrganism> result = eco.getTopN(10);
		assertEquals(10, result.size());
	}

//	@Test
//	public void testGetBest() {
//		List<TestOrganism> result = eco.getTopN(1);
//		TestOrganism best = eco.getBest();
//		System.out.println(result.get(0) + " =?= " + best);
//		assertTrue(result.get(0) == best);
//	}

}
