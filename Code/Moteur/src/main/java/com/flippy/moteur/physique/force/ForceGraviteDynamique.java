package com.flippy.moteur.physique.force;

import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;

/**
 * Class which is used to represent a force of gravity on two object
 *
 * @see Force
 */
public class ForceGraviteDynamique extends Force {

	/**
	 * Represents the Gravitational constant
	 */
	public static double G = 6.67 * Math.pow(10, -11);

	/**
	 * DynamicGraviteDynamic constructor which initializes a null vector
	 */
	public ForceGraviteDynamique() {
		super(0, 0, true);
	}

	/**
	 * DynamicGraviteDynamic constructor which initializes a vector going from A to
	 * B
	 *
	 * @param A The point A
	 * @param B The point B
	 * @see Point
	 */
	public ForceGraviteDynamique(Point A, Point B) {
		super(A, B, true);
	}

	/**
	 * Calculates the vector of ForceGraviteDynamique which is applied on the
	 * objects i1 and i2
	 *
	 * @param i1 The object i1
	 * @param i2 The object i2
	 * @return the force of ForceGraviteDynamique which is applied on the objects i1
	 *         and i2
	 * @see Item
	 */
	public static ForceGraviteDynamique calculForce(Item i1, Item i2) {
		boolean b = Math.sqrt(Math.pow(i1.getPosX() - i2.getPosX(), 2) + Math.pow(i1.getPosY() - i2.getPosY(), 2)) <= i2
				.getInfluanceGravite();
		if (i2.getInfluanceGravite() == -1 || b) {
			double norme = G * i1.getMass() * i2.getMass() / Math.pow(Point.distance(i1.getPos(), i2.getPos()), 2);
			ForceGraviteDynamique res = new ForceGraviteDynamique(i1.getPos(), i2.getPos());
			Vecteur.normaliserVecteur(res);
			Vecteur.vecteurFoisDouble(res, norme);
			return res;
		}
		return new ForceGraviteDynamique();
	}

	/**
	 * Apply on the object i1 the dynamicGraviteDynamic vector which is applied to
	 * the objects i1 and i2
	 *
	 * @param i1 The object i1
	 * @param i2 The object i2
	 * @param t  The time
	 * @see Item
	 */
	public void action(Item i1, Item i2, double t) {
		ForceGraviteDynamique f = (ForceGraviteDynamique) Vecteur.inverserVecteur(calculForce(i1, i2));
		i1.setForce(f);
	}
}
