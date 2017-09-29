package evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestOrganismTests extends TestCase {

	TestOrganism organism1;
	TestOrganism organism2;
	
	@Before
	public void setUp() throws Exception {
		TestOrganismGenerator gen = new TestOrganismGenerator(new Random(2));
		organism1 = gen.newOrganism();
		organism2 = gen.newOrganism();
	}

	@Test
	public void testBreed() {
		System.out.println("Mommy: " + organism1 + ", Daddy: " + organism2);
		TestOrganism child = organism1.breed(organism2);
		System.out.println("    Baby: " + child);
		//assertEquals(organism1.getName().length(), child.getName().length());
	}

	@Test
	public void testMutate() {
		String origName = organism1.getName();
		organism1.mutate();
		assertNotEquals(origName, organism1.getName());
	}

	@Test
	public void testFitness() {
		organism1.setName("aaaabbccddeeff");
		assertEquals(4, organism1.fitness());
	}

}
