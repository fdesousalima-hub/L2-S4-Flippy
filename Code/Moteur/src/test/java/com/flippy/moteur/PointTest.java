package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Vecteur;

/**
 * Unit test for class Point
 */
public class PointTest {

	/**
	 * Verifies that the constructor and the getters of the class Force have the
	 * good attributions
	 */
	@Test
	public void testConstructeur() {
		Point p = new Point(2, 3);
		assertEquals(p.getX(), 2.0, 0.00000008);
		assertEquals(p.getY(), 3.0, 0.00000008);
	}

	/**
	 * Checks that the distance between two points is correct
	 */
	@Test
	public void testDistance() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 100);
		assertEquals(Point.distance(p1, p2), 100.0, 0.00000008);
	}

	/**
	 * Checks that the calculation of the translationDePoint is correct
	 */
	@Test
	public void testTranslationDePoint() {
		Point p = new Point(0, 0);
		Vecteur v = new Vecteur(0, 100);
		Point A = Point.translationDePoint(p, v);
		assertEquals(A.getY(), 100.0, 0.00000008);
		assertEquals(A.getX(), 0.0, 0.00000008);
	}

}
