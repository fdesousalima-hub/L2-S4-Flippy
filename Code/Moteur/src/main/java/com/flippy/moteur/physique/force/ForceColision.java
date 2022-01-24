package com.flippy.moteur.physique.force;

import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Line;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.geometry.Segment;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.Colision;

/**
 * Class used to represent the strengths of Colision
 *
 * @see Force
 */
public class ForceColision extends Force {

	/**
	 * Constructor of the ForceColision class which constructs a null Vector Force
	 */
	public ForceColision() {
		super(0, 0);
	}

	/**
	 * Calculate if there is a colision between the objects i1 and i2 and add the
	 * colision vectors to the object
	 *
	 * @param i1 The object i1
	 * @param i2 The object i2
	 * @param t  The time
	 * @see Item
	 */
	public void action(Item i1, Item i2, double t) {
		Segment s = null;
		if (i1.getShape() instanceof Circle && i2.getShape() instanceof Polygon) {
			s = Colision.colisionCerclePolygone(i1, i2);
		}
		if (i2.getShape() instanceof Circle && i1.getShape() instanceof Polygon) {
			s = Colision.colisionCerclePolygone(i2, i1);
		}
		if (i1.getShape() instanceof Circle && i2.getShape() instanceof Line) {
			s = Colision.colisionCercleLigne(i1, i2);
		}
		if (i1.getShape() instanceof Circle && i2.getShape() instanceof Circle) {
			s = Colision.colisionCercleCercle(i1, i2);
		}
		if (s != null) {
			i1.action(i2);
			i2.action(i1);
		}
	}

	/**
	 * Calculates a colision vector between object arrivant and frappant
	 *
	 * @param arrivant The object arrivant
	 * @param frappant The object frappant
	 * @param s        The segment of colision
	 * @see Item
	 * @see Segment
	 */
	public static void reaction(Item arrivant, Item frappant, Segment s) {
		Vecteur directeur = Segment.VecteurDirecteur(s);
		double pscal = Vecteur.produitScalaire(directeur, arrivant.getSpeed());
		directeur = Vecteur.vecteurFoisDouble(directeur, pscal);
		Vecteur normal = Colision.getNormale(s.getA(), s.getB(), arrivant.getPos());
		double pscal2 = Vecteur.produitScalaire(normal, arrivant.getSpeed());
		normal = Vecteur.vecteurFoisDouble(normal, pscal2);
		normal = Vecteur.inverserVecteur(normal);
		normal = Vecteur.vecteurFoisDouble(normal, frappant.getCoefficientDeRestitution());
		Vecteur vitesse = Vecteur.sommeDeVecteur(directeur, normal);
		vitesse = Vecteur.sommeDeVecteur(vitesse, frappant.getSpeed());// Pas sur
		arrivant.setVitesse(vitesse);
	}

}
