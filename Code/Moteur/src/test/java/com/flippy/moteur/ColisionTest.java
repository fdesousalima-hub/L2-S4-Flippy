package com.flippy.moteur;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.Colision;

/**
 * Unit test for the Colision class
 */
public class ColisionTest {

	/**
	 * Verifies that a Circle collides with a straight line
	 */
	@Test
	public void testColisionDroiteCercle() {
		Item circle = new Item(0, 10, 10);
		circle.setShape(new Circle(10));
		assertTrue(Colision.colisionDroiteCercle(new Point(-100.0, 0.0), new Point(100.0, 0.0), circle));
		Item circle2 = new Item(0, 0, 10);
		circle2.setShape(new Circle(10));
		assertTrue(Colision.colisionDroiteCercle(new Point(-100.0, 0.0), new Point(100.0, 0.0), circle2));
		Item circle3 = new Item(0, 11, 10);
		circle3.setShape(new Circle(10));
		assertFalse(Colision.colisionDroiteCercle(new Point(-100.0, 0.0), new Point(100.0, 0.0), circle3));
	}

	/**
	 * Verifies that a point is included inside or on the border of a circle
	 */
	@Test
	public void testCollisionPointCercle() {
		Item circle = new Item(0, 10, 10);
		circle.setShape(new Circle(10));
		assertTrue(Colision.CollisionPointCercle(new Point(0, 0), circle));
		Item circle2 = new Item(0, 0, 10);
		circle2.setShape(new Circle(10));
		assertTrue(Colision.CollisionPointCercle(new Point(0, 0), circle2));
		Item circle3 = new Item(0, 11, 10);
		circle3.setShape(new Circle(10));
		assertFalse(Colision.CollisionPointCercle(new Point(0, 0), circle3));
	}

	/**
	 * Check that the calculation of a normal vector is correct
	 */
	@Test
	public void testGetNormale() {
		Point p1 = new Point(0, 5);
		Point p2 = new Point(5, 0);
		Point p3 = new Point(5, 5);
	}

	/**
	 * Verifies that a Circle collides with a segment
	 */
	@Test
	public void testVecteurColision() {
		Vecteur arrive = new Vecteur(-10.0, 0.0);
		Vecteur normal = new Vecteur(1.0, 0.0);
		// assertEquals(Colision.VecteurColision(arrive,normal).getX(),10.0,0.00000008);
		// assertEquals(Colision.VecteurColision(arrive,normal).getY(),0.0,0.00000008);
	}

	/**
	 * Verifie qu'un Cercle entre en colision avec un segment
	 */
	@Test
	public void testColisionSegmentCercle() {
		Point p1 = new Point(-10, 0);
		Point p2 = new Point(10, 0);
		Item circle = new Item(0, 10, 10);
		circle.setShape(new Circle(10));
		assertTrue(Colision.colisionSegmentCercle(p1, p2, circle));
		circle.setShape(new Circle(9));
		assertFalse(Colision.colisionSegmentCercle(p1, p2, circle));
	}

	/**
	 * Verifies that the collision between a circle and a segment is valid
	 */
	@Test
	public void testColisionCerclePolygone() {
		Item circle = new Item(0, 13, 10);
		circle.setShape(new Circle(10));
		Polygon poly = new Polygon();
		Item Polygon = new Item(0, 0, 10);
		poly.addPoint(-5, 5);
		poly.addPoint(5, 5);
		poly.addPoint(5, -5);
		poly.addPoint(-5, -5);
		Polygon.setShape(poly);
		Colision.colisionCerclePolygone(circle, Polygon);
	}

	/**
	 * Verifies that the collision between a straight line and a segment is valid
	 */
	@Test
	public void testColisionDroiteSegment() {
		Point A = new Point(0, 0);
		Point B = new Point(10, 0);
		Point C = new Point(0, 10);
		Point D = new Point(10, 10);
		assertTrue(Colision.colisionDroiteSegment(A, D, C, B));
		assertFalse(Colision.colisionDroiteSegment(A, B, C, D));
	}

	/**
	 * Verifies that the colonization between two segments is valid
	 */
	@Test
	public void testColisionSegmentSegment() {
		Point A = new Point(0, 0);
		Point B = new Point(10, 0);
		Point C = new Point(0, 10);
		Point D = new Point(10, 10);
		assertTrue(Colision.colisionSegmentSegment(A, D, C, B));
		assertFalse(Colision.colisionSegmentSegment(A, B, C, D));
	}

	/**
	 * Verifies that the detection of the point inside a polygon is valid
	 */
	@Test
	public void testColisionPolygonePoint() {
		Polygon poly = new Polygon();
		Item Polygon = new Item(0, 0, 10);
		poly.addPoint(-10, 10);
		poly.addPoint(10, 10);
		poly.addPoint(10, -10);
		poly.addPoint(-10, -10);
		Polygon.setShape(poly);
		Point A = new Point(0, 0);
		Point B = new Point(20, 0);
		assertTrue(Colision.colisionPolygonePoint(Polygon, A));
		assertFalse(Colision.colisionPolygonePoint(Polygon, B));
	}

	/**
	 * Verifies that the colision between circle is valid
	 */
	@Test
	public void colisionCercleCerlce() {
	}

	/**
	 * Verifies that the colonization between polygons is valid
	 */
	@Test
	public void colisionPolygonePolygone() {
	}
}
