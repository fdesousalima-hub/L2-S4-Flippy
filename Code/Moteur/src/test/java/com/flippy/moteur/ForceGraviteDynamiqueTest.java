package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.force.ForceGraviteDynamique;

/**
 * Unit test for the ForceGraviteDynamique class
 */
public class ForceGraviteDynamiqueTest {

	/**
	 * Verifies that the calculation of the gravitational force between two objects
	 * is coherent
	 */
	@Test
	public void test() {
		double masseSoleil = 1.989 * Math.pow(10, 30);
		double masseTerre = 5.98 * Math.pow(10, 24);
		Item soleil = new Item(0, 0, masseSoleil);
		Item terre = new Item(0, 1.49 * Math.pow(10, 11), masseTerre);
		assertEquals(-ForceGraviteDynamique.calculForce(soleil, terre).getY(), 3.573462789964417 * Math.pow(10, 22), 1);
	}
}
