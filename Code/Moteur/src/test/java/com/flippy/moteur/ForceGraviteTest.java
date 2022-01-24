package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.physique.force.ForceGravite;

/**
 * ForceGravite class unit test
 */
public class ForceGraviteTest {

	/**
	 * Verifies that the constructor of the Forces class performs the right actions
	 */
	@Test
	public void testConstructeur() {
		ForceGravite fg1 = new ForceGravite(0);
		ForceGravite fg2 = new ForceGravite(1);
		ForceGravite fg3 = new ForceGravite(10);
		assertEquals(fg1.getX(), 0.0, 0.00000008);
		assertEquals(fg2.getX(), 0.0, 0.00000008);
		assertEquals(fg3.getX(), 0.0, 0.00000008);
		assertEquals(fg1.getY(), 0.0 * ForceGravite.constanteDeGravitation, 0.00000008);
		assertEquals(fg2.getY(), 1.0 * ForceGravite.constanteDeGravitation, 0.00000008);
		assertEquals(fg3.getY(), 10.0 * ForceGravite.constanteDeGravitation, 0.00000008);
	}
}
