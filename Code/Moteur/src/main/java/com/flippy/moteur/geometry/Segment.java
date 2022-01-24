package com.flippy.moteur.geometry;

/**
 * Class that represents a Segment
 */
public class Segment {

	/**
	 * Starting point of the segment
	 */
	private Point A;

	/**
	 * Point of arrival of the segment
	 */
	private Point B;

	/**
	 * Construct a Segment AB
	 *
	 * @param a The point A
	 * @param b The point B
	 * @see Point
	 */
	public Segment(Point a, Point b) {
		this.A = a;
		this.B = b;
	}

	/**
	 * Gets the Point A of the Segment
	 *
	 * @return The Point A of the Segment
	 * @see Point
	 */
	public Point getA() {
		return this.A;
	}

	/**
	 * Gets the Point B of the Segment
	 *
	 * @return The Point B of the Segment
	 * @see Point
	 */
	public Point getB() {
		return this.B;
	}

	/**
	 * Returns a unitary direction vector of the segment AB
	 *
	 * @param AB The segment AB
	 * @return A unitary direction vector of the segment AB
	 * @see Vecteur
	 */
	public static Vecteur VecteurDirecteur(Segment AB) {
		Vecteur d = new Vecteur(AB.getA(), AB.getB());
		return Vecteur.normaliserVecteur(d);
	}
}
