package com.flippy.moteur.geometry;

import java.util.ArrayList;

/**
 * Class that represents a Polygon
 */
public class Polygon extends Geometry {

	/**
	 * Represents all the points of the Polygon
	 *
	 * @see Point
	 */
	private ArrayList<Point> outline;

	/**
	 * Polygon Constructor who builds an empty Polygon
	 */
	public Polygon() {
		outline = new ArrayList<Point>();
	}

	/**
	 * Polygon Constructor who builds an empty Polygon
	 *
	 * @param l An ArrayList of Points which represents the Polygon
	 * @see Point
	 */
	public Polygon(ArrayList<Point> l) {
		outline = l;
	}

	/**
	 * Gets the ArrayList of Point
	 *
	 * @return The ArrayList of Points of the object
	 * @see Point
	 */
	public ArrayList<Point> getOutline() {
		return outline;
	}

	/**
	 * Add a point to the list of points of the Polygon
	 *
	 * @param x The coordinate x of the new Point
	 * @param y The coordinate y of the new Point
	 * @see Point
	 */
	public void addPoint(double x, double y) {
		outline.add(new Point(x, y));
	}

	/**
	 * Indicates if two Polygon are equals
	 *
	 * @param obj The Polygon to compare
	 * @return A boolean which indicates if two Polygon are equals
	 */
	@Override
	public boolean equals(Object obj) {
		Polygon objp = (Polygon) obj;
		if (this.outline.size() != objp.outline.size()) {
			return false;
		}
		for (int i = 0; i < this.outline.size(); i++) {
			if (!this.outline.get(i).equals(objp.outline.get(i))) {
				return false;
			}
		}
		return true;
	}
}
