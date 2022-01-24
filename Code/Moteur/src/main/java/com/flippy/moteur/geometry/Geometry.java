package com.flippy.moteur.geometry;

import java.util.ArrayList;

/**
 * Represents the outline of an Item
 */
public abstract class Geometry {

	/**
	 * Gets the ArrayList of Point
	 *
	 * @return An empty ArrayList of Points if the object is not a Polygon
	 * @see Point
	 * @see Polygon
	 */
	public ArrayList<Point> getOutline() {
		return new ArrayList<Point>();
	}

	/**
	 * Gets the ArrayList of Point
	 *
	 * @return An empty ArrayList of Points if the object is not a Line
	 * @see Point
	 * @see Line
	 */
	public ArrayList<Point> getLine() {
		return new ArrayList<Point>();
	}

	/**
	 * Gets the radius of the object
	 *
	 * @return Return -1 if the object is not a Circle
	 * @see Point
	 * @see Circle
	 */
	public double getRadius() {
		return -1;
	}

	/**
	 * Ajoute un point a la geometry
	 *
	 *  @see Point
	 */
	public void addPoint(Point p) {
	}
}
