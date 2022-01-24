package com.flippy.moteur.geometry;

import java.util.ArrayList;

/**
 * Class that represents a line
 */
public class Line extends Geometry {

	/**
	 * Represents all the points of the line
	 *
	 * @see Point
	 */
	private ArrayList<Point> line;

	/**
	 * Line Constructor who builds an empty Line
	 */
	public Line() {
		this.line = new ArrayList<>();
	}

	/**
	 * Line Constructor who builds an empty Line
	 *
	 * @param l An ArrayList of Points which represents the line
	 * @see Point
	 */
	public Line(ArrayList<Point> l) {
		this.line = l;
	}

	/**
	 * Add a point to the list of points of the line and add at its ends two very
	 * close points to create a thickness for the segment
	 *
	 * @param p The new Point to add to the line
	 * @see Point
	 */
	public void addPoint(Point p) {
		if (line.size() == 0)
			line.add(p);
		else if (line.size() == 1) {
			line.add(p);
			Vecteur v = new Vecteur(line.get(0), line.get(1));
			Vecteur normal = Vecteur.vecteurNormal(v);
			normal = Vecteur.vecteurDiviseDouble(normal, 1_000_000_000);
			line.add(Point.translationDePoint(line.get(1), normal));
			line.add(0, Point.translationDePoint(line.get(0), normal));
		} else {
			line.remove(line.size() - 1);
			line.add(p);
			Vecteur v = new Vecteur(line.get(line.size() - 2), line.get(line.size() - 1));
			Vecteur normal = Vecteur.vecteurNormal(v);
			normal = Vecteur.vecteurDiviseDouble(normal, 1_000_000_000);
			line.add(Point.translationDePoint(line.get(line.size() - 1), normal));
		}
	}

	/**
	 * Gets the ArrayList of Point
	 *
	 * @return The ArrayList of Points of the object
	 * @see Point
	 */
	public ArrayList<Point> getLine() {
		return line;
	}

}
