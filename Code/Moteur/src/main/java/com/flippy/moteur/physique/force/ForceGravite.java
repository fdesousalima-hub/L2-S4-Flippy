package com.flippy.moteur.physique.force;

/**
 * Represents the force of Earth's gravity at the surface
 *
 *  @see Force
 */
public class ForceGravite extends Force {

	/**
	 * Represents the intensity of earthly thinker
	 */
	public static double constanteDeGravitation = 9.81;

	/**
	 * ForceGravite constructor
	 *
	 * @param masse The mass of the object
	 */
	public ForceGravite(double masse) {
		super(0, masse * constanteDeGravitation, false);
	}

}
