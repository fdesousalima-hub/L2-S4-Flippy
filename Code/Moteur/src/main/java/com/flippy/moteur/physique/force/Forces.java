package com.flippy.moteur.physique.force;

import java.util.ArrayList;

import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;

/**
 * Class which is used to represent a set of forces which can be applied to a
 * system
 */
public class Forces {

	/**
	 * All forces
	 */
	private ArrayList<Force> forces;

	/**
	 * Force builder, initially all the forces are zero
	 *
	 * @see Vecteur
	 */
	public Forces() {
		this.forces = new ArrayList<Force>();
	}

	/**
	 * Gets all the forces
	 *
	 * @return An array list of all the force
	 * @see Force
	 * @see Vecteur
	 */
	public ArrayList<Force> getForces() {
		return this.forces;
	}

	/**
	 * Add a new force to all the force
	 *
	 * @param f The new forces
	 * @see Force
	 * @see Vecteur
	 */
	public void ajouterForce(Force f) {
		this.forces.add(f);
	}

	/**
	 * Calculate the resultant of the forces from the set of forces
	 *
	 * @return The resultant of the forces from the set of forces
	 * @see Force
	 * @see Vecteur
	 */
	public Force resultante() {
		Vecteur f = new Vecteur(0, 0);
		for (int x = 0; x < this.forces.size(); x++) {
			f = Vecteur.sommeDeVecteur(f, this.forces.get(x));
		}
		return new Force(f.getX(), f.getY());
	}

	/**
	 * Returns the norm of the force vector
	 *
	 *  @return the norm of the force vector
	 */
	public double getNorme() {
		return resultante().getNorme();
	}

	/**
	 * Updates the force in the array
	 *
	 * @param v The new Force
	 * @see Force
	 * @see Vecteur
	 */
	public void setForce(Force v) {
		for (int x = 0; x < this.forces.size(); x++) {
			if (this.forces.get(x).getClass().getName().equals(v.getClass().getName())) {
				v.setX(this.forces.get(x).getX() + v.getX());
				v.setY(this.forces.get(x).getY() + v.getY());
				this.forces.set(x, v);
				return;
			}
		}
	}

	/**
	 * Apply the forces of object i1 and i2
	 *
	 * @param i1 The object i1
	 * @param i2 The object i2
	 * @param t  The time
	 * @see Item
	 */
	public static void action(Item i1, Item i2, double t) {
		for (Force a : i1.getForces().getForces()) {
			a.action(i1, i2, t);
		}
		for (Force a : i2.getForces().getForces()) {
			a.action(i2, i1, t);
		}
	}

	/**
	 * Resets all object strengths if they are resettable
	 */
	public void reinitialiser() {
		for (Force f : this.forces) {
			f.reinitialiser();
		}
	}

}
