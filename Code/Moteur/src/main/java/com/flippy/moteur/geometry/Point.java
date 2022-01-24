package com.flippy.moteur.geometry;

/**
 * Class that represents a point in space
 */
public class Point {

	/**
	 * Object x coordinate
	 */
	private double x;

	/**
	 * Object y coordinate
	 */
	private double y;

	/**
	 * Constructs an x and y coordinate point
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x coordinate
	 *
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y coordinate
	 *
	 * @return The y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the x coordinate
	 *
	 * @param x The x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y coordinate
	 *
	 * @param y The y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Calculate the distance between point A and B
	 *
	 * @param A The point A
	 * @param B The Point B
	 * @return The distance between the point A and B
	 */
	public static double distance(Point A, Point B) {
		return Math.sqrt(Math.pow(B.getX() - A.getX(), 2) + Math.pow(B.getY() - A.getY(), 2));
	}

	/**
	 * Calculate the point resulting from the sum of point A with the vector v
	 *
	 * @param A The point A
	 * @param v The vector v
	 * @return The point resulting from the sum of point A with the vector v  @see
	 *         Vecteur
	 */
	public static Point translationDePoint(Point A, Vecteur v) {
		return new Point(A.getX() + v.getX(), A.getY() + v.getY());
	}

	/**
	 * Convert point to character string indicating point coordinates
	 *
	 * @return The character string indicating point coordinates
	 */
	public String toString() {
		return "x :	" + this.x + "	y : " + this.y;
	}

	/**
	 * Indicates if one of the coordinates of the current point is identical to the
	 * parameter
	 *
	 * @param obj The Point to compare
	 * @return A boolean which indicates if one of the coordinates of the current
	 *         point is identical to the parameter
	 */
	@Override
	public boolean equals(Object obj) {
		Point pointObj = (Point) obj;
		if (getX() != pointObj.getX() || getY() != pointObj.getY()) {
			return false;
		}
		return true;
	}
}
