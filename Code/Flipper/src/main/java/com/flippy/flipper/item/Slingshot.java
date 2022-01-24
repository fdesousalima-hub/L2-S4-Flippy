package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.item.Item;

/**
 * Class that represents the Slingshot object
 *
 * @see Item
 */
public class Slingshot extends Item {

	/**
	 * Constructor for the object Slingshot
	 *
	 * @param x The coordinate x of the object
	 * @param y The coordinate y ot the object*
	 * @see Item
	 */
	public Slingshot(double x, double y) {
		super(x, y, 10);
		this.coefficientDeRestitution = 2;
		setScore(5);
	}

	/**
	 * Launch the action of the object at the time of the colision
	 */
	public void action(Item i) {
		if (((Ball) i).getSuperball())
			Game.addPlayerScore(getScore() * 2);
		else
			Game.addPlayerScore(getScore());
	}
}
