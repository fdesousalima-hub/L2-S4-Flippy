package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.geometry.Vecteur;

/**
 * Unit test for the Vector class
 */
public class VecteurTest {

	/**
	 * Verifies that the constructor and the getters of the class Vector have the
	 * good attributions
	 */
	@Test
	public void forceCreatedWithGoodAttribute() {
		Vecteur f = new Vecteur(2, 3);
		assertEquals(f.getX(), 2.0, 0.00000008);
		assertEquals(f.getY(), 3.0, 0.00000008);
	}

	/**
	 * Verifies that the calculation of the norm is coherent
	 */
	@Test
	public void testLeCalculDeLaNorme() {
		Vecteur f = new Vecteur(100, 0);
		assertEquals(f.getNorme(), 100.0, 0.00000008);
	}

	/**
	 * Checks that the finder of the X coordinate is correct
	 */
	@Test
	public void testGetX() {
		Vecteur f = new Vecteur(12895, 97257);
		assertEquals(f.getX(), 12895, 0.00000008);
	}

	/**
	 * Checks that the g coordinate of the Y coordinate is correct
	 */
	@Test
	public void testGetY() {
		Vecteur f = new Vecteur(12895, 97257);
		assertEquals(f.getY(), 97257, 0.00000008);
	}

	/**
	 * Checks that the setter of the X coordinate is correct
	 */
	@Test
	public void testSetX() {
		Vecteur f = new Vecteur(12895, 97257);
		f.setX(2579);
		assertEquals(f.getX(), 2579, 0.00000008);
	}

	/**
	 * Checks that the setter of the Y coordinate is correct
	 */
	@Test
	public void testSetY() {
		Vecteur f = new Vecteur(12895, 97257);
		f.setY(2579);
		assertEquals(f.getY(), 2579, 0.00000008);
	}

	/**
	 * Checks that the vector sum is correct
	 */
	@Test
	public void testSommeDeForce() {
		Vecteur v1 = new Vecteur(40, 60);
		Vecteur v2 = new Vecteur(60, 40);
		Vecteur somme = Vecteur.sommeDeVecteur(v1, v2);
		assertEquals(somme.getX(), 100, 0.00000008);
		assertEquals(somme.getY(), 100, 0.00000008);
	}

	/**
	 * Checks that the normalization of the vector is correct
	 */
	@Test
	public void testNormaliserVecteur() {
		Vecteur v = new Vecteur(1224542323, 1243535);
		Vecteur.normaliserVecteur(v);
		assertEquals(v.getNorme(), 1, 0.00000008);
	}

	/**
	 * Verifies that the inversion of the vector is correct
	 */
	@Test
	public void testInverserVecteur() {
		Vecteur v = new Vecteur(10, 10);
		Vecteur.inverserVecteur(v);
		assertEquals(v.getX(), -10, 0.00000008);
		assertEquals(v.getY(), -10, 0.00000008);
	}

	/**
	 * Check that the multiplication of the vector with a double is correct
	 */
	@Test
	public void testVecteurFoisDouble() {
		Vecteur v = new Vecteur(10, 10);
		Vecteur.vecteurFoisDouble(v, 10);
		assertEquals(v.getX(), 100, 0.00000008);
		assertEquals(v.getY(), 100, 0.00000008);
	}

	/**
	 * Check that the division of the vector with a double is correct
	 */
	@Test
	public void testVecteurDiviseDouble() {
		Vecteur v = new Vecteur(100, 100);
		Vecteur.vecteurDiviseDouble(v, 10);
		assertEquals(v.getX(), 10, 0.00000008);
		assertEquals(v.getY(), 10, 0.00000008);
	}

}
