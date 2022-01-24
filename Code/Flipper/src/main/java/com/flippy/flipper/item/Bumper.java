package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.item.Item;

/**
 * Class used to create Bumper
 * 
 * @see Item
 */
public class Bumper extends Item {

	/**
	 * Constructor for a Bumper
	 * 
	 * @param x          Position X
	 * @param y          Position Y
	 * @param radius     Radius of bumper
	 * @param proportion size of bumper
	 */
	public Bumper(double x, double y, double radius, double proportion) {
		super(x, y, 10);
		this.coefficientDeRestitution = 2;
		this.proportion = proportion;
		Circle c = new Circle(radius * Game.getProportion().getX() * proportion);
		this.shape = c;
		setScore(10);
	}

	/**
	 * Launch the action of the bumper on colision
	 */
	public void action(Item i) {
		if (((Ball) i).getSuperball())
			Game.addPlayerScore(getScore() * 2);
		else
			Game.addPlayerScore(getScore());
	}

}
