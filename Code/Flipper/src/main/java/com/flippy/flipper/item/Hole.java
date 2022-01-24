package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.Colision;

/**
 * Constructor for the Hole object
 *
 * @see Item
 */
public class Hole extends Item {

	/**
	 * Represents the link from the current object to another
	 */
	private Hole lien;

	/**
	 * Constructor for the object trou
	 *
	 * @param x The coordinate x of the object
	 * @param y The coordinate y ot the object
	 * @see Item
	 */
	public Hole(double x, double y) {
		super(x, y, 12);
		this.coefficientDeRestitution = -1;
		Circle c = new Circle(12 * Game.getProportion().getX());
		this.shape = c;
		this.mass = 2500000000000000.0;
		this.influanceGravite = 50;
	}

	/**
	 * Defined the link from the object to the object goes in parameter this action
	 * is not reciprocal
	 *
	 * @param t The link of the object
	 * @see Item
	 */
	public void ajouterLien(Item t) {
		this.lien = (Hole) t;
	}

	/**
	 * Gets the link of the object
	 *
	 *  @return Return the link of the object
	 */
	public Hole getLien() {
		return this.lien;
	}

	/**
	 * Launch the action of the object at the time of the colision
	 */
	public void action(Item i) {
		if (i.getShape() instanceof Circle && Colision.CollisionPointCercle(i.getPos(), this)
				&& i.getShape().getRadius() <= this.getShape().getRadius()) {
			Vecteur v = new Vecteur(i.getSpeed().getX(), i.getSpeed().getY());
			v = Vecteur.norme(v, lien.getShape().getRadius());
			Point p = Point.translationDePoint(lien.getPos(), v);
			i.setPos(p);
		}
	}
}
