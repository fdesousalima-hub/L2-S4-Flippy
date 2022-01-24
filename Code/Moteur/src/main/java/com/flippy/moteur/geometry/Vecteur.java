package com.flippy.moteur.geometry;

/**
 * Class that represents a vector
 */
public class Vecteur extends Point {

	/**
	 * Construct a new Vecteur
	 *
	 * @param x The coordinate x of the vector
	 * @param y The coordinate y of the vector
	 */
	public Vecteur(double x, double y) {
		super(x, y);
	}

	/**
	 * Construct a vector going from point A to point B
	 *
	 * @param A Starting point
	 * @param B Ending point
	 */
	public Vecteur(Point A, Point B) {
		super(B.getX() - A.getX(), B.getY() - A.getY());
	}

	/**
	 * Gets the norm of the vector
	 *
	 * @return The norm of the vector
	 */
	public double getNorme() {
		return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
	}

	/**
	 * Return an addition of the vector f1 and f2
	 *
	 * @param f1 The vector f1
	 * @param f2 The vector f2
	 * @return The addition of the vector passed in parameter
	 */
	public static Vecteur sommeDeVecteur(Vecteur f1, Vecteur f2) {
		return new Vecteur(f1.getX() + f2.getX(), f1.getY() + f2.getY());
	}

	/**
	 * Modifies the Vector passed in parameter so that its norm is equal to 1 and
	 * returns it
	 *
	 * @param v The vector to modify
	 * @return The vector modified
	 */
	public static Vecteur normaliserVecteur(Vecteur v) {
		double norme = Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		v.setX((v.getX() / norme));
		v.setY((v.getY() / norme));
		return v;
	}

	/**
	 * Inverts the direction of the Vector goes into parameter and returns it
	 *
	 * @param v The vector to modify
	 * @return The vector modified
	 */
	public static Vecteur inverserVecteur(Vecteur v) {
		v.setX(-v.getX());
		v.setY(-v.getY());
		return v;
	}

	/**
	 * Multiplies the vector with the double pass in parameter and returns it
	 *
	 * @param v The vector concerned
	 * @param d The double to divide
	 * @return The vector divided with the double
	 */
	public static Vecteur vecteurFoisDouble(Vecteur v, double d) {
		v.setX((v.getX() * d));
		v.setY((v.getY() * d));
		return v;
	}

	/**
	 * Divide the vector with the double pass in parameter and returns it
	 *
	 * @param v The vector concerned
	 * @param d The double to multiply
	 * @return The vector multiplied with the double
	 */
	public static Vecteur vecteurDiviseDouble(Vecteur v, double d) {
		v.setX((v.getX() / d));
		v.setY((v.getY() / d));
		return v;
	}

	/**
	 * Calculates a normal vector with respect to the vector passed in parameter
	 *
	 * @param v The vector concerned
	 * @return Return a normal vector with respect to the vector passed in parameter
	 */
	public static Vecteur vecteurNormal(Vecteur v) {
		return new Vecteur(-v.getY(), v.getX());
	}

	/**
	 * Calculates the dot product of vectors v1 and v2
	 *
	 * @param v1 The vector v1
	 * @param v2 The Vector v2
	 * @return Return the dot product of vectors v1 and v2
	 */
	public static double produitScalaire(Vecteur v1, Vecteur v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}

	/**
	 * Returns a new vector which has the same direction as v and has the standard d
	 *
	 * @param v The vector concerned
	 * @param d the standard wish for a new vector
	 * @return The new vector which has the same direction as v and has the standard
	 *         d
	 */
	public static Vecteur norme(Vecteur v, double d) {
		Vecteur ret = normaliserVecteur(v);
		return vecteurFoisDouble(ret, d);
	}

}
