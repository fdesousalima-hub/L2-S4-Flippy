package com.flippy.moteur.physique;

import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Segment;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.force.ForceColision;

/**
 * This class includes all the functions that allow you to calculate a colision
 * and assign them a reaction.
 */
public class Colision {

	/**
	 * Calculate if there is a colision between the circles i1 and i2 and add the
	 * colision vectors to the object
	 *
	 * @param i1 The circle i1
	 * @param i2 The circle i2
	 * @return The segment tangent to the two circles
	 * @see Item
	 * @see Segment
	 */
	public static Segment colisionCercleCercle(Item i1, Item i2) {
		double dist = Point.distance(i1.getPos(), i2.getPos());
		if (dist < i1.getShape().getRadius() + i2.getShape().getRadius()) {
			Vecteur v = new Vecteur(i1.getPos(), i2.getPos());
			v = Vecteur.norme(v, i1.getShape().getRadius());
			Vecteur normal = Vecteur.vecteurNormal(v);
			Point A = Point.translationDePoint(i1.getPos(), v);
			Point B = Point.translationDePoint(A, normal);
			ForceColision.reaction(i1, i2, new Segment(A, B));
			return new Segment(A, B);
		}
		return null;
	}

	/**
	 * Calculate if there is a colision between the circle i1 and the Polygon i2 and
	 * add the colision vectors to the object
	 *
	 * @param i1 The circle i1
	 * @param i2 The Polygon i2
	 * @return The segment where the colision is detected with the polygon i2
	 * @see Item
	 * @see Segment
	 */
	public static Segment colisionCerclePolygone(Item i1, Item i2) {
		Segment ret = null;
		Point A, B;
		Point T = i1.getNewPos(0.024);
		Item projection = new Item(i1.getNewPos(0.024).getX(), i1.getNewPos(0.024).getY(), 10);
		projection.setShape(new Circle(i1.getShape().getRadius()));
		for (int x = 0; x < i2.getShape().getOutline().size(); x++) {
			A = i2.getShape().getOutline().get(x);
			A = new Point(A.getX() + i2.getPosX(), A.getY() + i2.getPosY());
			B = i2.getShape().getOutline().get(x == i2.getShape().getOutline().size() - 1 ? 0 : x + 1);
			B = new Point(B.getX() + i2.getPosX(), B.getY() + i2.getPosY());
			if (colisionSegmentCercle(A, B, projection) || colisionSegmentSegment(A, B, i1.getPos(), T)) {
				ForceColision.reaction(i1, i2, new Segment(A, B));
				ret = new Segment(A, B);
			}
		}
		return ret;
	}

	/**
	 * Calculate if there is a colision between the circle i1 and the line i2 and
	 * add the colision vectors to the object
	 *
	 * @param i1 The circle i1
	 * @param i2 The line i2
	 * @return The segment where the colision is detected with the line i2
	 * @see Item
	 * @see Segment
	 */
	public static Segment colisionCercleLigne(Item i1, Item i2) {
		Segment ret = null;
		Point A, B;
		Item projection = new Item(i1.getNewPos(0.024).getX(), i1.getNewPos(0.024).getY(), 10);
		projection.setShape(new Circle(i1.getShape().getRadius()));
		for (int x = 0; x < i2.getShape().getLine().size() - 1; x++) {
			A = i2.getShape().getLine().get(x);
			A = new Point(A.getX() + i2.getPosX(), A.getY() + i2.getPosY());
			B = i2.getShape().getLine().get(x + 1);
			B = new Point(B.getX() + i2.getPosX(), B.getY() + i2.getPosY());
			if (colisionSegmentCercle(A, B, projection)
					|| colisionSegmentSegment(A, B, i1.getPos(), projection.getPos())) {
				ForceColision.reaction(i1, i2, new Segment(A, B));
				ret = new Segment(A, B);
			}
		}
		return ret;
	}

	/**
	 * Calculate if there is a collision between the line AB and the circle C
	 *
	 * @param A The Point A
	 * @param B The point B
	 * @param C The circle C
	 * @return Return true if there is a collision between the line AB and the
	 *         circle C
	 * @see Point
	 */
	public static boolean colisionDroiteCercle(Point A, Point B, Item C) {
		Vecteur AB = new Vecteur(A, B);
		Vecteur AC = new Vecteur(A, C.getPos());
		double numerateur = Math.abs(AB.getX() * AC.getY() - AB.getY() * AC.getX());
		double denominateur = AB.getNorme();
		double CI = numerateur / denominateur;
		return CI <= C.getShape().getRadius();
	}

	/**
	 * Calculate if there is a collision between the segment AB and the circle C
	 *
	 * @param A The Point A
	 * @param B The point B
	 * @param C The circle C
	 * @return Return true if there is a collision between the segment AB and the
	 *         circle C
	 * @see Point
	 */
	public static boolean colisionSegmentCercle(Point A, Point B, Item C) {
		if (!colisionDroiteCercle(A, B, C))
			return false;
		Vecteur AB = new Vecteur(A, B);
		Vecteur AC = new Vecteur(A, C.getPos());
		Vecteur BC = new Vecteur(B, C.getPos());
		double pscal1 = AB.getX() * AC.getX() + AB.getY() * AC.getY();
		double pscal2 = (-AB.getX()) * BC.getX() + (-AB.getY()) * BC.getY();
		if (pscal1 >= 0 && pscal2 >= 0)
			return true;
		if (CollisionPointCercle(A, C))
			return true;
		if (CollisionPointCercle(B, C))
			return true;
		return false;
	}

	/**
	 * Calculate if the point A is inside the circle C
	 *
	 * @param A The Point A
	 * @param C The circle C
	 * @return Return true if the point A is inside the circle C
	 * @see Point
	 */
	public static boolean CollisionPointCercle(Point A, Item C) {
		return Math.sqrt(Math.pow(A.getX() - C.getPosX(), 2) + Math.pow(A.getY() - C.getPosY(), 2)) <= C.getShape()
				.getRadius();
	}

	/**
	 * Calculates a normal vector a AB of norm 1 and which looks in the direction of
	 * point C
	 *
	 * @param A The point A
	 * @param B The point B
	 * @param C The point C
	 * @return Return a normal vector a AB of norm 1 and which looks in the
	 *         direction of point C
	 * @see Vecteur
	 * @see Point
	 */
	public static Vecteur getNormale(Point A, Point B, Point C) {
		Vecteur AB = new Vecteur(A, B);
		Vecteur AC = new Vecteur(A, C);
		double parenthesis = AB.getX() * AC.getY() - AB.getY() * AC.getX();
		Vecteur normal = new Vecteur(-AB.getY() * parenthesis, AB.getX() * parenthesis);
		return Vecteur.normaliserVecteur(normal);
	}

	/**
	 * Calculates if there is a collision between the line AB and the segment CD
	 *
	 * @param A The point A
	 * @param B The point B
	 * @param C The point C
	 * @param D The point D
	 * @return Return true if there is a collision between the line AB and the
	 *         segment CD
	 * @see Point
	 */
	public static boolean colisionDroiteSegment(Point A, Point B, Point C, Point D) {
		Vecteur AB = new Vecteur(A, B);
		Vecteur AD = new Vecteur(A, D);
		Vecteur AC = new Vecteur(A, C);
		return ((AB.getX() * AD.getY() - AB.getY() * AD.getX()) * (AB.getX() * AC.getY() - AB.getY() * AC.getX()) < 0);
	}

	/**
	 * Calculates if there is a collision between the segment AB and the segment CD
	 *
	 * @param A The point A
	 * @param B The point B
	 * @param C The point C
	 * @param D The point D
	 * @return Return true if there is a collision between the segment AB and the
	 *         segment CD
	 * @see Point
	 */
	public static boolean colisionSegmentSegment(Point A, Point B, Point C, Point D) {
		if (!colisionDroiteSegment(A, B, C, D))
			return false;
		Vecteur AB = new Vecteur(A, B);
		Vecteur CD = new Vecteur(C, D);
		double k = -(A.getX() * CD.getY() - C.getX() * CD.getY() - CD.getX() * A.getY() + CD.getX() * C.getY())
				/ (AB.getX() * CD.getY() - AB.getY() * CD.getX());
		return (k >= 0 && k <= 1);
	}

	/**
	 * Calculates if a point P is inside the polygon i
	 *
	 * @param i The polygon i
	 * @param P The point P
	 * @return Return true if a point P is inside the polygon i
	 * @see Item
	 * @see Point
	 */
	public static boolean colisionPolygonePoint(Item i, Point P) {
		Point A, B;
		Vecteur AB, AP;
		for (int x = 0; x < i.getShape().getOutline().size(); x++) {
			A = i.getShape().getOutline().get(x);
			A = new Point(A.getX() + i.getPosX(), A.getY() + i.getPosY());
			B = i.getShape().getOutline().get(x == i.getShape().getOutline().size() - 1 ? 0 : x + 1);
			B = new Point(B.getX() + i.getPosX(), B.getY() + i.getPosY());
			AB = new Vecteur(A, B);
			AP = new Vecteur(A, P);
			double d = AB.getX() * AP.getY() - AB.getY() * AP.getX();
			if (d > 0)
				return false;
		}
		return true;
	}
}
