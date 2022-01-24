package com.flippy.moteur.physique.force;

import java.util.Vector;

import com.flippy.moteur.geometry.Vecteur;

/**
 * Class which is used to represent a restoring force
 *
 * @see Force
 */
public class ForceRappel extends Force {

	/**
	 * Constructor of the ForceReminder class that builds a Vector Force
	 *
	 * @param i The vector of the force
	 * @see Vector
	 */
	public ForceRappel(Vecteur i) {
		super(i.getX(), i.getY(), false);
	}

}
