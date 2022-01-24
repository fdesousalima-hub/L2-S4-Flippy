package com.flippy.moteur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Segment;
import com.flippy.moteur.geometry.Vecteur;

/**
 * Unit test for the Segment class
 */
public class SegmentTest {

	/**
	 * Verifies that the constructor and the investors of the Segment class have the
	 * correct attributions
	 */
	@Test
	public void testConstructeur() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 100);
		Segment s = new Segment(p1, p2);
		assertEquals(s.getA(), p1);
		assertEquals(s.getB(), p2);
	}

	/**
	 * Verifies that the computation of the direction vector of a segment is correct
	 */
	@Test
	public void testVecteurDirecteur() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 100);
		Segment s = new Segment(p1, p2);
		Vecteur v = Segment.VecteurDirecteur(s);
		assertEquals(v.getX(), 0.0, 0.00000008);
		assertEquals(v.getY(), 1.0, 0.00000008);
	}

}
