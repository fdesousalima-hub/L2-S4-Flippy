package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.physique.force.Force;
import com.flippy.moteur.physique.force.Forces;

/**
 * Unit test for the Forces class.
 */
public class ForcesTest {

	/**
	 * Verifies that the constructor of the Forces class performs the right actions
	 */
	@Test
	public void testConstructeur() {
		Forces f = new Forces();
		assertEquals(f.getForces().size(), 0);
	}

	/**
	 * Check that the addForce function adds a force at the end of the array and
	 * that it corresponds to the force you want
	 */
	@Test
	public void testDeLAjoutDUneNouvelleForce() {
		Forces fs = new Forces();
		Force f1 = new Force(0, 0);
		fs.ajouterForce(f1);
		assertEquals(fs.getForces().get(0), f1);
		assertEquals(fs.getForces().size(), 1);
		Force f2 = new Force(2, 3);
		fs.ajouterForce(f2);
		assertEquals(fs.getForces().get(1), f2);
		assertEquals(fs.getForces().size(), 2);
	}

	/**
	 * Test if the calculation of the resultant force is correct
	 */
	@Test
	public void testResultanteDesForce() {
		Forces fs = new Forces();
		Force f1 = new Force(-2, 9);
		Force f2 = new Force(1, 1);
		fs.ajouterForce(f1);
		fs.ajouterForce(f2);
		Force f3 = fs.resultante();
		assertEquals(f3.getX(), -1.0, 0.00000008);
		assertEquals(f3.getY(), 10.0, 0.00000008);
	}
}
