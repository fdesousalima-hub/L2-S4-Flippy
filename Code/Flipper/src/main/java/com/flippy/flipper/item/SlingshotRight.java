package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.item.Item;

/**
 * Class that represents the SlingshotRight object
 *
 * @see Item
 * @see Slingshot
 */
public class SlingshotRight extends Slingshot {

	/**
	 * Constructor for the object SlingshotRight
	 *
	 * @param x          The coordinate x of the object
	 * @param y          The coordinate y ot the object
	 * @param proportion The proportion of the object
	 * @see Item
	 */
	public SlingshotRight(double x, double y, double proportion) {
		super(x, y);
		Polygon p = new Polygon();
		this.proportion = proportion;
		p.addPoint(20 * Game.getProportion().getX() * proportion, -60 * Game.getProportion().getY() * proportion);
		p.addPoint(20 * Game.getProportion().getX() * proportion, 20 * Game.getProportion().getY() * proportion);
		p.addPoint(-30 * Game.getProportion().getX() * proportion, 40 * Game.getProportion().getY() * proportion);
		setShape(p);
	}
}
