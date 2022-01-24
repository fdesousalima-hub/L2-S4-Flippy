package com.flippy.moteur.geometry;

/**
 * Class that represents a Circle
 * 
 * @see Geometry
 */
public class Circle extends Geometry {

	/**
	 * Represents the radius of the circle
	 */
	private double radius;

	/**
	 * Constructor of the class Circle which takes a radius argument
	 * 
	 * @param d The radius of the object
	 * @see Geometry
	 */
	public Circle(double d) {
		this.radius = d;
	}

	/**
	 * Gets the radius of the circle
	 * 
	 * @return The radius of the object
	 */
	public double getRadius() {
		return radius;
	}

}
