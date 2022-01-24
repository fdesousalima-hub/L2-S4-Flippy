package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.item.Item;

/**
 * Class that represents the Planete object
 *
 * @see Item
 */
public class Planet extends Item {

	/**
	 * Constructor for the object Planete
	 *
	 * @param x          The coordinate x of the object
	 * @param y          The coordinate y ot the object
	 * @param radius     The radius of the object
	 * @param proportion The proportion of the object
	 * @see Item
	 */
	public Planet(double x, double y, double radius, double proportion) {
		super(x, y, 10);
		this.coefficientDeRestitution = 0;
		this.proportion = proportion;
		Circle c = new Circle(radius * Game.getProportion().getX() * proportion);
		this.shape = c;
		this.mass = 500000000000000.0*radius*proportion;
		this.influanceGravite = 3*radius*proportion;
	}

}
