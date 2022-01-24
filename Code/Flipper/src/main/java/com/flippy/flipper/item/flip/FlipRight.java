package com.flippy.flipper.item.flip;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Polygon;

/**
 * Class that represents the flip right object
 *
 */
public class FlipRight extends Flip {
	/**
	 * Constructor for the flip right
	 *
	 * @param x Position X
	 * @param y Position Y
	 */
	public FlipRight(double x, double y, double proportion) {
		super(x, y);
		setAngleDeRotation(65);
		this.proportion = proportion;
		Polygon p = new Polygon();
    p.addPoint(Math.rint(0*Game.getProportion().getX()*proportion),Math.rint(0*Game.getProportion().getY()*proportion));
		p.addPoint(Math.rint(-50*Game.getProportion().getX()*proportion),Math.rint(60*Game.getProportion().getY()*proportion));
		p.addPoint(Math.rint(30*Game.getProportion().getX()*proportion),Math.rint(0*Game.getProportion().getY()*proportion));
		this.setShape(p);
	}
}
