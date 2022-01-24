package com.flippy.moteur.physique.force;

import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;

/**
 * Class which is used to represent a force on the model of a vector
 *
 * @see Vecteur
 */
public class Force extends Vecteur {

	/**
	 * Indicates if the force should be reset at each check
	 */
	private boolean reinitialisable;

	/**
	 * Class force constructor who builds a Vector Force
	 * 
	 * @param x Coordinate x of the vector
	 * @param y Coordinate y of the vector
	 * @param b Indicates whether the force should be reset after each check
	 */
	public Force(double x, double y, boolean b) {
		super(x, y);
		this.reinitialisable = b;
	}

	/**
	 * Class force constructor who builds a Vector Force
	 * 
	 * @param x Coordinate x of the vector
	 * @param y Coordinate y of the vector
	 */
	public Force(double x, double y) {
		super(x, y);
	}

	/**
	 * Class force constructor who builds a Vector Force
	 * 
	 * @param A Starting point
	 * @param B Ending point
	 * @param b Indicates whether the force should be reset after each check
	 */
	public Force(Point A, Point B, boolean b) {
		super(B, A);
		this.reinitialisable = b;
	}

	/**
	 * Method used to calculate a force based on item i1 and i2
	 *
	 *  @param i1 The item i1
	 * 
	 * @param i2 The item i2
	 * @param t  The time
	 * @see Item
	 */
	public void action(Item i1, Item i2, double t) {
	}

	/**
	 * resets the force if it is resettable
	 */
	public void reinitialiser() {
		if (!this.reinitialisable)
			return;
		this.setX(0);
		this.setY(0);
	}
}
